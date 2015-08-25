package mx.itesmfiestachibihalloween;

import java.util.Random;

import mx.itesmotros.LinkedList;
import mx.itesmotros.Node;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.interval.CCJumpTo;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

public class Manzana extends CCSprite implements Animacion{
	CCSprite manzana;
	LinkedList manzanas=new LinkedList(1);
	
	Node m;
	public Manzana(){
		manzanas.insertNode(3);
		manzana=CCSprite.sprite("Manzana_small.png");
		manzanas.fillM();
		m=manzanas.getFirst().next;
		this.movimiento(randInt(1, 6));
		

	}
	
	@Override
	public void movimiento(int i) {
		//CCAction accion1;
		CCAction accion2;
		
		//accion1= CCMoveTo.action(.5f, CGPoint.make(this.cerebro.getPosition().x+2+i,  0));//movimiento horizontal
		accion2= CCJumpTo.action(1f, CGPoint.ccp(this.manzana.getPosition().x+6*i,  -7),150,3);//movimiento vertical
		animar(i);
		//this.cerebro.runAction(accion1);
		this.manzana.runAction(accion2);
		
		/*
		if(this.manzana.getPosition().x>0){
			accion1= CCMoveTo.action(.5f, CGPoint.make(this.manzana.getPosition().x-2,  0));//movimiento horizontal
			accion2= CCMoveTo.action(.1f, CGPoint.make(this.manzana.getPosition().x-2,  this.manzana.getPosition().y-4));//movimiento vertical
			animar(i);
			this.manzana.runAction(accion1);
			this.manzana.runAction(accion2);
		}else{
			accion1= CCMoveTo.action(.5f, CGPoint.make(this.manzana.getPosition().x+2,  0));//movimiento horizontal
			accion2= CCMoveTo.action(.1f, CGPoint.make(this.manzana.getPosition().x+2,  this.manzana.getPosition().y-4));//movimiento vertical
			animar(i);
			this.manzana.runAction(accion1);
			this.manzana.runAction(accion2);
		}
		*/
	}

	@Override
	public void animar(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CCSprite getCosa() {
		
		return this.manzana;
	}
	
	public LinkedList getList() {
		return this.manzanas;
	}
	
	
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	public void movimiento(int i, CCSprite m) {
		int apoyo=1;
		if (i<0)
			apoyo=-1;
		if(i==0)
			i=1;
		CCAction salto=CCJumpTo.action(apoyo*i/3, CGPoint.make(m.getPosition().x+i*15, -7), 0, 1);
		m.runAction(salto);
		return;
		
		/*
		CCAction accion1;
		CCAction accion2;
		//move by o jump to
			accion1= CCMoveTo.action(.5f, CGPoint.make(m.getPosition().x+2+i,  0));//movimiento horizontal
			accion2= CCMoveTo.action(.1f, CGPoint.make(m.getPosition().x+2+i,  this.manzana.getPosition().y-4));//movimiento vertical
			animar(i);
			m.runAction(accion1);
			m.runAction(accion2);
		*/
		
	
		
	}
	

}
