package com.ampinity.inv.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
/*import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;*/
import android.os.Bundle;
/*
import android.support.v7.widget.Toolbar;
*/
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.Utility.ConnectionDetector;
import com.ampinity.inv.Utility.SharedPreferencesConstants;
import com.ampinity.inv.adapter.CustomizedSpinnerAdapter;
import com.google.zxing.client.android.Intents;
import com.ampinity.inv.Database.DBHelper;

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

public class RegistrationActivity extends AppCompatActivity {
    ConnectionDetector connectionDetector;
    String strtxtIMEI;
    SoapPrimitive result1;
    String jsonResponce;
    private static final String SOAP_ACTION = "http://tempuri.org/GetAutonumber";
    private static final String METHOD_NAME = "GetAutonumber";
    private static final String NAMESPACE = "http://tempuri.org/";

    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    EditText IMEI;
    private DBHelper mydb ;


    String [] CompanyName={
            "Select Group Name",
            "ABCA",
            "PQRS",
            "LNMO",
            "XYZA"
    };
    Button register,login;
    int length;
    ConnectionDetector cd;
    String id;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String phonePattern = "[0-9]{10}";
    String selcetedspinnergroupname,selcetedspinnerState,selcetedspinnerCity,selcetedlobname;
    EditText contactname, cmpnyphn1,cmpnyphn2,idEditTextVenderName,idEditTextAddress1,idEditTextAddress2,idEditTextEmail,idEditTextPhoneNo,idEditTextNameOfBusiness;
    String  stridEditTextNameOfBusiness,strcontactname ,strcmpnyphn1,strcmpnyphn2,stridEditTextVenderName,stridEditTextAddress,stridEditTextAddress2,stridEditTextEmail,stridEditTextPhoneNo,stridEditTextGSTINNo;
    Spinner spinnergroupname,spinnerState,spinnerCity,spinnerlobname;
    String [] City,State,GroupName,Lobname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Business Registration");


        /** Fading Transition Effect */
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        mydb = new DBHelper(this);

