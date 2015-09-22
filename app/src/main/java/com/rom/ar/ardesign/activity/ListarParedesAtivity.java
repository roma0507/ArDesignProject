package com.rom.ar.ardesign.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rom.ar.ardesign.R;

/**
 * Activity para la pestaña de materiales y texturas de paredes.
 *
 * @author Johan Lopez
 * @version 12.15.2015
 */
public class ListarParedesAtivity extends Fragment {

    /**
     * Método que se ejecuta al crear el Activity e instancia
     * los atributos de la clase.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Método que coloca la pestaña en el contenedor de pestañas.
     *
     * @param inflater
     * @param container Contenedor de las pestañas.
     * @param savedInstanceState
     * @return View Los Componentes de interfaz de usuario de la pestaña.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.paredes_activity, container, false);
    }
}
