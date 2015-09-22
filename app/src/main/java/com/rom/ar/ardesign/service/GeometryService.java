package com.rom.ar.ardesign.service;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKAndroid;
import com.metaio.tools.io.AssetsManager;

import java.io.File;

/**
 * Clase que genera el archivo de configuración y tracking de los modelos.
 *
 * @author Reinel Ortiz
 * @version 12.9.2015
 */
public class GeometryService {

    private ContextWrapper ctx;
    private IMetaioSDKAndroid metaio;

    /**
     * Constructor de la clase.
     *
     * @param ctx
     * @param metaioSDK Servicio de la libreria Metaio
     */
    public GeometryService (final Context ctx, final IMetaioSDKAndroid metaioSDK) {
        this.ctx = new ContextWrapper(ctx);
        this.metaio = metaioSDK;
    }

    /**
     * Este método contrulle el archivo de configuración para el rastreo.
     *
     * @param pathTrackingConfigFile
     * @return Si el archivo de configuración existe.
     */
    public boolean setTrackingConfig (final String pathTrackingConfigFile) {
        boolean result = false;
        try {
            final File trackingConfigFile = AssetsManager.getAssetPathAsFile(this.ctx.getApplicationContext(), pathTrackingConfigFile);
            result = this.metaio.setTrackingConfiguration(trackingConfigFile);
            MetaioDebug.log("Loaded tracking configuration "+trackingConfigFile);
        }
        catch (Exception e) {
            MetaioDebug.log(Log.ERROR, "Error loading tracking configuration: "+ pathTrackingConfigFile + " " +e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * Este método crea un objeto IGeometry con el modelo seleccionado.
     *
     * @param pathModel path del modelo seleccionado.
     * @return geometry
     */
    public IGeometry getObjModel (final String pathModel) {
        IGeometry geometry = null;
        try {

            final File modelPath = AssetsManager.getAssetPathAsFile(this.ctx.getApplicationContext(), pathModel);
            geometry = this.metaio.createGeometry(modelPath);
            MetaioDebug.log("Loaded geometry " + modelPath);
        }
        catch (Exception e) {
            MetaioDebug.log(Log.ERROR, "Error loading geometry: "+e.getMessage());
            return geometry;
        }
        return geometry;
    }

    /**
     * Este método se encarga de reinicializar la vista.
     */
    public void resetArView () {
        this.metaio.sensorCommand("reset");
    }
}
