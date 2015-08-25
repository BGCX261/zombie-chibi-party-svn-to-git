package mx.itesmfiestachibihalloween;
import mx.itesmotros.LinkedList;
import mx.itesmotros.Node;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCIntervalAction;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCRepeat;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

public class Zombie extends CCSprite implements Animacion{//Añadir punto para la bolsa de captura.
	private CCSprite personaje;
	private String nombre="Camina1.png";
	private int voltea=1, nVidas=12;
	private CGPoint bolsa;
	LinkedList vidas=new LinkedList(3);
	CCAnimation animacion;
	CCIntervalAction accion2;
	
	public Zombie(){//Javi por default
		nombre="Javi"+nombre;
		personaje=CCSprite.sprite(nombre);
		personaje.setScaleX(tamano.width/(190*3));
		personaje.setScaleY(tamano.height/(109*3));
		bolsa=CGPoint.ccp(personaje.getPosition().x+40, personaje.getPosition().y);
		vidas.fillV();
		
		animacion=CCAnimation.animation("caminar");
		animacion.addFrame("JaviCamina1.png");
		animacion.addFrame("JaviCamina2.png");
		animacion.addFrame("JaviCamina3.png");
		animacion.addFrame("JaviCamina4.png");
		animacion.addFrame("JaviCamina5.png");
		animacion.addFrame("JaviCamina6.png");
		animacion.addFrame("JaviCamina7.png");
		animacion.addFrame("JaviCamina8.png");
		animacion.addFrame("JaviCamina9.png");
		accion2= CCAnimate.action(1,animacion,true);
	}
	
	public Zombie(int i){//Para seleccion de personajes
		switch(i){
		case 1:
			nombre="Javi"+nombre;
		break;
		case 2:
			nombre="Gabi"+nombre;
		break;
		default:
			nombre="Javi"+nombre;
		break;
		}
		personaje=CCSprite.sprite(nombre);
	}
	public CCSprite getCosa(){
		return this.personaje;
	}
	
	public CGPoint getBolsa(){
		return this.bolsa;
	}
	
	public LinkedList getVida(){
		return this.vidas;
	}
	
	public void perderVida(){
		Node v=vidas.getFirst().prev;
		int i=0;
		nVidas--;
		while(v.info==null||i<10){
			if(v.info==null){
				v=v.prev;
			}
			i++;
		}
		
		vidas.pVida(v,nVidas);
		
	}
	
	@Override
	public void movimiento(int i){
		CCAction accion1;
		switch(i){
		case 1:
			if(this.voltea==1){
				this.personaje.setFlipX(!this.personaje.flipX_);
				voltea--;
				
			}
			if(this.personaje.getPosition().x>30){
				accion1= CCMoveTo.action(0.7f, CGPoint.make(this.personaje.getPosition().x-40,  40));//izquierda
				animar(i);
				this.personaje.runAction(accion1);
			}
			bolsa=CGPoint.ccp(personaje.getPosition().x-60, personaje.getPosition().y);
		break;
		case 2:
			if(this.voltea!=1){
				this.personaje.setFlipX(!this.personaje.flipX_);
				voltea++;
				
			}
			if(this.personaje.getPosition().x<tamano.width-20){
				accion1= CCMoveTo.action(0.7f, CGPoint.make(this.personaje.getPosition().x+40,  40));//derecha
				animar(i);
				this.personaje.runAction(accion1);
				}
			bolsa=CGPoint.ccp(personaje.getPosition().x+60, personaje.getPosition().y);
		break;
		case 0:
 

		}
	}

	@Override
	public void animar(int i) {
		
		switch(i){
		case 1:
			if(this.voltea==1){
				this.personaje.setFlipX(!this.personaje.flipX_);
				voltea--;
			}
			if(this.personaje.getPosition().x>20){
				this.personaje.runAction(CCRepeat.action(accion2,1));
			}
		break;
		case 2:
			if(this.voltea!=1){
				this.personaje.setFlipX(!this.personaje.flipX_);
				voltea++;
			}
			if(this.personaje.getPosition().x<tamano.width-20){
				this.personaje.runAction(CCRepeat.action(accion2,1));
				}
		break;
		case 0:
 

		}
		
	}

}
