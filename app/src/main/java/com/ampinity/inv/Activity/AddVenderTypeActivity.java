package com.ampinity.inv.Activity;

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
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;

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

public class AddVenderTypeActivity extends AppCompatActivity {

    private static final String SOAP_ACTION = "http://tempuri.org/GetAutonumber";
    private static final String METHOD_NAME = "GetAutonumber";
    private static final String NAMESPACE = "http://tempuri.org/";
    EditText idEditTextIDVenderType,idEditTextVenderGroup,idEditTextDescription;
    Button idButtonSave;
    String id,VGroup,Description;
    Button idButtonCancel;

    String idEditTextIDVenderType1;
    int id1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vender_type);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      //  getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Add Vender Type Details");

        idEditTextIDVenderType=(EditText)findViewById(R.id.idEditTextIDVenderType);
        idEditTextVenderGroup=(EditText)findViewById(R.id.idEditTextVenderGroup);
        idEditTextDescription=(EditText)findViewById(R.id.idEditTextDescription);
        idButtonSave=(Button)findViewById(R.id.idButtonSave);
        idButtonCancel=(Button)findViewById(R.id.idButtonCancel);
        idButtonCancel.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        idButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate();
            }
        });
        getid();
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
    public void getid()
    {
        new getidAsyncTask().execute();

    }
    public class getidAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddVenderTypeActivity.this);
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
                id= jsonObject.getString("Column1");
                int idd= Integer.parseInt(id)+1;
                if(id.equals("null")){
                    idEditTextIDVenderType.setText("1");
                }
                else {
                    idEditTextIDVenderType.setText(""+idd);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
          //  idEditTextIDVenderType.setText(id);
            dialog.dismiss();
        }
        @Override
        protected String doInBackground(String... arg) {
            String URL = Config.Url+"vendertype.asmx";
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
    public void Validate()
        {
        if (idEditTextVenderGroup.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Vender Group",Toast.LENGTH_SHORT).show();
        }else
        if (idEditTextDescription.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Description",Toast.LENGTH_SHORT).show();
        }
        else{
            idEditTextIDVenderType1=idEditTextIDVenderType.getText().toString();
            id1=Integer.parseInt(idEditTextIDVenderType1);
            VGroup=idEditTextVenderGroup.getText().toString();
            Description=idEditTextDescription.getText().toString();
            new AddVTAsyncTask().execute();
        }
        }
    public class AddVTAsyncTask extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddVenderTypeActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);
            }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(AddVenderTypeActivity.this,"Added Succesfully",Toast.LENGTH_SHORT).show();
            getid();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String url =Config.Url+"vendertype.asmx";
            String soapactionaddlob ="http://tempuri.org/Insert_new";
            String methodname="Insert_new";
            String namespacesaalob="http://tempuri.org/";
            try {
                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                int lobint= Integer.parseInt(id);
                // requestAddlob.addProperty("LobId", 6);
                //updated
                requestAddlob.addProperty("Id", id1);
                requestAddlob.addProperty("partyGroup", VGroup);
                requestAddlob.addProperty("Description", Description);
                // adding method property here serially
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
