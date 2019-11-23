package com.ampinity.inv;

import android.app.ProgressDialog;
import android.os.AsyncTask;
/*
import android.support.v7.app.AppCompatActivity;
*/
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ampinity.inv.Activity.AddBankActivity;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.Utility.ConnectionDetector;
import com.ampinity.inv.fragment.ActivityAddOffer;

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

public class ActivityOffer2 extends AppCompatActivity {
    private static final String SOAP_ACTION = "http://tempuri.org/GetAutonumber";
    private static final String METHOD_NAME = "GetAutonumber";
    private static final String NAMESPACE = "http://tempuri.org/";
    Button buttonSave, buttonCancel;
    EditText idEditTextOffername, idEditTextOfferId, idEditTextPercentage;
    TextView textViewTodate, textViewfromDate;
    DatePicker datePickerToDate, datePickerFromdate, picker;
    String Name, SchemeName, CompanyID, TotalAttribute, Percentage;
    String lobid;
    int id;
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Offer");

        buttonSave = (Button) findViewById(R.id.idButtonSave);
        buttonCancel = (Button) findViewById(R.id.idButtonCancel);
        idEditTextOffername = (EditText) findViewById(R.id.idEditTextLobName);
        idEditTextPercentage = (EditText) findViewById(R.id.idEditTextPercentage);
        idEditTextOfferId = (EditText) findViewById(R.id.idEditTextIDLOB);

        buttonCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validate();
            }
        });

        getlobid();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
    public void getlobid() {
        new getBankidAsyncTask().execute();
    }
    public class getBankidAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(ActivityOffer2.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            //  dialog.show();
        }
        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            try {
                JSONArray jsonArray = new JSONArray(String.valueOf(aVoid));
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                    lobid= jsonObject.getString("Column1");
//
                   if(lobid.equals("null")){
                   idEditTextOfferId.setText("1");
                  }
                  else {

                    int idd= Integer.parseInt(lobid)+1;
                    idEditTextOfferId.setText(""+idd);
                  }
               }
                    catch (JSONException e)
                {
                e.printStackTrace();
                }
                  dialog.dismiss();
        }
        @Override
        protected String doInBackground(String... arg) {
            String URL = Config.Url+"Offer2.asmx";
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
    public void validate()
    {
        cd=new ConnectionDetector(getApplicationContext());
        if (!cd.isConnectingToInternet()) {
            Toast.makeText(getApplicationContext(), "Network Unavailable", Toast.LENGTH_SHORT).show();
        }
        else
        if (idEditTextOffername.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Offer Name",Toast.LENGTH_SHORT).show();
        }else
        if (idEditTextPercentage.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Percentage",Toast.LENGTH_SHORT).show();
        }
        else{
            SchemeName=idEditTextOffername.getText().toString();
            Percentage=idEditTextPercentage.getText().toString();
            new AddBankInsertDetailtAsyncTask().execute();
        }
    }
    public class AddBankInsertDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(ActivityOffer2.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            finish();
            Toast.makeText(ActivityOffer2.this,"Added Succesfully",Toast.LENGTH_SHORT).show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String url =Config.Url+"Offer2.asmx";
            String soapactionaddlob ="http://tempuri.org/Insert";
            String methodname="Insert";
            String namespacesaalob="http://tempuri.org/";
            try {
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                requestAddlob.addProperty("SchemeName",SchemeName);
                requestAddlob.addProperty("peroff", Percentage);
                requestAddlob.addProperty("CompanyID", "1");
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
                }
                catch (Exception e)
                {
                    //   Log.e("IOLOG", e.getMessage());
                    e.printStackTrace();
//                dialog.dismiss();
                }

            }
            catch (Exception e) {
                //   Log.e("IOLOG", e.getMessage());
                e.printStackTrace();
                //                dialog.dismiss();
            }
            return null;

        }
    }
    }


