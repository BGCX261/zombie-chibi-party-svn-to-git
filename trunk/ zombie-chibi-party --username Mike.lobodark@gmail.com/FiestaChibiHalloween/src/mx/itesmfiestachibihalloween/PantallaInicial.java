package mx.itesmfiestachibihalloween;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class PantallaInicial extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.pantalla_inicial);
		
		//Conectar con la segunda pantalla (Menú principal)
		new Handler().postDelayed(
				new Runnable() {
					
					@Override
					public void run() {
						Intent intencion = new Intent(PantallaInicial.this, PantPrincipal.class);
						PantallaInicial.this.startActivity(intencion);
						PantallaInicial.this.finish();//Elimina el activity de la pila de memoria.
					}
				}
				, 4000);//r, tiempo
	}
	

}
