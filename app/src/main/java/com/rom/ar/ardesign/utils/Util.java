package com.rom.ar.ardesign.utils;

import android.os.Bundle;

import com.rom.ar.ardesign.utils.enumerator.ModelParams;

/**
 * Clase que contiene las utilidades de la aplicación.
 *
 * @author Reinel Ortiz
 * @version 12.9.2015
 *
 */
public class Util {

    /**
     * Método que obtiene un modelo a partir del id del mismo.
     *
     * @param id id del modelo.
     * @return modelo y configuracion de tracking.
     */
    public Bundle getViewModelParams (final Long id){

        final ModelParams mModel = ModelParams.fromId(id);

        if (mModel != null) {
            Bundle b = new Bundle();
            b.putString("mTrackingConfigPath", mModel.getStrTrackingConfig());
            b.putString("mModelPath", mModel.getStrModelPath());
            b.putString("mPerfilModelPath", mModel.getStrPerfilPath());
            return b;
        }
        return null;
    }
}
