package mx.itesmfiestachibihalloween;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGSize;

public class CapaFondo extends CCLayer {
	private CCSprite imagenFondo;

	protected CapaFondo() {
		imagenFondo = CCSprite.sprite("portada.jpg");// Imagen de fondo inicial.
														// Deben tener cuidado
														// con mayúsculas.
		CGSize tamano = CCDirector.sharedDirector().winSize();
		imagenFondo.setPosition(tamano.width / 2, tamano.height / 2);
		addChild(imagenFondo);
	}

	protected CapaFondo(int i) {
		CGSize tamano;
		switch (i) {
		case 0:
			imagenFondo = CCSprite.sprite("fondo.jpg");// Imagen de fondo
															// primer nivel.
															// Deben tener
															// cuidado con
															// mayúsculas.
			tamano = CCDirector.sharedDirector().winSize();
			imagenFondo.setPosition(tamano.width / 2, tamano.height / 2);
			break;
		case 1:
			imagenFondo = CCSprite.sprite("fondo.jpg");// Imagen de fondo
													// inicial. Deben tener
														// cuidado con
														// mayúsculas.
			tamano = CCDirector.sharedDirector().winSize();
			imagenFondo.setScaleX(tamano.width/800);
			imagenFondo.setScaleY(tamano.height/480);//Dividir el tamaño de la pantalla entre el tamaño de la imagen.800x480
			imagenFondo.setPosition(tamano.width / 2, tamano.height / 2);
			break;
		case 2:
			imagenFondo = CCSprite.sprite("market.jpg");// Imagen de fondo
														// inicial. Deben tener
														// cuidado con
														// mayúsculas.
			tamano = CCDirector.sharedDirector().winSize();
			imagenFondo.setScaleX(tamano.width/800);
			imagenFondo.setScaleY(tamano.height/480);
			imagenFondo.setPosition(tamano.width / 2, tamano.height / 2);
			break;
		case 3:
			imagenFondo = CCSprite.sprite("hospital.jpg");// Imagen de fondo
														// inicial. Deben tener
														// cuidado con
														// mayúsculas.
			tamano = CCDirector.sharedDirector().winSize();
			imagenFondo.setScaleX(tamano.width/800);
			imagenFondo.setScaleY(tamano.height/480);
			imagenFondo.setPosition(tamano.width / 2, tamano.height / 2);
			break;
		case 4:
			imagenFondo = CCSprite.sprite("estadio.jpg");// Imagen de fondo
														// inicial. Deben tener
														// cuidado con
														// mayúsculas.
			tamano = CCDirector.sharedDirector().winSize();
			imagenFondo.setScaleX(tamano.width/800);
			imagenFondo.setScaleY(tamano.height/480);
			imagenFondo.setPosition(tamano.width / 2, tamano.height / 2);
			break;
		case 5:
			imagenFondo = CCSprite.sprite("planta.jpg");// Imagen de fondo
														// inicial. Deben tener
														// cuidado con
														// mayúsculas.
			tamano = CCDirector.sharedDirector().winSize();
			imagenFondo.setScaleX(tamano.width/800);
			imagenFondo.setScaleY(tamano.height/480);
			imagenFondo.setPosition(tamano.width / 2, tamano.height / 2);
			break;
		case 6:
			imagenFondo = CCSprite.sprite("victoria.jpg");// Imagen de fondo
														// inicial. Deben tener
														// cuidado con
														// mayúsculas.
			tamano = CCDirector.sharedDirector().winSize();
			imagenFondo.setScaleX(tamano.width/800);
			imagenFondo.setScaleY(tamano.height/480);
			imagenFondo.setPosition(tamano.width / 2, tamano.height / 2);
			break;
		}
		addChild(imagenFondo);

	}

}