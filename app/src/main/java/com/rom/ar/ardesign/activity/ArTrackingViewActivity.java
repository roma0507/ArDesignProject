package com.rom.ar.ardesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.rom.ar.ardesign.R;
import com.rom.ar.ardesign.service.GeometryService;
import com.rom.ar.metaio.ARViewActivity;

public class ArTrackingViewActivity extends ARViewActivity {

    private String mTrackingConfigPath = null;
    private String mModelPath = null;
    private IGeometry mModel = null;
    private IGeometry mPerfilModel = null;
    private String mPerfilModelPath = null;

    private GeometryService service;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (service == null)
            this.service = new GeometryService(this.getApplicationContext(), this.metaioSDK);

        final Bundle b = getIntent().getExtras();
        this.mTrackingConfigPath =  b.getString("mTrackingConfigPath");
        this.mModelPath =  b.getString("mModelPath");
        this.mPerfilModelPath =  b.getString("mPerfilModelPath");
    }

    @Override
    protected int getGUILayout() {
        return R.layout.ar_tracking_view_activity;
    }

    @Override
    protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
        return null;
    }

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

    @Override
    protected void onGeometryTouched(IGeometry geometry) {}

    public void onButtonClick(View v) {
        finish();
    }

    public void onResetButtonClick(View v) {
        this.service.resetArView();
    }

    public void goToHome(View mV) {
        Intent mIntent = new Intent(this, ArDesignActivity.class);
        startActivity(mIntent);
    }
}
