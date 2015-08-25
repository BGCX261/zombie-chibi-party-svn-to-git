package mx.itesmfiestachibihalloween;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGSize;

interface Animacion {
	CGSize tamano =CCDirector.sharedDirector().winSize();//dimensiones de la pantalla
	void movimiento(int i);
	void animar(int i);
	public CCSprite getCosa();

}
