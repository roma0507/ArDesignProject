package com.rom.ar.ardesign.utils;

import android.os.Bundle;

import com.rom.ar.ardesign.utils.enumerator.ModelParams;

public class Util {

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
