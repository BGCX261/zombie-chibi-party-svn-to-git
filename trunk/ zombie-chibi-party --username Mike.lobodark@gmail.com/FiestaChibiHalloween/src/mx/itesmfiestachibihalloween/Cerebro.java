package mx.itesmfiestachibihalloween;

import java.util.Random;
import mx.itesmotros.LinkedList;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.interval.CCJumpTo;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

public class Cerebro extends CCSprite implements Animacion{
	CCSprite cerebro;
	LinkedList cerebros=new LinkedList(1);
	
	public Cerebro(){
		cerebros.insertNode(3);
		cerebro=CCSprite.sprite("Cerebro_small.png");
		cerebros.fillC();
		this.movimiento(randInt(1, 6));
	}
	
	@Override
	public void movimiento(int i) {
		CCAction accion1;
		CCAction accion2;
		
			//accion1= CCMoveTo.action(.5f, CGPoint.make(this.cerebro.getPosition().x+2+i,  0));//movimiento horizontal
			accion2= CCJumpTo.action(1f, CGPoint.ccp(this.cerebro.getPosition().x+6*i,  -7),150,3);//movimiento vertical
			animar(i);
			//this.cerebro.runAction(accion1);
			this.cerebro.runAction(accion2);
		
		
	
		
	}
	public void movimiento(int i,CCSprite c) {
			int apoyo=1;
			if (i<0)
				apoyo=-1;
			if(i==0)
				i=1;
			CCAction salto=CCJumpTo.action(apoyo*i/3, CGPoint.make(c.getPosition().x+i*15, -7), 0, 1);
			c.runAction(salto);
			return;
		/*
		CCAction accion1;
		CCAction accion2;
		//move by o jump to
			accion1= CCMoveTo.action(.5f, CGPoint.make(c.getPosition().x+2+i,  0));//movimiento horizontal
			accion2= CCMoveTo.action(.1f, CGPoint.make(c.getPosition().x+2+i,  c.getPosition().y-4));//movimiento vertical
			animar(i);
			c.runAction(accion1);
			c.runAction(accion2);
		*/
		
	
		
	}

	@Override
	public void animar(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CCSprite getCosa() {
		return this.cerebro;
	}
	public LinkedList getList() {
		return this.cerebros;
	}
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
}
