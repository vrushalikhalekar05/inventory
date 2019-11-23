package com.ampinity.inv.Activity;

import android.Manifest;
import android.animation.Animator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
/*import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;*/
/*
import android.support.v7.app.AppCompatActivity;
*/
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.Utility.PrefManager;
import com.ampinity.inv.Utility.SharedPreferencesConstants;
import com.ampinity.inv.Utility.SharedPreferencesUtils;
import com.ampinity.inv.Utility.Utils;
import com.ampinity.inv.adapter.CustomizedSpinnerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.HashMap;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LoginActivity extends Activity {
    private ImageView bookIconImageView;
    private TextView bookITextView;
    private ProgressBar loadingProgressBar;
    private RelativeLayout rootView, afterAnimationView;
    EditText idEditTextEmail, idEditTextPassword;
    Spinner spinnercompanyname;
    String result2,UserName,Password;
    SoapPrimitive result1;
    String jsonResponce;
    String jsonResponcee;
    Button idButtonLogin, idButtonReset;
    TextView idTextViewEmailID, idTextViewPassword, idTextViewCompanyName;
    String stridTextViewEmailID, stridTextViewPassword, stridTextViewCompanyName;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    PrefManager prefManager;
    String[] CompanyName = {

            "Select Company Name",
            "ABCA",
            "PQRS",
            "LNMO",
            "XYZA"
    };
    String[] companyname, id;
    String[] Id;
    private static final String SOAP_ACTION = "http://tempuri.org/getCompanyInfo";
    private static final String METHOD_NAME = "getCompanyInfo";
    private static final String NAMESPACE = "http://tempuri.org/";
    ArrayAdapter<String> adapter1;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String phonePattern = "[0-9]{10}";
    TelephonyManager tel;
    String IMEI;
    public static HashMap <Integer, String> spinnerMap;
    public static String[] spinnerArray;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);

        //for animation
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_login);
        /*initViews();
        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
               *//* bookITextView.setVisibility(GONE);
                loadingProgressBar.setVisibility(GONE);*//*
                rootView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.colorSplashText));
                bookIconImageView.setImageResource(R.drawable.logo);
                startAnimation();
            }

            @Override
            public void onFinish() {

            }
        }.start();*/




       // getSupportActionBar().hide();
        pref = getApplicationContext().getSharedPreferences("MyPreference", 0); // 0 - for private mode
        editor = pref.edit();
        prefManager=new PrefManager(getApplicationContext());
        spinnercompanyname = (Spinner) findViewById(R.id.spinnercompanyname);
        idEditTextEmail = (EditText) findViewById(R.id.email);
        idTextViewCompanyName = (TextView) findViewById(R.id.idTextViewCompanyName);
        idEditTextPassword = (EditText) findViewById(R.id.pass);
        idButtonLogin = (Button) findViewById(R.id.loginButton);
        idButtonReset = (Button) findViewById(R.id.idButtonReset);
       // final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.blink);
        // initializeWidget();
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
        IMEI = tel.getDeviceId().toString();
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
        //  SharedPreferencesUtils.addStringToSharedPreferences(SharedPreferencesConstants.KEY_SP_IMEI, tel.getDeviceId().toString(), getApplicationContext());
        //Log.e("IMEI", SharedPreferencesUtils.getStringFromSharedPreference(SharedPreferencesConstants.KEY_SP_IMEI, getApplicationContext()));
        //  callingws();
        Toast.makeText(this, "IMEI" + tel.getDeviceId().toString(), Toast.LENGTH_SHORT).show();

        //new FetchdakhledataAsyncTask_A().execute();
