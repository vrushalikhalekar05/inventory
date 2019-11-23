package com.ampinity.inv.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.Utility.ConnectionDetector;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/*import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;*/
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.ampinity.inv.R;

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

public class EmployeeRegistration extends AppCompatActivity {
    Button idButtonCancel,idButtonSave;

    ConnectionDetector cd;
    EditText idEditTextID,idEditTextName,idEditTextAddress,idEditTextState,idEditTextCity,idEditTextPhoneNo,idEditTextEmail,idEditTextSalary,idEditTextTarget;
    String empid,empname,empaddress,empstate,empcity,empphone,empemail,empsalary,emptarget;
    String lobid;
    int idd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration2);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add  Employee Details");

        idEditTextID=(EditText) findViewById(R.id.idEditTextID);
        idEditTextName=(EditText) findViewById(R.id.idEditTextName);
        idEditTextAddress=(EditText) findViewById(R.id.idEditTextAddress);
        idEditTextState=(EditText) findViewById(R.id.idEditTextState);
        idEditTextCity=(EditText) findViewById(R.id.idEditTextCity);
        idEditTextPhoneNo=(EditText) findViewById(R.id.idEditTextPhoneNo);
        idEditTextEmail=(EditText) findViewById(R.id.idEditTextEmail);
        idEditTextSalary=(EditText) findViewById(R.id.idEditTextSalary);
        idEditTextTarget=(EditText) findViewById(R.id.idEditTextTarget);

        idButtonCancel=(Button) findViewById(R.id.idButtonCancel);
        idButtonSave=(Button) findViewById(R.id.idButtonSave);
        idButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        idButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
        new getEmpRegIdAsyncTask().execute();
        //new  getBankidAsyncTask().execute();
    }
    @Override
    public void onResume() {
        super.onResume();
        //new EmployeeInsertAsyncTask().execute();
    }
    public void validate()
    {
        /*cd=new ConnectionDetector(getApplicationContext());
        if (!cd.isConnectingToInternet()) {
            Toast.makeText(getApplicationContext(), "Network Unavailable", Toast.LENGTH_SHORT).show();
        }
        else
        if (idEditTextBankName.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Bank Name",Toast.LENGTH_SHORT).show();
        }else
        if (idEditTextBranchName.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Branch Name",Toast.LENGTH_SHORT).show();
        }
        if (idEditTextIFSCCode.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter IFSC Code",Toast.LENGTH_SHORT).show();
        }
        else{*/
            empid=idEditTextID.getText().toString();
            empname=idEditTextName.getText().toString();
            empaddress=idEditTextAddress.getText().toString();
            empstate=idEditTextState.getText().toString();
            empcity=idEditTextCity.getText().toString();
            empphone=idEditTextPhoneNo.getText().toString();
            empemail=idEditTextEmail.getText().toString();
            empsalary=idEditTextSalary.getText().toString();
            emptarget=idEditTextTarget.getText().toString();


            new EmployeeInsertAsyncTask().execute();
        //}

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
    public class getEmpRegIdAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(EmployeeRegistration.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            //  dialog.show();

        }
        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            System.out.println("value of jsonArray in get id emp regi aVoid "+aVoid);
            try {

                JSONArray jsonArray = new JSONArray(String.valueOf(aVoid));
                System.out.println("value of jsonArray in get id emp regi "+jsonArray);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                lobid= jsonObject.getString("Column1");

                if(lobid.equals("null")){
                    idEditTextID.setText("1");
                }
                else {
                    idd= Integer.parseInt(lobid)+1;
                    String j=Integer.toString(idd);
                    idEditTextID.setText(""+j);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();

        }
        @Override
        protected String doInBackground(String... arg) {
            String URL = Config.Url+"empregi.asmx";
            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "GetAutonumber");
            // request.addProperty("Celsius", "48"); // adding method property here serially
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;
            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.debug = true;
            try {
                httpTransport.call("http://tempuri.org/GetAutonumber", envelope);
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
    public class EmployeeInsertAsyncTask extends AsyncTask<Void, Void, Void>{
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(EmployeeRegistration.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);
        }
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            finish();
            Toast.makeText(EmployeeRegistration.this,"Added Succesfully",Toast.LENGTH_SHORT).show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String url =Config.Url+"empregi.asmx";
            String soapactionaddlob ="http://tempuri.org/Insert_new";
            String methodname="Insert_new";
            String namespacesaalob="http://tempuri.org/";
            try {
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                //requestAddlob.addProperty("id",empid );
                requestAddlob.addProperty("EmployeeName", empname);
                requestAddlob.addProperty("Address", empaddress);
                requestAddlob.addProperty("State", empstate);
                requestAddlob.addProperty("City", empcity);
                requestAddlob.addProperty("PhoneNo", empphone);
                requestAddlob.addProperty("Email", empemail);
                requestAddlob.addProperty("Salary", empsalary);
                requestAddlob.addProperty("BasicTarget", emptarget);
                requestAddlob.addProperty("WorkingDays", "2");
                // requestAddlob.addProperty("UpdatedBy", "UpdatedBy");
                // adding method property here serially
                SoapSerializationEnvelope envelopeAddlob = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelopeAddlob.encodingStyle  = "utf-8";
                envelopeAddlob.implicitTypes  = true;
                envelopeAddlob.setOutputSoapObject(requestAddlob);
                envelopeAddlob.dotNet = true;
                Object result1=null;
                try {
                    HttpTransportSE httpTransportAddlob = new HttpTransportSE(url);
                    httpTransportAddlob.debug = true;
                    httpTransportAddlob.call(soapactionaddlob, envelopeAddlob);
                    result1 = (Object) envelopeAddlob.getResponse();
                    Log.i("RESPONSE after add", String.valueOf(result1));

                } catch (Exception e) {

                    //   Log.e("IOLOG", e.getMessage());
                    e.printStackTrace();
//                dialog.dismiss();
                }

            } catch (Exception e) {

                //   Log.e("IOLOG", e.getMessage());
                e.printStackTrace();
//                dialog.dismiss();
            }

            return null;
        }
    }
}
