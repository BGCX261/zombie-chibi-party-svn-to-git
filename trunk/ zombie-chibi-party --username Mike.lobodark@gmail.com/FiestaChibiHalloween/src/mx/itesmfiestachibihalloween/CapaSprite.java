/*
 * IMPORTANTE: 
 * -Mover control de LinkedLists a cada clase (pinata, cerebro, manzana).
 * 
 */

package mx.itesmfiestachibihalloween;

import java.util.Random;


import java.util.RandomAccess;

import javax.print.attribute.standard.Finishings;

import mx.itesmotros.LinkedList;
import mx.itesmotros.Node;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;

public class CapaSprite extends CCLayer{
	private int cerVict;
	int lvl=0;
	CGSize tamano =CCDirector.sharedDirector().winSize();//dimensiones de la pantalla
	private	Vibrator vibra;
	private CCLabel lblTexto;
	
	private int cerCaido=0;
	private Multitud multitud=new Multitud();
	private Zombie zombie;
	private Cerebro cerebro=new Cerebro();
	private Manzana manzana=new Manzana();
	private Pinata pinata=new Pinata();
	private CCSprite spriteBotton1;
	private CCSprite spriteBotton2;
	int puntaje=0;

	
	//Lista de objetos. Es mejor usar una lista encadenada.
	//ArrayList<CCSprite> lista;
	//LinkedList cerebros=new LinkedList(5);
	//LinkedList manzanas=new LinkedList(8);
	LinkedList pinatas=new LinkedList(10);
	
	protected CapaSprite(Vibrator b, int i, int marcador){
		this.puntaje=marcador;
		this.vibra = b;
		lvl=i;
		cerVict=0;
		pinatas.insertNode(i);
		//manzanas.insertNode(i);
		//cerebros.insertNode(i);
		//Crear la lista
		//lista=new ArrayList<CCSprite>();//Vacia

		zombie=new Zombie();
		spriteBotton1=CCSprite.sprite("boton_movimientoI.png");
		spriteBotton2=CCSprite.sprite("boton_movimientoD.png");
		
		//manzana.getCosa().setPosition(60, tamano.height-80);
		//cerebro.getCosa().setPosition(200, tamano.height-90);
		pinata.getCosa().setPosition(tamano.width-20,  tamano.height-60);
		zombie.getCosa().setPosition(40,40);
		addChild(zombie.getCosa());
		//addChild(manzana.getCosa());
		//addChild(cerebro.getCosa());//sustituir por el contenido del LinkedList
		
		addChild(pinata.getCosa());
		
		spriteBotton1.setPosition(40, 40);
		addChild(spriteBotton1);
		spriteBotton2.setPosition(tamano.width-40, 40);
		addChild(spriteBotton2);
		zombie.setFlipX(true);
		
		//Genera multitud
		multitud.getCosaSeg().setPosition(0-tamano.width/2, 40);
		multitud.getCosa().setPosition(tamano.width+tamano.width/2, 40);
		addChild(multitud.getCosa());
		addChild(multitud.getCosaSeg());
		
		Node v=zombie.getVida().getFirst().next;
		
		float j=tamano.width/10;
		while(v.info!=null){
			v.info.setPosition(j, tamano.height-40);
			addChild(v.info);
			v=v.next;
			j+=tamano.width/10;
		}
		
		lblTexto=CCLabel.makeLabel(" "+puntaje, "Arial", 40);
		addChild(lblTexto);
		lblTexto.setPosition(tamano.width-50, tamano.height-50);
		
	//Avisa que se quiere detectar el evento
		setIsTouchEnabled(true);
		setIsAccelerometerEnabled(true);
		if (i>=1&&i<6){
			schedule("probarChoques", 0.1f);//10veces / segundo
			schedule("bottonPressed", 0.1f);//Se presionï¿½ un botï¿½n eliminarCosas
			schedule("movimientoPerpetuo", 0.1f);
			schedule("eliminarCosas", 0.1f);
			schedule("tocaBolsa", 0.1f);
			//schedule("nextLvl",0.1f);
		//schedule("actualizarVida", 0.1f);
		
		//Prepara SONIDO Fondo/efecto
			Context contexto=CCDirector.sharedDirector().getActivity();
		
			SoundEngine.sharedEngine().preloadEffect(contexto, R.raw.manzana);
			SoundEngine.sharedEngine().preloadEffect(contexto, R.raw.cere);
			SoundEngine.sharedEngine().preloadEffect(contexto, R.raw.menu);
			SoundEngine.sharedEngine().preloadEffect(contexto, R.raw.nextstg);
		//Música fondo
			SoundEngine.sharedEngine().playSound(contexto, R.raw.fondofinal, true);//El booleano define si se cicla.
		}
	}
	
