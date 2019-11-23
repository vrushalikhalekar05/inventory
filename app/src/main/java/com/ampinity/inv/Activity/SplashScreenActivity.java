package com.ampinity.inv.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
/*import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;*/
import android.util.Log;
import android.widget.Toast;
import android.app.AlertDialog;
import android.Manifest;
import android.view.View;
import android.support.annotation.NonNull;
import android.content.DialogInterface;
import android.telephony.TelephonyManager;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.lang.annotation.Annotation;

import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;
import com.ampinity.inv.Utility.SharedPreferencesUtils;
import com.ampinity.inv.Utility.SharedPreferencesConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import static android.Manifest.*;
import static android.Manifest.permission.CAMERA;
public class SplashScreenActivity extends AppCompatActivity {
    ConnectionDetector cd;
    //TelephonyManager telephonyManager;
    TelephonyManager tel;
    int MY_PERMISSIONS_REQUEST_READ_CONTACTS=0;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private static final int REQUEST_PERMISSIONS = 100;
    private static final String PERMISSIONS_REQUIRED[] = new String[]{
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        pref = getApplicationContext().getSharedPreferences("MyPreference", Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferencesConstants.UserName =""+pref.getString("Userid","");
        SharedPreferencesConstants.Password =""+pref.getString("Password","");
        Toast.makeText(this, "Userid"+SharedPreferencesConstants.UserName, Toast.LENGTH_SHORT).show();
        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE);

//        if (showRationale) {
//            tel = (TelephonyManager) getSystemService(SplashScreenActivity.TELEPHONY_SERVICE);
//            SharedPreferencesUtils.addStringToSharedPreferences(SharedPreferencesConstants.KEY_SP_IMEI, tel.getDeviceId().toString(), getApplicationContext());
//            Log.e("TOKEN", SharedPreferencesUtils.getStringFromSharedPreference(SharedPreferencesConstants.KEY_SP_IMEI, getApplicationContext()));
//            Log.e("No Permission Given Old One", "No Permission Given  Old One");
//
//        } else {
//            Log.e("Permission Given", "Permission Given");
//            tel = (TelephonyManager) getSystemService(SplashScreenActivity.TELEPHONY_SERVICE);
//            SharedPreferencesUtils.addStringToSharedPreferences(SharedPreferencesConstants.KEY_SP_IMEI, tel.getDeviceId().toString(), getApplicationContext());
//            Log.e("IMEI", SharedPreferencesUtils.getStringFromSharedPreference(SharedPreferencesConstants.KEY_SP_IMEI, getApplicationContext()));
//
//
//        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            Log.e("No Permission Given New One", "No Permission Given  New One");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);

        }
        else
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            Log.e("Permission Given", "Permission Given");
            tel = (TelephonyManager) getSystemService(SplashScreenActivity.TELEPHONY_SERVICE);
           // SharedPreferencesConstants.KEY_SP_IMEI=tel.getDeviceId().toString();
           // SharedPreferencesUtils.addStringToSharedPreferences(SharedPreferencesConstants.KEY_SP_IMEI, tel.getDeviceId().toString(), getApplicationContext());
          //  Log.e("IMEI", SharedPreferencesUtils.getStringFromSharedPreference(SharedPreferencesConstants.KEY_SP_IMEI, getApplicationContext()));

        }
        cd = new ConnectionDetector(getApplicationContext());
        if (cd.isConnectingToInternet()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
//
                            /*if(SharedPreferencesConstants.Password.isEmpty()) {*/
                            Intent i = new Intent(SplashScreenActivity.this, Login.class);
                            startActivity(i);
                            finish();
                        //}
                      /*  else
                            {
                                Intent i1 = new Intent(SplashScreenActivity.this, Dashboard.class);
                                startActivity(i1);
                                finish();
                            }*/
                            //                    }
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Toast.makeText(getApplicationContext(), "please connect to internet", Toast.LENGTH_SHORT).show();
        }
    }

}
