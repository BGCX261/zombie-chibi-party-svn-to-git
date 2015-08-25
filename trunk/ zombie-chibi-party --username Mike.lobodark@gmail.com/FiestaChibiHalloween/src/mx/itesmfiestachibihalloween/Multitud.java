package mx.itesmfiestachibihalloween;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCIntervalAction;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCRepeat;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

public class Multitud implements Animacion{
	CCSprite multIzq;
	CCSprite multDer;
	CCIntervalAction accion2;
	CCIntervalAction accion3;
	public Multitud(){
		multIzq=CCSprite.sprite("01.png");
		multDer=CCSprite.sprite("01.png");
		multIzq.setScaleX(tamano.width/(800));
		multIzq.setScaleY(tamano.height/(200));
		multDer.setScaleX(tamano.width/(800));
		multDer.setScaleY(tamano.height/(200));
		
		CCAnimation animacion=CCAnimation.animation("caminar");
		animacion.addFrame("01.png");
		animacion.addFrame("02.png");
		animacion.addFrame("03.png");
		animacion.addFrame("04.png");
		animacion.addFrame("05.png");
		animacion.addFrame("06.png");
		animacion.addFrame("07.png");
		animacion.addFrame("08.png");
		accion2= CCAnimate.action(1,animacion,true);
		accion3= CCAnimate.action(1,animacion,true);
		
	}

	@Override
	public void movimiento(int i) {
		CCAction accion1;
		switch(i){
		case 1:
			if(this.multIzq.getPosition().x<10){
				accion1= CCMoveTo.action(0.7f, CGPoint.make(this.multIzq.getPosition().x+10,  40));//izquierda
				animar(i);
				this.multIzq.runAction(accion1);
			}
			
		break;
		case 2:
			if(this.multDer.getPosition().x>tamano.width-10){
				accion1= CCMoveTo.action(0.7f, CGPoint.make(this.multDer.getPosition().x-10,  40));//derecha
				animar(i);
				this.multDer.runAction(accion1);
				}
		break;
		case 3:
			//if(this.multDer.getPosition().x>tamano.width-10){
				accion1= CCMoveTo.action(0.7f, CGPoint.make(this.multDer.getPosition().x+10,  40));//derecha
				animar(i);
				this.multDer.runAction(accion1);
				//accion1= CCMoveTo.action(0.7f, CGPoint.make(this.multIzq.getPosition().x+10,  40));//izquierda
				//animar(i);
				//this.multIzq.runAction(accion1);
				//}
		break;
		case 4:
			//if(this.multIzq.getPosition().x<10){
				accion1= CCMoveTo.action(0.7f, CGPoint.make(this.multDer.getPosition().x-10,  40));//derecha
				animar(i);
				this.multDer.runAction(accion1);
				//accion1= CCMoveTo.action(0.7f, CGPoint.make(this.multIzq.getPosition().x-10,  40));//izquierda
				//animar(i);
				//this.multIzq.runAction(accion1);
				//}
		break;
		}
	}

	@Override
	public void animar(int i) {
		
		this.multDer.runAction(CCRepeat.action(accion2,1));
		this.multIzq.runAction(CCRepeat.action(accion3,1));
		
	}

	@Override
	public CCSprite getCosa() {
		return this.multDer;
	}
	public CCSprite getCosaSeg() {
		return this.multIzq;
	}

}