	public void CCAccelerometerChanged(float aX,float aY, float aZ){
		
		Log.d("Acelerometro","prueba");
		Log.d("Acelerometro","prueba");
		Log.d("Acelerometro","prueba");
		Log.d("Acelerometro","prueba");
		Log.d("Acelerometro","prueba");
		multitud.movimiento(3);
		if (aX>1||aY>1||aZ>1){
			multitud.movimiento(3);
		}
		if (aX<-1||aY<-1||aZ<-1){
			multitud.movimiento(4);
		}
		
	}
	
	public void probarChoques(float dt){
		float multDer=multitud.getCosa().getPosition().x-tamano.width/2;
		float multIzq=multitud.getCosaSeg().getPosition().x+tamano.width/2;
		float posZombie=zombie.getCosa().getPosition().x;
		if(posZombie>=multDer){
			zombie.movimiento(1);
			if(zombie.vidas.getFirst().next.info!=null){
				removerVida();
				zombie.perderVida();
				actualizarVida();
				
			}else{
				gameOver(1);
			}
		}
		if(posZombie<=multIzq){
			zombie.movimiento(2);
			if(zombie.vidas.getFirst().next.info!=null){
				removerVida();
				zombie.perderVida();
				actualizarVida();
				
			}else{
				gameOver(2);
			}
		}
	}
	public void nextLvl(){
		if(cerVict>=lvl*5){
			/*
			removeAllChildren(true);
			CCSprite imagenFondo = CCSprite.sprite("victoria.jpg");// Cambiar por imagen de victoria
			tamano = CCDirector.sharedDirector().winSize();
			imagenFondo.setPosition(tamano.width / 2, tamano.height / 2);
			imagenFondo.setScaleX(tamano.width/700);
			imagenFondo.setScaleY(tamano.height/400);
			addChild(imagenFondo);
			//Colocar espera de 10 seg.
			*/
			CCDirector.sharedDirector().pause();
			removeAllChildren(true);
			CCScene escena = CreadorEscenas.crearEscena(vibra,lvl+1,puntaje);// el número entero
			// indica el nivel
			CCDirector.sharedDirector().runWithScene(escena);
			
		}
	}
	
	public void movimientoPerpetuo(float dt){
		Node m,c;
		m=manzana.getList().getFirst().next;
		c=cerebro.getList().getFirst().next;
		pinata.movimiento(0);
		
		while(m.info!=null){
			if(m.info.getPosition().y<=-5)
				removeChild(m.info, true);
			m=m.next;
		}
		while(c.info!=null){
			if(c.info.getPosition().y<=-5){
				c.info.setPosition(0, -1);
				removeChild(c.info, true);
				cerCaido++;
			}
			c=c.next;
		}
		m=m.next;
		c=c.next;
		//rebote
		while(m.info!=null){
			if(m.info.getPosition().x<=1)
				manzana.movimiento(randInt(1,10), m.info);
			
			if(m.info.getPosition().x>=tamano.width)
				manzana.movimiento(randInt(-10,-1), m.info);
			m=m.next;
		}
		while(c.info!=null){
			if(c.info.getPosition().x>=tamano.width)
				cerebro.movimiento(randInt(-10,-1), c.info);
			
			if(c.info.getPosition().x<=1)
				cerebro.movimiento(randInt(1,10), c.info);
			c=c.next;
		}
		if(cerCaido>=11)
			cerCaido=0;
		if(cerCaido==10){
			multitud.movimiento(1);
			multitud.movimiento(2);
			cerCaido=0;
		}
		
		
	}
	public void movimiento(){
		Node m,c;
		m=manzana.getList().getFirst().next;
		c=cerebro.getList().getFirst().next;
			
		while(m.info!=null){
			if(m.info.getPosition().y>=tamano.height-65)
			manzana.movimiento(randInt(-10,10), m.info);
			if(m.info.getPosition().y<=1)
				removeChild(m.info, true);
			m=m.next;
			Log.d("manzana","la manzana se mueve");
		}
		while(c.info!=null){
			if(c.info.getPosition().y>=tamano.height-65)
			cerebro.movimiento(randInt(-10,10), c.info);
			if(c.info.getPosition().y<=1)
				removeChild(c.info, true);
			c=c.next;
			Log.d("cerebro","el cerebro se mueve");
		}
		

	
	}
	
