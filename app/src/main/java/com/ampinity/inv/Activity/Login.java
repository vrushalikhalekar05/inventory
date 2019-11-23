package com.ampinity.inv.Activity;

import android.Manifest;
import android.animation.Animator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
/*import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;*/
//import android.support.v7.app.AppCompatActivity;
/*
import android.support.v7.widget.Toolbar;
*/
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.Utility.SharedPreferencesConstants;
import com.ampinity.inv.adapter.CustomizedSpinnerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.HashMap;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class Login extends AppCompatActivity {

    TelephonyManager tel;
    EditText idEditTextEmail, idEditTextPassword;
    String stridTextViewEmailID, stridTextViewPassword;
    Button login;
    TextView signup;
    TextView otp;
    private ImageView bookIconImageView;
    Spinner spinnercompanyname;
    ArrayAdapter<String> adapter1;
    String jsonResponcee;
    SoapPrimitive result1;
    String jsonResponce;
    String IMEI;
    String[] Id;
    String result2;
    public static HashMap <Integer, String> spinnerMap;
    public static String[] spinnerArray;
    String[] companyname, id;
    private RelativeLayout rootView, afterAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //for animation
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login2);
        initViews();
        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                rootView.setBackgroundColor(ContextCompat.getColor(Login.this, R.color.colorSplashText));
                bookIconImageView.setImageResource(R.drawable.logo);
                startAnimation();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        tel = (TelephonyManager) getSystemService(SplashScreenActivity.TELEPHONY_SERVICE);
        new FetchdakhledataAsyncTask_A().execute();
        idEditTextEmail = (EditText) findViewById(R.id.email);
        otp= (TextView) findViewById(R.id.otp);
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),EmployeeRegistration.class);
                startActivity(i);
            }
        });
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
        IMEI = tel.getDeviceId().toString();
        idEditTextPassword = (EditText) findViewById(R.id.pass);

        spinnercompanyname = (Spinner) findViewById(R.id.spinnercompanyname);
        signup=(TextView) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Login.this,"login",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(i);
            }
        });
        Toast.makeText(this, "IMEI" + tel.getDeviceId().toString(), Toast.LENGTH_SHORT).show();
        login=(Button) findViewById(R.id.loginButton);
    login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Validate();
        Toast.makeText(Login.this,"login",Toast.LENGTH_LONG).show();
        Intent i=new Intent(getApplicationContext(),Dashboard.class);
        startActivity(i);
    }
});

    }//end of oncreate
    private void Validate() {
        stridTextViewEmailID = idEditTextEmail.getText().toString();
        stridTextViewPassword = idEditTextPassword.getText().toString();
        if (stridTextViewEmailID.equals("")) {
            Toast.makeText(this, "Enter User Id", Toast.LENGTH_SHORT).show();
        }
        else if (stridTextViewPassword.equals("")) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            new Login.LoginAsyncTask().execute();
        }
    }
    private void initViews() {
        bookIconImageView = findViewById(R.id.bookIconImageView);
        /*bookITextView = findViewById(R.id.bookITextView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);*/
        rootView = findViewById(R.id.rootView);
        afterAnimationView = findViewById(R.id.afterAnimationView);
    }

    private void startAnimation() {
        ViewPropertyAnimator viewPropertyAnimator = bookIconImageView.animate();
        viewPropertyAnimator.x(250f);
        viewPropertyAnimator.y(50f);
        viewPropertyAnimator.setDuration(1000);
        viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                afterAnimationView.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public class FetchdakhledataAsyncTask_A extends AsyncTask<String,Integer,String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getApplicationContext());
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
        }
        @Override
        protected String doInBackground(String... args) {
            String URL = Config.Url +"Loginpage.asmx";
            String soapactionaddlob_N = "http://tempuri.org/getCompanyInfo";
            String methodname = "getCompanyInfo";
            String namespacesaalob_N = "http://tempuri.org/";
            try {
                SoapObject requestAddlob_N = new SoapObject(namespacesaalob_N, methodname);
                SoapSerializationEnvelope envelopeAddlob_N = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelopeAddlob_N.encodingStyle = "utf-8";
                envelopeAddlob_N.implicitTypes = true;
                envelopeAddlob_N.setOutputSoapObject(requestAddlob_N);
                envelopeAddlob_N.dotNet = true;
                result2 = null;
                try {
                    HttpTransportSE httpTransportAddlob_N = new HttpTransportSE(URL);
                    httpTransportAddlob_N.debug = true;
                    httpTransportAddlob_N.call(soapactionaddlob_N, envelopeAddlob_N);
                    result2 = String.valueOf((SoapPrimitive) envelopeAddlob_N.getResponse());
                    Log.i("RESPONSE after add", String.valueOf(result2));
                    jsonResponcee = result2.toString();
                    JSONArray jsonArray = new JSONArray(String.valueOf(result2));
                    Log.i("RESPONSE jsonArray", String.valueOf(jsonArray));int length = jsonArray.length();
                    Log.e("length", "length" + length);
                    spinnerArray = new String[length];
                    companyname = new String[length];
                    Id = new String[length];
                    spinnerMap = new HashMap<Integer, String>();

                    for (int i = 0; i < length; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        companyname[i] = jsonObject.getString("CompanyName");
                        Id[i] = jsonObject.getString("CompanyID");
                        spinnerMap.put(i, Id[i]);
                        spinnerArray[i] = companyname[i];
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            catch (Exception e){
            }
            return jsonResponcee;

        }
        @Override
        protected void onPostExecute(String Result) {
            super.onPostExecute(Result);
            System.out.println(Result);
            try {
                JSONArray jarrayss = new JSONArray(Result);
                Log.e("show", "show responce" + jarrayss);
                adapter1 = new CustomizedSpinnerAdapter(
                        Login.this, android.R.layout.simple_spinner_item,
                        spinnerArray);
                spinnercompanyname.setAdapter(adapter1);
            }
            catch (Exception e) {
            }
        }
    }

    public class LoginAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Login.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
        }
        @Override
        protected String doInBackground(String... args) {
            String url = Config.Url+"loginpage.asmx";
            String soapactionaddlob = "http://tempuri.org/SucessLogin_New";
            String methodname ="SucessLogin_New";
            String namespacesaalob = "http://tempuri.org/";
            SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
            requestAddlob.addProperty("UserName",stridTextViewEmailID);
            requestAddlob.addProperty("Password", stridTextViewPassword);
            requestAddlob.addProperty("IMEINO",IMEI);
            SoapSerializationEnvelope envelopeAddlob = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelopeAddlob.encodingStyle="utf-8";
            envelopeAddlob.implicitTypes=true;
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
                String responseJSON=result1.toString();
                JSONArray jarray = null;
                jarray = new JSONArray(responseJSON);
                /*try {
                    UserName = jarray.getJSONObject(0).getString("UserName");
                    SharedPreferencesConstants.UserName=UserName;
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    Password = jarray.getJSONObject(0).getString("Password");
                    SharedPreferencesConstants.Password=Password;
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                editor.putString("Userid",UserName);
                editor.putString("Password",Password);
                editor.commit();*/
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return jsonResponce;
        }
        @Override
        protected void onPostExecute(String Result) {
            super.onPostExecute(Result);
            //Toast.makeText(Login.this, ""+String.valueOf(result1), Toast.LENGTH_SHORT).show();


            if(String.valueOf(result1).equals("[]"))
            {
                //Toast.makeText(Login.this, ""+String.valueOf(result1), Toast.LENGTH_LONG).show();
                Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(Login.this, ""+String.valueOf(result1), Toast.LENGTH_LONG).show();
                Toast.makeText(Login.this, "Log in Successfully !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Login.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
