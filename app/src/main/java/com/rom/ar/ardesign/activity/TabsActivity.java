package com.rom.ar.ardesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.View;

import com.rom.ar.ardesign.R;
import com.rom.ar.ardesign.utils.Util;

/**
 * Activity que contiene las tabs de paredes, pisos y techos.
 *
 * @author Johan Lopez
 * @version 12.16.2015
 */
public class TabsActivity extends FragmentActivity {

    private FragmentTabHost tabHost;
    private Util u = null;

    /**
     * Método que se ejecuta al crear el Activity e instancia
     * los atributos de la clase y las pestañas de la interfaz gráfica.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.models_tabs);
        if (u == null)
            this.u = new Util();
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this,
                getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Paredes"),
                ListarParedesAtivity.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Pisos"),
                ListarPisosAtivity.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Techos"),
                ListarTechosAtivity.class, null);

    }

    /**
     * Metodo que abre la pantalla de en Construcción.
     *
     * @param view Componentes de interfaz de usuario.
     */
    public void onConstruccion(View view) {
        Intent mIntent = new Intent(getApplicationContext(), ConstruccionActivity.class);
        startActivity(mIntent);
    }

    /**
     * Método que lanza el modelo o textura que se selecciona en una de las pestañas.
     *
     * @param mV Componentes de interfaz de usuario.
     */
    public void newArDesign(View mV) {
        final Bundle mParams = this.u.getViewModelParams(Long.parseLong((String) mV.getTag()));
        if (mParams != null) {
            Intent mIntent = new Intent(getApplicationContext(), ArTrackingViewActivity.class);
            mIntent.putExtras(mParams);
            startActivity(mIntent);
        }else {
            Log.d("newArDesign ", "Parametros para crear el modelo nullos");
        }
    }

}
