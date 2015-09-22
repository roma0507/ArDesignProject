package com.rom.ar.ardesign.activity;

import android.app.Activity;
import android.os.Bundle;

import com.rom.ar.ardesign.R;

/**
 * Activity para la ventana de En construccion.
 *
 * @author Johan Lopez
 * @version 12.15.2015
 */
public class ConstruccionActivity extends Activity{

    /**
     * Método que se ejecuta al crear el Activity e instancia
     * los atributos de la clase.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.under_construccion);
    }
}
