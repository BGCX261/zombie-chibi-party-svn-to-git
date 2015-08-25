package mx.itesmfiestachibihalloween;


import org.cocos2d.layers.CCScene;

import android.os.Vibrator;

public class CreadorEscenas {
	
	public static CCScene crearEscena(Vibrator vibra,int i,int marcador){
		
		
		CCScene escena = CCScene.node();	
		
		CapaFondo capaFondo=new CapaFondo(i);//"i" determina el nivel, siendo 0 la pantalla inicial.
		escena.addChild(capaFondo);
		
		if(i>=1&&i<6){
		CapaSprite capaJuego=new CapaSprite(vibra,i,marcador);
		escena.addChild(capaJuego);
		}
		
		return escena;
	}

}