        IMEI=(EditText) findViewById(R.id.idEditTextIMEI);
        idEditTextNameOfBusiness= (EditText) findViewById(R.id.idEditTextNameOfBusiness);
        spinnerState= (Spinner) findViewById(R.id.spinnerState);//state
        spinnerCity= (Spinner) findViewById(R.id.spinnerCity);//city
        spinnerlobname = (Spinner) findViewById(R.id.spinnerlobname);//type of business
        idEditTextAddress1=(EditText)findViewById(R.id.idEditTextAddress1);
        cmpnyphn1=(EditText)findViewById(R.id.cmpnyphn1);
        cmpnyphn2=(EditText)findViewById(R.id.cmpnyphn2);
        idEditTextAddress2=(EditText)findViewById(R.id.idEditTextAddress2);
        idEditTextEmail=(EditText)findViewById(R.id.idEditTextEmail);
        idEditTextPhoneNo=(EditText)findViewById(R.id.idEditTextMobileNo);
        contactname=(EditText)findViewById(R.id.contactname);
        loadIMEI();
      // new getCityspinnerdataAsyncTask().execute();
        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selcetedspinnerState = spinnerState.getSelectedItem().toString();
                if (selcetedspinnerState.equals("Select State")) {
                    City=new String[1];
                    City[0]="Select City";
                    final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
                            RegistrationActivity.this, android.R.layout.simple_spinner_item,
                            City);
                    spinnerCity.setAdapter(adapter1);
                } else {
                    new getCityspinnerdataAsyncTask().execute();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        /*spinnerlobname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selcetedlobname=spinnerlobname.getSelectedItem().toString();
                if(selcetedlobname.equals("Select Lobid"))
                {
//                    Lobname=new String[1];
//                    Lobname[0]=
//
//                    final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
//                            AddAttributeActivity.this, android.R.layout.simple_spinner_item,
//                            Lobname);
//                    spinnerlobname.setAdapter(adapter1);

                }
                else
                {
                   // new gettotalattributespinnerdataAsyncTask().execute();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });*/
        /*spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selcetedspinnerCity = spinnerCity.getSelectedItem().toString();
                if (selcetedspinnerCity.equals("Select City")) {
//                    Lobname=new String[1];
//                    Lobname[0]=
//
//                    final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
//                            AddAttributeActivity.this, android.R.layout.simple_spinner_item,
//                            Lobname);
//                    spinnerlobname.setAdapter(adapter1);

                } else {
                    // new gettotalattributespinnerdataAsyncTask().execute();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });*/

        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.blink);
        register=(Button)findViewById(R.id.register);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                Intent intent = new Intent(RegistrationActivity.this, Login.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
        // new getgroupnamespinnerdataAsyncTask().execute();
        new getStatespinnerdataAsyncTask().execute();
        new getlobspinnerdataAsyncTask().execute();
    }

    public void loadIMEI() {
        // Check if the READ_PHONE_STATE permission is already available.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // READ_PHONE_STATE permission has not been granted.
            requestReadPhoneStatePermission();
        } else {
            // READ_PHONE_STATE permission is already been granted.
            doPermissionGrantedStuffs();
        }
    }
    public void doPermissionGrantedStuffs() {
        //Have an  object of TelephonyManager
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //Get IMEI Number of Phone  //////////////// for this example i only need the IMEI
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        String IMEINumber = tm.getDeviceId();


        // Now read the desired content to a textview.

        IMEI.setText(IMEINumber);

       // txtFullName= (EditText) findViewById(R.id.txtFullName);

        /*txtAddress= (EditText) findViewById(R.id.txtAddress);
        txtEmail= (EditText) findViewById(R.id.txtEmail);
        txtMobile= (EditText) findViewById(R.id.txtMobile);
        txtPanCard= (EditText) findViewById(R.id.txtPanCard);
        txtAdharNo= (EditText) findViewById(R.id.txtAdharNo);
        btnRegister= (Button) findViewById(R.id.btnRegister);

        // txtIMEI = (EditText) findViewById(R.id.txtIMEI);

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                validate();

            }
        });*/


    }
    private void requestReadPhoneStatePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(RegistrationActivity.this,Manifest.permission.READ_PHONE_STATE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            new AlertDialog.Builder(RegistrationActivity.this)
                    .setTitle("Permission Request")
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //re-request
                            ActivityCompat.requestPermissions(RegistrationActivity.this,
                                    new String[]{Manifest.permission.READ_PHONE_STATE},
                                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
                        }
                    })

                    .show();
        } else {
            // READ_PHONE_STATE permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        }
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
    public class getStatespinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(RegistrationActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            //  dialog.show();
        }
        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            try {
                JSONArray jsonArray = new JSONArray(String.valueOf(aVoid));
                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                length = jsonArray.length()+1;
                Log.e("length", "length" + length);
                State=new String[length];

                int j=0;
                State[0]="Select State";
                //  Namearray = new ArrayList<String>();
                for (int i = 0; i < length-1; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    j=j+1;
//                    Namearray.add (jsonObject.getString("Name"));
                    State[j] = jsonObject.getString("State");
//
//
//
//
                    Log.e("State", "State" + State[j]);
                }
                final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(RegistrationActivity.this, android.R.layout.simple_spinner_item,
                        State);
                spinnerState.setAdapter(adapter1);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        }
        @Override
        protected String doInBackground(String... arg) {
            String url = Config.Url+"vendardetails.asmx";
            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "BindState");
            // request.addProperty("Celsius", "48"); // adding method property here serially
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;
            HttpTransportSE httpTransport = new HttpTransportSE(url);
            httpTransport.debug = true;
            try {
                httpTransport.call("http://tempuri.org/BindState", envelope);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.e("IOLOG", e.getMessage());
                dialog.dismiss();
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                Log.e("XMLLOG", e.getMessage());
                e.printStackTrace();
                dialog.dismiss();
            } //send request
            Object  result = null;
            try {
                result = (Object) envelope.getResponse();
                Log.i("RESPONSE", String.valueOf(result));

                //                lobid=String.valueOf(result);
            } catch (SoapFault e) {
                // TODO Auto-generated catch block
                Log.e("SOAPLOG", e.getMessage());
                e.printStackTrace();
                dialog.dismiss();
            }
            return String.valueOf(result);
        }
    }
    public class getCityspinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(RegistrationActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            //  dialog.show();
        }
        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            try {
                JSONArray jsonArray = new JSONArray(String.valueOf(aVoid));
                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                length = jsonArray.length()+1;
                Log.e("length", "length" + length);
                City=new String[length];
                int j=0;
                City[0]="Select City";
                //  Namearray = new ArrayList<String>();
                for (int i = 0; i < length-1; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                      j=j+1;


//                    Namearray.add (jsonObject.getString("Name"));
                    City[j] = jsonObject.getString("City");


//
//
//
//
                    Log.e("City", "City" + City[j]);


                }
                final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(RegistrationActivity.this, android.R.layout.simple_spinner_item,
                        City);
                spinnerCity.setAdapter(adapter1);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        }
        @Override
        protected String doInBackground(String... arg) {
            String url =Config.Url+"vendardetails.asmx";
            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "BindCity_ByState");
            request.addProperty("State", selcetedspinnerState); // adding method property here serially
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;
            HttpTransportSE httpTransport = new HttpTransportSE(url);
            httpTransport.debug = true;
            try {
                httpTransport.call("http://tempuri.org/BindCity_ByState", envelope);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.e("IOLOG", e.getMessage());
                dialog.dismiss();
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                Log.e("XMLLOG", e.getMessage());
                e.printStackTrace();
                dialog.dismiss();
            } //send request
            Object  result = null;
            try {
                result = (Object) envelope.getResponse();
                Log.i("RESPONSE", String.valueOf(result));


//                lobid=String.valueOf(result);
            } catch (SoapFault e) {
                // TODO Auto-generated catch block
//                Log.e("SOAPLOG", e.getMessage());
                e.printStackTrace();
                dialog.dismiss();
            }
            return String.valueOf(result);
        }
    }
    public class getlobspinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(RegistrationActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            //  dialog.show();
        }
        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            //Toast.makeText(RegistrationActivity.this, "sdvf"+aVoid, Toast.LENGTH_SHORT).show();
            try {
                JSONArray jsonArray = new JSONArray(String.valueOf(aVoid));
                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                length = jsonArray.length()+1;
                Log.e("length", "length" + length);
                Lobname=new String[length];

                int j=0;
                Lobname[0]="Select Lobid";

                //  Namearray = new ArrayList<String>();
                for (int i = 0; i < length-1; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    j=j+1;
//                    Namearray.add (jsonObject.getString("Name"));
                    Lobname[j] = jsonObject.getString("Name");
//
//
//
//
                    Log.e("Name", "Name" + Lobname[j]);



                }




                final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
                        RegistrationActivity.this, android.R.layout.simple_spinner_item,
                        Lobname);
                spinnerlobname.setAdapter(adapter1);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url =Config.Url+"attributedetails.asmx";


            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "Bindlob");
            // request.addProperty("Celsius", "48"); // adding method property here serially

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            HttpTransportSE httpTransport = new HttpTransportSE(url);
            httpTransport.debug = true;

            try {
                httpTransport.call("http://tempuri.org/Bindlob", envelope);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.e("IOLOG", e.getMessage());
                dialog.dismiss();
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                Log.e("XMLLOG", e.getMessage());
                e.printStackTrace();
                dialog.dismiss();
            } //send request

            Object  result = null;
            try {
                result = (Object) envelope.getResponse();
                Log.i("RESPONSE", String.valueOf(result));


//                lobid=String.valueOf(result);





            } catch (SoapFault e) {
                // TODO Auto-generated catch block
                Log.e("SOAPLOG", e.getMessage());
                e.printStackTrace();
                dialog.dismiss();
            }

            return String.valueOf(result);
        }



    }


    public void validate() {


        selcetedlobname = spinnerlobname.getSelectedItem().toString();
        stridEditTextNameOfBusiness=idEditTextNameOfBusiness.getText().toString();

        stridEditTextAddress = idEditTextAddress1.getText().toString();
        stridEditTextAddress2 = idEditTextAddress2.getText().toString();
        strcmpnyphn1 = cmpnyphn1.getText().toString();
        strcmpnyphn2 = cmpnyphn2.getText().toString();
        strcontactname = contactname.getText().toString();
        stridEditTextPhoneNo = idEditTextPhoneNo.getText().toString();
        stridEditTextEmail = idEditTextEmail.getText().toString();
        strtxtIMEI=IMEI.getText().toString();



        selcetedspinnerState = spinnerState.getSelectedItem().toString();
        selcetedspinnerCity = spinnerCity.getSelectedItem().toString();


        if (idEditTextPhoneNo.getText().toString().length() == 0) {
            Toast.makeText(this, "Please Enter Mobile No", Toast.LENGTH_SHORT).show();
        } /*else if (selcetedspinnerState.equals("Select State")) {
            Toast.makeText(this, "Please Select State", Toast.LENGTH_SHORT).show();
        }*/ else if (selcetedlobname.equals("Select Lobid")) {
            Toast.makeText(this, "Please Select Lobid", Toast.LENGTH_SHORT).show();
        }
       /* else if (selcetedspinnerCity.equals("Select City")) {
            Toast.makeText(this, "Please Select City", Toast.LENGTH_SHORT).show();
        }*/
        else if (stridEditTextNameOfBusiness.equals("")) {
            Toast.makeText(this, "Please enter business Name", Toast.LENGTH_SHORT).show();
        }
        else if (stridEditTextAddress.equals("")) {
            Toast.makeText(this, "Please enter first Address", Toast.LENGTH_SHORT).show();
        }

        else if (stridEditTextAddress2.equals("")) {
            Toast.makeText(this, "Please enter second Address", Toast.LENGTH_SHORT).show();
        }
        else if (stridEditTextPhoneNo.equals("")) {
            Toast.makeText(this, "Please enter Phone Number", Toast.LENGTH_SHORT).show();
        }

        else if (strcmpnyphn1.equals("")) {
            Toast.makeText(this, "Please enter Company Phone Number 1", Toast.LENGTH_SHORT).show();
        }

        else if (strcmpnyphn1.length()>10 ||strcmpnyphn1.length()<1) {
            Toast.makeText(this, "Please enter valid Company Phone Number 1", Toast.LENGTH_SHORT).show();
        }
        else if (!strcmpnyphn1.matches(phonePattern)) {
            Toast.makeText(this, "Please enter valid Company Phone Number", Toast.LENGTH_SHORT).show();
        }
        else if (strcmpnyphn2.equals("")) {
            Toast.makeText(this, "Please enter Company Phone Number 1", Toast.LENGTH_SHORT).show();
        }

        else if (strcmpnyphn2.length()>10 ||strcmpnyphn2.length()<1) {
            Toast.makeText(this, "Please enter valid Company Phone Number 1", Toast.LENGTH_SHORT).show();
        }
        else if (!strcmpnyphn2.matches(phonePattern)) {
            Toast.makeText(this, "Please enter valid Company Phone Number", Toast.LENGTH_SHORT).show();
        }
        else if (stridEditTextPhoneNo.length()>10 ||stridEditTextPhoneNo.length()<1) {
            Toast.makeText(this, "Please enter valid Phone Number", Toast.LENGTH_SHORT).show();
        }
        else if (!stridEditTextPhoneNo.matches(phonePattern)) {
            Toast.makeText(this, "Please enter valid Phone Number", Toast.LENGTH_SHORT).show();
        }

        else if(stridEditTextEmail.equals("")){
            Toast.makeText(this, "Please enter valid Email Id", Toast.LENGTH_SHORT).show();
        }
        /*else if (!stridEditTextEmail.matches(emailPattern)) {
            Toast.makeText(this, "Please enter valid Email Id", Toast.LENGTH_SHORT).show();
        }*/

        else if (strcontactname.equals("")) {
            Toast.makeText(this, "Please enter Contact Name", Toast.LENGTH_SHORT).show();
        }



        else {

            new AddDataAsyncTask().execute();
        }
    }



    public class AddDataAsyncTask extends AsyncTask<String, Integer, String>
    {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(RegistrationActivity.this);
            dialog.setMessage("Please Wait...");

            dialog.setCancelable(false);

        }

        @Override
        protected String doInBackground(String... args) {

            String url =Config.Url+"registration.asmx";
            String soapactionaddlob ="http://tempuri.org/Insert_new";
            String methodname="Insert_new";
            String namespacesaalob="http://tempuri.org/";

            SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);

            requestAddlob.addProperty("TypeOfBussiness", selcetedlobname);
            requestAddlob.addProperty("CompanyName", stridEditTextNameOfBusiness);
            requestAddlob.addProperty("CompanyAddress",stridEditTextAddress);
            requestAddlob.addProperty("CompanyAddress2",stridEditTextAddress2);
            requestAddlob.addProperty("CompanyPhone1",strcmpnyphn1);
            requestAddlob.addProperty("CompanyPhone2",strcmpnyphn2);
            requestAddlob.addProperty("EmailId",stridEditTextEmail);
            requestAddlob.addProperty("IMEI",strtxtIMEI);
            //requestAddlob.addProperty("State","MH");
            requestAddlob.addProperty("State",selcetedspinnerState);
            requestAddlob.addProperty("City",selcetedspinnerCity);
            requestAddlob.addProperty("Mobile",stridEditTextPhoneNo);
            requestAddlob.addProperty("ContactName",strcontactname);

//                requestAddlob.addProperty("Password", Pass);

            System.out.println(requestAddlob);

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

            System.out.println("JsonResponce"+jsonResponce);
            return jsonResponce;
        }

        @Override
        protected void onPostExecute(String Result) {
            super.onPostExecute(Result);

            System.out.println("Result"+Result);
           /* Toast.makeText(RegistrationActivity.this, ""+Result
                    , Toast.LENGTH_SHORT).show();*/


           // SharedPreferencesConstants.mobilenum=stridEditTextPhoneNo;
        //startActivity(new Intent(RegistrationActivity.this,Otp.class));

            if(String.valueOf(Result).equals("null"))
            {
                Toast.makeText(RegistrationActivity.this, "Already Registred !", Toast.LENGTH_SHORT).show();

            }
            else {

                Toast.makeText(RegistrationActivity.this, "Registered Successfully !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
//           intent.putExtra("Mobile",Mobile);
               // SharedPreferencesConstants.Mobile = Mobile;
                //  Toast.makeText(RegistrationForm.this, Mobile, Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();

            }
        }


    }
}
