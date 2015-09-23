package com.rom.ar.ardesign.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.metaio.sdk.MetaioDebug;
import com.metaio.tools.io.AssetsManager;
import com.rom.ar.ardesign.R;

import java.io.IOException;

/**
 * Activity principal que contiene las acciones para redireccionar a la galeria
 * y a la paleta de selección de materiales y texturas.
 *
 * @author Reinel Ortiz
 * @version 12.9.2015
 */
public class ArDesignActivity extends AppCompatActivity  {


    AssetsExtracter mTask;


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
        mTask = new AssetsExtracter();
        mTask.execute(0);
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

    /**
     * Clase interna que contiene el metodo para cargar los elementos de la carpeta Assets.
     */
    private class AssetsExtracter extends AsyncTask<Integer, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Integer... params) {
            try {
                AssetsManager.extractAllAssets(getApplicationContext(), true);
            } catch (IOException e) {
                MetaioDebug.printStackTrace(Log.ERROR, e);
                return false;
            }
            return true;
        }
    }

  }
