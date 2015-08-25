package mx.itesmotros;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGSize;

public class LinkedList {
	CGSize tamano =CCDirector.sharedDirector().winSize();//dimensiones de la pantalla
	
	Node head=new Node();
	public LinkedList(){
		
		Node b=new Node();
		head.next=b;
		b.prev=head;
		b.next=head;
		head.prev=b;
		
	}
	public LinkedList(int i){
		i--;
		Node b=new Node();
		head.next=b;
		b.prev=head;
		b.next=head;
		head.prev=b;
		
		for(int j=i;j>=0;j--)
			insertNode();
	}
	public Node getFirst(){
		return this.head;
	}
	public void insertNode(){
		Node rep=this.getFirst().next;
		
		Node x=new Node();
		x.next=rep;
		this.getFirst().next=x;
		x.prev=this.getFirst();
		rep.prev=x;
	}
	public void insertNode(int i){

		while(i>0){
			insertNode();
			i--;
		}	
	}
	public void fillM(){
		
		
		Node x=new Node();
		x=head.next;
		x.info=CCSprite.sprite("Manzana_small.png");
		x.info.setScaleX(tamano.width/(230*3));
		x.info.setScaleY(tamano.width/(230*3));
		x=head.next;
		while(x!=head){
			x.info=CCSprite.sprite("Manzana_small.png");
			x.info.setScaleX(tamano.width/(230*3));
			x.info.setScaleY(tamano.width/(230*3));
			x=x.next;
		}
		head.info=null;
	}
	public void fillC(){

		
		Node x=new Node();
		x=head.next;
		x.info=CCSprite.sprite("Cerebro_small.png");
		x.info.setScaleX(tamano.width/(230*3));
		x.info.setScaleY(tamano.width/(230*3));
		x=head.next;
		while(x!=head){
			x.info=CCSprite.sprite("Cerebro_small.png");
			x.info.setScaleX(tamano.width/(230*3));
			x.info.setScaleY(tamano.width/(230*3));
			x=x.next;
		}
		head.info=null;
	}
	
	public void fillV(){

		
		Node x=new Node();
		x=head.next;
		x.info=CCSprite.sprite("IconoVida.png");
		x.info.setScaleX(tamano.width/(299*2));
		x.info.setScaleY(tamano.width/(299*2));
		x=head.next;
		while(x!=head){
			x.info=CCSprite.sprite("IconoVida.png");
			x.info.setScaleX(tamano.width/(299*2));
			x.info.setScaleY(tamano.width/(299*2));
			x=x.next;
		}
		head.info=null;
	}
	
	public void pVida(Node v,int nV){
		
		
		if (nV==0||nV==3||nV==6||nV==9){
			
			v.info=null;
			
		}
		if(nV==1||nV==4||nV==7||nV==10){
			
			v.info=CCSprite.sprite("IconoVida-2.png");
			v.info.setScaleX(tamano.width/(299*2));
			v.info.setScaleY(tamano.width/(299*2));
		}
		if(nV==2||nV==5||nV==8||nV==11){
		
			v.info=CCSprite.sprite("IconoVida-1.png");
			v.info.setScaleX(tamano.width/(299*2));
			v.info.setScaleY(tamano.width/(299*2));//mantiene cuadrados los corazones.
		}
	}
	
}
