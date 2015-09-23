package com.rom.ar.ardesign.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.ImageStruct;
import com.rom.ar.ardesign.R;
import com.rom.ar.ardesign.service.GeometryService;
import com.rom.ar.metaio.ARViewActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Activity principal que contiene las acciones para el tracking, la superposicion de
 * materiales y texturas y la captura de imagenes.
 *
 * @author Reinel Ortiz
 * @version 12.9.2015
 */
public class ArTrackingViewActivity extends ARViewActivity {

    private MetaioSDKCallbackHandler mCallbackHandler;
    private String mTrackingConfigPath = null;
    private String mModelPath = null;
    private IGeometry mModel = null;
    private IGeometry mPerfilModel = null;
    private String mPerfilModelPath = null;
    private File mImageFile;

    private GeometryService service;

    /**
     * Método que se ejecuta al crear el Activity e instancia
     * los atributos de la clase.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (service == null)
            this.service = new GeometryService(this.getApplicationContext(), this.metaioSDK);
        this.limpiarModelo();
        mCallbackHandler = new MetaioSDKCallbackHandler();
        final Bundle b = getIntent().getExtras();
        this.mTrackingConfigPath =  b.getString("mTrackingConfigPath");
        this.mModelPath =  b.getString("mModelPath");
        this.mPerfilModelPath =  b.getString("mPerfilModelPath");
        mImageFile = new File(Environment.getExternalStorageDirectory(), "target.jpg");
    }

    /**
     * Metodo que obtiene el indicador del layout que se usara
     * para sobreponer el modelo.
     *
     * @return int indicador del layout.
     */
    @Override
    protected int getGUILayout() {
        return R.layout.ar_tracking_view_activity;
    }

    /**
     * Inicializa el controllador.
     *
     * @return controlador.
     */
    @Override
    protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
        return mCallbackHandler;
    }

    /**
     * Inicializa los componentes de la clase.
     */
    @Override
    protected void loadContents() {

        mPerfilModel = this.service.getObjModel(this.mPerfilModelPath);
        mModel = this.service.getObjModel(this.mModelPath);

        if (mModel != null)
            mModel.setCoordinateSystemID(1);

        if (mPerfilModel != null)
            mPerfilModel.setCoordinateSystemID(2);

        this.service.setTrackingConfig(this.mTrackingConfigPath);
    }

    /**
     * Metodo para gestionar el touch sobre el material o la textura.
     * @param geometry Geometry that is touched
     */
    @Override
    protected void onGeometryTouched(IGeometry geometry) {}

    /**
     * Limpia el modelo y regresa a la paleta de texturas y materiales.
     * @param v Componentes de interfaz de usuario.
     */
    public void finalizarTracking (View v) {
        this.limpiarModelo();
        finish();
    }

    /**
     * Reinicia el tracking en la pantalla.
     *
      * @param v Componentes de interfaz de usuario.
     */
    public void limpiarTrankignView (View v) {
        this.service.resetArView();
    }

    /**
     * Regresa a la bandeja de materiales y texturas.
     *
     * @param mV Componentes de interfaz de usuario.
     */
    public void goToHome(View mV) {
        Intent mIntent = new Intent(this, ArDesignActivity.class);
        startActivity(mIntent);
    }

    /**
     * Captura el evento de captura de imagen provista por metaio.
     *
     * @param v Componentes de interfaz de usuario.
     */
    public void capturar(View v) {
       metaioSDK.requestScreenshot();
    }


    /**
     * Resetea la textura o material seleccionado.
     */
    private void limpiarModelo () {
        this.mModelPath = null;
        this.mModel = null;
    }

    /**
     * Clase interna que contiene los metodos para la captura
     * de imagenes y la configuracion del directorio donde se va a almacenar.
     */
    final class MetaioSDKCallbackHandler extends IMetaioSDKCallback {

        /**
         * Busca si existe el directorio donde se van a almacenar
         * las capturas y si no existe crea el directorio.
         *
         * @return Directorio de almacenamiento de caturas.
         */
        private File getPicturesDirectory() {
            File picPath = null;
            try {
                picPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File path = new File(picPath, "Metaio_Capturas");
                boolean success = path.mkdirs() || path.isDirectory();
                if (!success) {
                    path = new File(Environment.getExternalStorageDirectory(), "Pictures");
                }
                success = path.mkdirs() || path.isDirectory();
                if (!success) {
                    path = Environment.getDataDirectory();
                }
                return path.getAbsoluteFile();
            }
            catch (Exception e) {
                return null;
            }
        }

        /**
         * Inicializa el layout.
         */
        @Override
        public void onSDKReady() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mGUIView.setVisibility(View.VISIBLE);
                }
            });
        }

        /**
         * Metodo de mataio para almacenar la imagen.
         *
         * @param filePath directorio de la captura.
         */
        @Override
        public void onCameraImageSaved(final File filePath) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (filePath.getPath().length() > 0) {
                        metaioSDK.setImage(filePath);
                    }
                }
            });
        }

        /**
         * Metodo que almacena la captura en la memoria del dispositivo.
         *
         * @param image Imagen a guardar.
         */
        @Override
        public void onScreenshotImage(ImageStruct image) {
            final File directory = getPicturesDirectory();
            if (directory == null) {
                image.release();
                image.delete();
                MetaioDebug.log(Log.ERROR, "Could not find pictures directory, not saving screenshot");
                return;
            }
            // Creating directory
            directory.mkdirs();
            try {
                // Creating file
                final File screenshotFile = new File(directory, "paredes_" + System.currentTimeMillis() + ".jpg");
                screenshotFile.createNewFile();
                FileOutputStream stream = new FileOutputStream(screenshotFile);
                boolean result = false;
                Bitmap bitmap = image.getBitmap();
                try{
                    result = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                }
                finally{
                    // release screenshot ImageStruct
                    image.release();
                    image.delete();
                    stream.close();
                }

                if (!result) {
                    MetaioDebug.log(Log.ERROR, "Error al guardar captura " + screenshotFile);
                    return;
                }
                final String url =
                        MediaStore.Images.Media.insertImage(getContentResolver(), bitmap,
                                "paredes_" + System.currentTimeMillis(), "screenshot");
                // Recycle the bitmap
                bitmap.recycle();
                bitmap = null;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String message = "La imagen ha sido agregada a la galeria.";
                        if (url == null) {
                            message = "No se puede guardar la captura";
                        }
                        else {
                            MediaScannerConnection.scanFile(getApplicationContext(),
                                    new String[]{screenshotFile.getAbsolutePath()}, new String[]{"image/jpg"},
                                    new MediaScannerConnection.OnScanCompletedListener() {
                                        @Override
                                        public void onScanCompleted(String path, Uri uri) {
                                            MetaioDebug.log("Imagen guardada en " + path);
                                        }
                                    });
                        }
                        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });
            }
            catch (IOException e) {
                MetaioDebug.printStackTrace(Log.ERROR, e);
            }
        }
    }
}
