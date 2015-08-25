package mx.itesmfiestachibihalloween;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;//librerias de cocos2D
import org.cocos2d.sound.SoundEngine;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class FCH extends Activity {
	private CCGLSurfaceView glSurfaceView;
	CCScene escena;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
		// WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		glSurfaceView = new CCGLSurfaceView(this);// se crea SView
		setContentView(glSurfaceView);// se pone como vista de la actividad

		CCDirector.sharedDirector().attachInView(glSurfaceView);
		CCDirector.sharedDirector().setDeviceOrientation(
				CCDirector.kCCDeviceOrientationLandscapeLeft);
		//CCDirector.sharedDirector().setDisplayFPS(true);
		CCDirector.sharedDirector().setAnimationInterval(1.0f / 40);
		// herramientas para
		Vibrator vibra=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		escena = CreadorEscenas.crearEscena(vibra,1,0);// el número entero
														// indica el nivel
		CCDirector.sharedDirector().runWithScene(escena);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		onPause();
		switch (item.getItemId()) {
		case R.id.itemSalir:
			finish();// Salimos de la actividad
			break;
		case R.id.itemCred:
			Intent intencion = new Intent(this, Cred.class);
			
			startActivity(intencion);
			break;

		}

		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		SoundEngine.sharedEngine().resumeSound();
		
		CCDirector.sharedDirector().resume();
		
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		SoundEngine.sharedEngine().pauseSound();
		
		CCDirector.sharedDirector().pause();
	}
	@Override
	protected void onStop() {
		onPause();
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		SoundEngine.sharedEngine().realesAllEffects();
		SoundEngine.sharedEngine().realesAllSounds();
		SoundEngine.purgeSharedEngine();
		super.onDestroy();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menup, menu);
		
		return true;
	}

}
