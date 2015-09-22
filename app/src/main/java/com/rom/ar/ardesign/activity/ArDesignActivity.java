package com.rom.ar.ardesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.rom.ar.ardesign.R;

/**
 * Activity principal que contiene las acciones para redireccionar a la galeria
 * y a la paleta de selección de materiales y texturas.
 *
 * @author Reinel Ortiz
 * @version 12.9.2015
 */
public class ArDesignActivity extends AppCompatActivity  {

    /**
     * Método que se ejecuta al crear el Activity e instancia
     * los atributos de la clase.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar_design_activity);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ar_logo);
        ImageButton scanBtn = (ImageButton)findViewById(R.id.imageButtonGaleria);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Método que abre la galeria de imagenes.
             *
             * @param v Componentes de interfaz de usuario.
             */
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_VIEW,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/png");
                // Start the Intent
                startActivity(galleryIntent);
            }
        });
    }

    /**
     * Método que abre la paleta de selección de materiales y texturas.
     *
     * @param v Componentes de interfaz de usuario.
     */
    public void listarModelos(View v) {
        Intent mIntent = new Intent(getApplicationContext(), TabsActivity.class);
        startActivity(mIntent);
    }

  }
