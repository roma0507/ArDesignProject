package com.rom.ar.ardesign.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.metaio.sdk.MetaioDebug;
import com.metaio.tools.io.AssetsManager;
import com.rom.ar.ardesign.R;
import com.rom.ar.ardesign.utils.Util;

import java.io.IOException;

public class ArDesignActivity extends AppCompatActivity  {

    private Util u = null;
    AssetsExtracter mTask;


    private String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar_design_activity);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ar_logo);

        //File folder = new File(Environment.getExternalStorageDirectory().getPath()+"/Pictures/Metaio_Capturas/");
        //allFiles = folder.listFiles();

        //SCAN_PATH=Environment.getExternalStorageDirectory().getPath().toString()+"/Pictures/Metaio_Capturas/";
        Button scanBtn = (Button)findViewById(R.id.misDiseños);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_VIEW,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/png");
                // Start the Intent
                startActivity(galleryIntent);
            }
        });

        if (u == null)
            this.u = new Util();

        mTask = new AssetsExtracter();
        mTask.execute(0);
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
