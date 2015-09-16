package com.rom.ar.ardesign.service;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKAndroid;
import com.metaio.tools.io.AssetsManager;

import java.io.File;

public class GeometryService {

    private ContextWrapper ctx;
    private IMetaioSDKAndroid metaio;

    public GeometryService (final Context ctx, final IMetaioSDKAndroid metaioSDK) {
        this.ctx = new ContextWrapper(ctx);
        this.metaio = metaioSDK;
    }

    /**
     * Este método contrulle el archivo de configuración para el rastreo.
     * @param pathTrackingConfigFile
     * @return result
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
     * Este método crea un objeto IGeometry.
     * @param pathModel
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
