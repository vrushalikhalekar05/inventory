package com.ampinity.inv.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
/*
import android.support.v7.app.AppCompatActivity;
*/
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.Utility.ConnectionDetector;
import com.ampinity.inv.adapter.CustomizedSpinnerAdapter;

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

public class AddGstActivity extends AppCompatActivity {
    Button idButtonCancel,idButtonSave;
    EditText idEditTextIDGST,idEditTextRate,idEditTextGSTName;
    String lobid;
    ConnectionDetector cd;
    String Id,Rate,GstName;
    int idd;
    int id1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gst);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add GST Per Details");


        idButtonCancel=(Button)findViewById(R.id.idButtonCancel);
        idButtonSave=(Button)findViewById(R.id.idButtonSave);
        idEditTextRate=(EditText)findViewById(R.id.idEditTextRate);
        idEditTextGSTName=(EditText)findViewById(R.id.idEditTextGSTName);
        idEditTextIDGST=(EditText)findViewById(R.id.idEditTextIDGST);

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
        new getGstidAsyncTask().execute();
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
    public void validate()
    {
        /*cd=new ConnectionDetector(getApplicationContext());
        if (!cd.isConnectingToInternet()) {
            Toast.makeText(getApplicationContext(), "Network Unavailable", Toast.LENGTH_SHORT).show();
            }
        else
        if (idEditTextRate.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Rate",Toast.LENGTH_SHORT).show();
        }else
        if (idEditTextGSTName.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter GST Name",Toast.LENGTH_SHORT).show();
        }
        else{*/
            Id=idEditTextIDGST.getText().toString();
            id1=Integer.parseInt(Id);
            Rate=idEditTextRate.getText().toString();
            GstName=idEditTextGSTName.getText().toString();
            new AddGstTaxGroupInsertDetailtAsyncTask().execute();
        //}
    }
    //for id
    public class getGstidAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddGstActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
        }
        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            try {
                JSONArray jsonArray = new JSONArray(String.valueOf(aVoid));
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                lobid= jsonObject.getString("Column1");


                if(lobid.equals("null")){
                    idEditTextIDGST.setText("1");
                }
                else {
                    //lobid="0";
                    idd= Integer.parseInt(lobid)+1;
                    idEditTextIDGST.setText(""+idd);
                }

               /*int idd= Integer.parseInt(lobid)+1;
                if(lobid.equals("null")){
                    idEditTextIDGST.setText("1");
                }
                else {
                    idEditTextIDGST.setText(""+idd);
                }*/
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
            }
            @Override
            protected String doInBackground(String... arg) {
            String URL = Config.Url+"TaxGroup.asmx";
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

        public class AddGstTaxGroupInsertDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddGstActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);
            }
            @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            finish();
            System.out.println("Value of id using idEditTextIDGST"+idEditTextIDGST.getText().toString());
            System.out.println("value of id using id"+Id);
            Toast.makeText(AddGstActivity.this,"Added Succesfully",Toast.LENGTH_SHORT).show();
           // new AddAttributeDetailtAsyncTask().execute();
            }
        @Override
        protected Void doInBackground(Void... params) {

            String url =Config.Url+"TaxGroup.asmx";
            String soapactionaddlob ="http://tempuri.org/Insert_new";
            String methodname="Insert_new";
            String namespacesaalob="http://tempuri.org/";
            try {
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                //Id
                requestAddlob.addProperty("Id",id1 );
                requestAddlob.addProperty("TaxName",GstName );
                requestAddlob.addProperty("Rate", Rate);

                SoapSerializationEnvelope envelopeAddlob = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelopeAddlob.encodingStyle = "utf-8";
                envelopeAddlob.implicitTypes = true;
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

                    e.printStackTrace();

                }
                } catch (Exception e) {

                e.printStackTrace();

            }
            return null;

            }

            }


}
