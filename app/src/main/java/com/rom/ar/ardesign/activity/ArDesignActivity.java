package com.rom.ar.ardesign.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.metaio.sdk.MetaioDebug;
import com.metaio.tools.io.AssetsManager;
import com.rom.ar.ardesign.R;
import com.rom.ar.ardesign.utils.Util;

import java.io.IOException;

public class ArDesignActivity extends AppCompatActivity {

    private Util u = null;
    AssetsExtracter mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar_design_activity);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ar_logo);

        if (u == null)
            this.u = new Util();

        mTask = new AssetsExtracter();
        mTask.execute(0);
    }

    public void newArDesign(View mV) {

        final Bundle mParams = this.u.getViewModelParams(Long.parseLong((String) mV.getTag()));
        if (mParams != null) {
            Intent mIntent = new Intent(getApplicationContext(), ArTrackingViewActivity.class);
            mIntent.putExtras(mParams);
            startActivity(mIntent);
        }else {
            //TODO
            //mostrar dialogo;
        }
    }

    public void listarModelos(View v) {
        Intent mIntent = new Intent(getApplicationContext(), TabsActivity.class);
        startActivity(mIntent);
    }

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
