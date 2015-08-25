package mx.itesmfiestachibihalloween;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

public class Pinata extends CCSprite implements Animacion{
	CCSprite pinata;
	
	public Pinata(){
	pinata=CCSprite.sprite("Pinata_small.png");	
	pinata.setScaleX(tamano.width/(230*3));
	pinata.setScaleY(tamano.height/(230*3));
	}
	
	@Override
	public void movimiento(int i){
		CCAction accion1;
		
			if(this.pinata.getPosition().x==30){
				accion1= CCMoveTo.action(1f, CGPoint.make(tamano.width-20,  tamano.height-60));//izquierda
				animar(i);
				this.pinata.runAction(accion1);
			}else if(this.pinata.getPosition().x==tamano.width-20){
				accion1= CCMoveTo.action(1f, CGPoint.make(30,  tamano.height-60));//derecha
				animar(i);
				this.pinata.runAction(accion1);
			
		}
		
	}

	@Override
	public void animar(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CCSprite getCosa() {
		return this.pinata;
	}

}