//        final ArrayAdapter<String>
        idButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   v.startAnimation(animAlpha);
                //Validate();
                Toast.makeText(LoginActivity.this,"login",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                //           intent.putExtra("Mobile",Mobile);
                // SharedPreferencesConstants.Mobile = Mobile;

                //  Toast.makeText(RegistrationForm.this, Mobile, Toast.LENGTH_SHORT).show();startActivity(intent);
                //Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
        /*idButtonReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });*/
    }

    //for animation
    private void initViews() {
        bookIconImageView = findViewById(R.id.bookIconImageView);
        /*bookITextView = findViewById(R.id.bookITextView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);*/
        rootView = findViewById(R.id.rootView);
        afterAnimationView = findViewById(R.id.afterAnimationView);
    }

    private void startAnimation() {
        ViewPropertyAnimator viewPropertyAnimator = bookIconImageView.animate();
        viewPropertyAnimator.x(50f);
        viewPropertyAnimator.y(100f);
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
                        new LoginAsyncTask().execute();
                    }
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
                        LoginActivity.this, android.R.layout.simple_spinner_item,
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
            dialog = new ProgressDialog(LoginActivity.this);
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
                try {
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
                    editor.commit();
            }
            catch (Exception e) {
                e.printStackTrace();
                }
                return jsonResponce;
        }
        @Override
        protected void onPostExecute(String Result) {
            super.onPostExecute(Result);
            Toast.makeText(LoginActivity.this, ""+String.valueOf(result1), Toast.LENGTH_SHORT).show();

            if(String.valueOf(result1).equals("[]"))
            {
                Toast.makeText(LoginActivity.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                }
            else {
                Toast.makeText(LoginActivity.this, "Logged in Successfully !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                startActivity(intent);
                finish();
                }
        }
        }
    /*
    public class myAsyncTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            adapter1 = new ArrayAdapter<String>  (LoginActivity.this,
//                    android.R.layout.simple_spinner_dropdown_item,
//                    spinnerArray);
//            spinnercompanyname.setAdapter(adapter1);
            adapter1 = new CustomizedSpinnerAdapter(
                    LoginActivity.this, android.R.layout.simple_spinner_item,
                    spinnerArray);
            spinnercompanyname.setAdapter(adapter1);
        }

        @Override
        protected Void doInBackground(Void... params) {

            String URL ="http://ampinityinventory.ampinityit.com/Loginpage.asmx";

            //for linear parameter
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            // request.addProperty("Celsius", "48"); // adding method property here serially

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.debug = true;

            try {
                httpTransport.call(SOAP_ACTION, envelope);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.e("IOLOG", e.getMessage());
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                Log.e("XMLLOG", e.getMessage());
                e.printStackTrace();
            } //send request

            String  result = null;
             try {
                 JSONArray jsonArray = new JSONArray(String.valueOf(result)
                 Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                int length = jsonArray.length();
                Log.e("length", "length" + length);
                spinnerArray = new String[length];

                companyname=new String[length];
                Id=new String[length];
                spinnerMap = new HashMap<Integer, String>();
                for (int i = 0; i < length; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    companyname[i] = jsonObject.getString("CompanyName");

                    Id[i] = jsonObject.getString("CompanyID");

   spinnerMap.put(i,Id[i]);
                    spinnerArray[i] = companyname[i];
  Log.e("companyname", "companyname" + companyname[i]);
                    Log.e("spinnerArray", "spinnerArray" + spinnerArray[i]);
                    Log.e("Id", "Id" + Id[i]);
  }


            } catch (JSONException e) {
                e.printStackTrace();
            }
  return null;
        }
}
*/

//    public void callingws() {
//        String SOAP_ACTION = "http://tempuri.org/getCompanyInfo";
//        String METHOD_NAME = "getCompanyInfo";
//        String NAMESPACE = "http://tempuri.org/";
//        String URL = "http://ampinventory.ampinityit.com/loginpage.asmx";
//
//        try {
//            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
//           // Request.addProperty("Celsius", getCel);
//            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//            soapEnvelope.dotNet = true;
//            soapEnvelope.setOutputSoapObject(Request);
//            HttpTransportSE transport = new HttpTransportSE(URL);
//            transport.call(SOAP_ACTION, soapEnvelope);
//            SoapPrimitive resultString = (SoapPrimitive) soapEnvelope.getResponse();
//            Log.i("", "Result Celsius: " + resultString);
//        } catch (Exception ex) {
//            Log.e("", "Error: " + ex.getMessage());
//            ex.printStackTrace();
//        }
//    }
//    public void validate() {
//        oldPassword = idEditTextOldPassword.getText().toString().trim();
//        newPassword = idEditTextNewPassword.getText().toString().trim();
//        confirmPassword = idEditTextConfirmPassword.getText().toString().trim();
//
//        ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
//        if (cd.isConnectingToInternet()) {
//            if (!(oldPassword.length() == 0) && oldPassword.equals(SharedPreferencesUtils.getStringFromSharedPreference(SharedPreferencesConstants.KEY_SP_PASSWORD, getApplicationContext()))) {
//                if (!(newPassword.length() == 0) && !(confirmPassword.length() == 0) && newPassword.equals(confirmPassword)) {
//
//                    // now that validation is successful, call WS to change password here
//                    changePassword(oldPassword, newPassword, confirmPassword);
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "Please Enter valid New Password", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Toast.makeText(getApplicationContext(), "Please Enter valid old password", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(getApplicationContext(), "please connect to internet", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void changePassword(String OldPassword, String NewPassword, String ConfirmPassword) {
//        JSONObject jsonObject = new JSONObject();
//        try {
//            Log.e("oldPassword", " " + OldPassword);
//            Log.e("NewPassword", " " + NewPassword);
//            Log.e("ConfirmPassword", " " + ConfirmPassword);
//            jsonObject.put("old_password", OldPassword);
//            jsonObject.put("new_password1", NewPassword);
//            jsonObject.put("new_password2", ConfirmPassword);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        if (jsonObject.length() > 0) {
//            new changePasswordWS().execute(String.valueOf(jsonObject));
//
//        }
//    }
//
//    public class changePasswordWS extends AsyncTask<String, Void, String> {
//        String JsonResponse = null;
//        ProgressDialog dialog;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            dialog = new ProgressDialog(ChangePasswordActivity.this);
//            dialog.setMessage("Please Wait...");
//            dialog.setCancelable(false);
//            dialog.show();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            Log.e("Params", "Params" + params);
//            String JsonDATA = params[0];
//            HttpURLConnection urlConnection = null;
//            BufferedReader reader = null;
//            try {
//                URL url = new URL("");
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setDoOutput(true);
//                // is output buffer writter
//                urlConnection.setRequestMethod("POST");
//                urlConnection.setRequestProperty("Content-Type", "application/json");
//                urlConnection.setRequestProperty("Accept", "application/json");
//                //set headers and method
//                Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
//                writer.write(JsonDATA);
//                // json data
//                Log.e("Response do in bg", "Response do in bg" + JsonResponse);
//                writer.close();
//                InputStream inputStream = urlConnection.getInputStream();
//                //input stream
//                StringBuffer buffer = new StringBuffer();
//                if (inputStream == null) {
//                    // Nothing to do.
//                    return null;
//                }
//
//                reader = new BufferedReader(new InputStreamReader(inputStream));
//                String inputLine;
//                while ((inputLine = reader.readLine()) != null)
//                    buffer.append(inputLine + "\n");
//                if (buffer.length() == 0) {
//                    // Stream was empty. No point in parsing.
//                    return null;
//                }
//                JsonResponse = buffer.toString();
//                Log.e("Response do in bg", "Response do in bg" + JsonResponse);
//                //response data
//                //send to post execute
//                return JsonResponse;
//                // return null;
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (final IOException e) {
//                        Log.e("Tag", "Error closing stream", e);
//                    }
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            Log.e("On post", "On post" + JsonResponse);
//            Log.e("Result", "Result" + result);
//            if (JsonResponse != null) {
//                if (JsonResponse != null) {
//                    dialog.dismiss();
//                    try {
//                        JSONObject jsonObject = new JSONObject(JsonResponse);
//                        Log.e("jsonObject", "jsonObject" + jsonObject);
//                        String message = jsonObject.getString("success");
//                        // handle response here
//                        Toast.makeText(getApplicationContext(), "password successfully changed", Toast.LENGTH_SHORT).show();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } else {
//                Toast.makeText(getApplicationContext(), "please try again later", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }



//    public class Loadspinnerdata extends AsyncTask<Void, Void, Void> {
//        String JsonResponse = null;
//        ProgressDialog dialog;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            dialog = new ProgressDialog(LoginActivity.this);
//            dialog.setMessage("Please Wait...");
//            dialog.setCancelable(false);
//            dialog.show();
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            return null;
//        }
//
//        @Override
//        protected Void onPostExecute(Void... params) {
//
//            return null;
//        }
//    }

}




