package com.example.root.google.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.google.R;
import com.facebook.drawee.backends.pipeline.Fresco;

public class BaseActivity extends AppCompatActivity {

    /*
    * @Author
    * @Set array of permissions
    *
    * */
    private static final String[] permissions = new String[]{
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.READ_CALENDAR ,
            Manifest.permission.WRITE_CALENDAR ,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
    }

    @Override
    public void onBackPressed() {
        BaseActivity.super.onBackPressed();
        overridePendingTransition(0,0);
//        new AlertDialog.Builder(this)
//                .setTitle("Back?")
//                .setNegativeButton(android.R.string.no, null)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        BaseActivity.super.onBackPressed();
//                        overridePendingTransition(0,0);
//                    }
//                }).create().show();

    }

}
