package com.ampinity.inv.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
/*import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;*/
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.Utility.SharedPreferencesConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Otp extends AppCompatActivity {
    String jsonResponce;
    SoapPrimitive result1;
    EditText otpverify;
    TextView mobilenumber;
    TelephonyManager tel;
    Button btnSubmit,btnCancel;
    String otp, imei;
    String UserName,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        otpverify = (EditText) findViewById(R.id.otpverify);
        //mobilenumber = (TextView) findViewById(R.id.mobilenumber);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnCancel= (Button) findViewById(R.id.btnCancel);
        tel = (TelephonyManager) getSystemService(SplashScreenActivity.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        imei = tel.getDeviceId().toString();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otp = otpverify.getText().toString().trim();
                if (otp.equals("")) {
                    Toast.makeText(Otp.this, "Enter Otp", Toast.LENGTH_SHORT).show();
                } else {

                    new VerifyOtp().execute();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,Login.class);
        startActivity(i);
        finish();
    }
    public class VerifyOtp extends AsyncTask<String, Integer, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Otp.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);

        }

        @Override
        protected String doInBackground(String... args) {

            String url = Config.Url+"Loginpage.asmx";
            String soapactionaddlob = "http://tempuri.org/verify_OTP";
            String methodname = "verify_OTP";
            String namespacesaalob = "http://tempuri.org/";

            SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
            requestAddlob.addProperty("mobile_no", SharedPreferencesConstants.mobilenum);
            requestAddlob.addProperty("OTP", otp);
            requestAddlob.addProperty("IMEINO",imei);

            SharedPreferencesConstants.KEY_SP_IMEI=imei;

           /*
            requestAddlob.addProperty("Address",stridEditTextAddress);
            requestAddlob.addProperty("City",selcetedspinnerCity);
            requestAddlob.addProperty("State",selcetedspinnerState);
            requestAddlob.addProperty("Email",stridEditTextEmail);
            requestAddlob.addProperty("PhoneNo",stridEditTextPhoneNo);
            requestAddlob.addProperty("GSTINNumber","212121");*/

//                requestAddlob.addProperty("Password", Pass);



            // adding method property here serially

            SoapSerializationEnvelope envelopeAddlob = new SoapSerializationEnvelope(SoapEnvelope.VER11);


            envelopeAddlob.encodingStyle = "utf-8";
            envelopeAddlob.implicitTypes = true;
            envelopeAddlob.setOutputSoapObject(requestAddlob);
            envelopeAddlob.dotNet = true;
            result1=null;

            try {
                HttpTransportSE httpTransportAddlob = new HttpTransportSE(url);
                httpTransportAddlob.debug = true;
                httpTransportAddlob.call(soapactionaddlob, envelopeAddlob);
                result1 = (SoapPrimitive) envelopeAddlob.getResponse();
                Log.i("RESPONSE after add", String.valueOf(result1));
                //  Toast.makeText(Registration.this, String.valueOf(result1), Toast.LENGTH_SHORT).show();


            } catch (Exception e) {

//                Log.e("IOLOG", e.getMessage());
                e.printStackTrace();
                //  Toast.makeText(Registration.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
            }




            /*String responseJSON=result1.toString();
            JSONArray jarray = null;
            try {
                jarray = new JSONArray(responseJSON);
             //   response = jarray.getJSONObject(0).getString("Column1");
            } catch (JSONException e) {
                Toast.makeText(RegistrationForm.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }*/
            // Log.e("jarray",""+jarray);

            //


            return jsonResponce;
        }

        @Override
        protected void onPostExecute(String Result) {
            super.onPostExecute(Result);
            Toast.makeText(Otp.this, ""+String.valueOf(result1), Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            String responseJSON=result1.toString();
            JSONArray jarray = null;
            try {
                jarray = new JSONArray(responseJSON);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            if(String.valueOf(result1).equals("null"))
            {
                Toast.makeText(Otp.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(Otp.this, "Logged in Successfully !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Otp.this, LoginActivity.class);
//           intent.putExtra("Mobile",Mobile);
                // SharedPreferencesConstants.Mobile = Mobile;
                //  Toast.makeText(RegistrationForm.this, Mobile, Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();

            }
        }


    }
}