	public void tocaBolsa(float dt){
		Context contexto =CCDirector.sharedDirector().getActivity();
		
		Node m,c;
		m=manzana.getList().getFirst().next;
		c=cerebro.getList().getFirst().next;
		float mY=0;
		float mX=0;
		float cY=0;
		float cX=0;
		float bX=zombie.getBolsa().x;
		float bY=zombie.getBolsa().y;
		
		
		while(m.info!=null){
			mX=m.info.getPosition().x;
			mY=m.info.getPosition().y;
			
			if((mX-bX)*(mX-bX)+(mY-bY)*(mY-bY)<800){
				m.info.setPosition(-150,-150);
				m.info.setPosition(-150,-150);
				removeChild(m.info, true);
				puntaje-=50;
				
				if(zombie.vidas.getFirst().next.info!=null){
					removerVida();
					zombie.perderVida();
					actualizarVida();
					
				}else{
					gameOver(1);
				}
				Log.d("manzana","manzana atrapada");
				SoundEngine.sharedEngine().playEffect(contexto, R.raw.manzana);
				vibra.vibrate(300);
			}
			m=m.next;
			
			
		}
		while(c.info!=null){
			cX=c.info.getPosition().x;
			cY=c.info.getPosition().y;
			if((cX-bX)*(cX-bX)+(cY-bY)*(cY-bY)<800){
				c.info.setPosition(-150,-150);
				c.info.setPosition(-150,-150);
				removeChild(c.info, true);
				puntaje+=100;
				Log.d("cerebro","cerebro atrapado");
				cerVict++;
				SoundEngine.sharedEngine().playEffect(contexto, R.raw.cere);
				nextLvl();
			}
			c=c.next;
			
			
		}
		removeChild(lblTexto, true);
		lblTexto=CCLabel.makeLabel(" "+puntaje, "Arial", 40);
		addChild(lblTexto);
		lblTexto.setPosition(tamano.width-50, tamano.height-50);
		
	}
	public void removerVida(){
		Node v=zombie.getVida().getFirst().next;
		
		if(v.info!=null)
			while(v.info!=null){
			
				removeChild(v.info, true);
				v=v.next;
			
			}
	}
	
	public void actualizarVida(){
		Node v=zombie.getVida().getFirst().next;
		
		float j=tamano.width/10;
		
		while(v.info!=null){
			v.info.setPosition(j, tamano.height-40);
			addChild(v.info);
			v=v.next;
			j+=tamano.width/10;
		}
		v=v.next;
		
	}
	
	public void gameOver(int i){// ¿?
		SoundEngine.sharedEngine().realesAllEffects();
		SoundEngine.sharedEngine().realesAllSounds();
		SoundEngine.purgeSharedEngine();
		removeAllChildren(true);
		CCSprite imagenFondo;
		switch (i){
			case 1:
				imagenFondo = CCSprite.sprite("derrota.png");// Cambiar por imagen de GameOver
				tamano = CCDirector.sharedDirector().winSize();
				imagenFondo.setPosition(tamano.width / 2, tamano.height / 2);
				imagenFondo.setScale(0.5f);
				addChild(imagenFondo);
			break;
			case 2:
				imagenFondo = CCSprite.sprite("derrota2.png");// Cambiar por imagen de GameOver
				tamano = CCDirector.sharedDirector().winSize();
				imagenFondo.setPosition(tamano.width / 2, tamano.height / 2);
				imagenFondo.setScale(0.5f);
				addChild(imagenFondo);
			break;
		}
	}
	
	public void eliminarCosas(){
		if(manzana.getCosa().getPosition().y<=10)
			removeChild(pinata.getCosa(), true);
		if(cerebro.getCosa().getPosition().y<=10)
			removeChild(pinata.getCosa(), true);
		
	}

	
	public boolean ccTouchesEnded(MotionEvent event){
		//Comienza movimiento de zombie.
		int i=0;
		float xb1=40;
		float yb1=40;
		float xb2=tamano.width-40;
		float yb2=40;
		float xe=event.getX();
		float ye=event.getY();
	
		CGPoint pos =CCDirector.sharedDirector().convertToGL(CGPoint.ccp(xe, ye));
		//xe=pos.x;
		ye=pos.y;
		
		if((xb1-xe)*(xb1-xe)+(yb1-ye)*(yb1-ye)<400){
			CCDirector.sharedDirector().resume();
			Log.d("izquierdo","botton presionado");
			i=1;
			zombie.movimiento(i);
			
			return true;
		}else if((xb2-xe)*(xb2-xe)+(yb2-ye)*(yb2-ye)<400){
			CCDirector.sharedDirector().resume();
			Log.d("derecho","botton presionado");
			i=2;
			zombie.movimiento(i);
			
			return true;
		}
		
		//termina movimiento
		//Comienza explosion de pinata
		xb1=this.pinata.getPosition().x;
		yb1=this.pinata.getPosition().y;
		
		if((xb1-xe)*(xb1-xe)+(yb1-ye)*(yb1-ye)<82944 && ye>tamano.height-tamano.height/5){
			Log.d("pinata","liberando cosas");
			
			


			
			Node m,c;
			m=manzana.getList().getFirst().next;
			c=cerebro.getList().getFirst().next;
			
			
			while(m.info!=null){
				
				if(m.info.getPosition().y<5){
				m.info.setPosition(pinata.getCosa().getPosition());
				addChild(m.info);
				}
				
				m=m.next;
				
			}
			int j=0;
			while(c.info!=null){
				j++;
				if(c.info.getPosition().y<5){
				c.info.setPosition(pinata.getCosa().getPosition().x+j,pinata.getCosa().getPosition().y);
				addChild(c.info);
				}
				c=c.next;
				
				
				
			}
			movimiento();
			
			return true;
		}
		//Termina explosion de pinata
		
		//¿Botón pausa/menú?
		return true;
	}
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	

}
