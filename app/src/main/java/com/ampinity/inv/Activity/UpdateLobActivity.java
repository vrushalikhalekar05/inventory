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
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class UpdateLobActivity extends AppCompatActivity {
    Bundle bundle;
    String lobid;
    String lobname;
    String totalattribute;
    EditText idEditTextIDLOB,idEditTextLobName,idEditTextTotalAttribute;
    Button idButtonCancel;
    Button idButtonSave;
    String Name,TotalAttribute;
    int Lobid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lob);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Lob Details");

        bundle = new Bundle();
        bundle = getIntent().getExtras();
        lobid = bundle.getString("Lobid").toString();
        lobname = bundle.getString("LobName").toString();
        totalattribute = bundle.getString("TotalAttribute").toString();

        Lobid= Integer.parseInt(bundle.getString("Lobid").toString());

        idButtonCancel=(Button)findViewById(R.id.idButtonCancel);
        idEditTextIDLOB=(EditText)findViewById(R.id.idEditTextIDLOB);
        idEditTextLobName=(EditText)findViewById(R.id.idEditTextLobName);
        idEditTextTotalAttribute=(EditText)findViewById(R.id.idEditTextTotalAttribute);
        idButtonSave=(Button)findViewById(R.id.idButtonSave);

        idEditTextIDLOB.setText(lobid);
        idEditTextLobName.setText(lobname);
        idEditTextTotalAttribute.setText(totalattribute);

        idButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        idButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateLob();
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

    public void ValidateLob()
    {

        if (idEditTextLobName.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Lob Name",Toast.LENGTH_SHORT).show();
        }else
        if (idEditTextTotalAttribute.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Total Attribute",Toast.LENGTH_SHORT).show();
        }
        else{
            Name=idEditTextLobName.getText().toString();
            TotalAttribute=idEditTextTotalAttribute.getText().toString();
            new AddLobtestAsyncTask().execute();
        }
    }

    public class AddLobtestAsyncTask extends AsyncTask<Void, Void, Void>
    {

        ProgressDialog dialog;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateLobActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(UpdateLobActivity.this,"Updated Succesfully",Toast.LENGTH_SHORT).show();

            finish();
        }

        @Override
        protected Void doInBackground(Void... params) {

            String urlupdate = Config.Url+"lob.asmx";
            String soapactionupdatelob ="http://tempuri.org/Update_New";
            String methodnameupdate="Update_New";
            String namespacesaupdatelob="http://tempuri.org/";
            try {

                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"
                SoapObject requestupdatelob = new SoapObject(namespacesaupdatelob, methodnameupdate);



                int lobint= Integer.parseInt(lobid);


//            PropertyInfo LobIdp=new PropertyInfo();
//            LobIdp.setName("LobId");
//            LobIdp.setValue(lobid);
//                LobIdp.setType(String.class);
//                requestupdatelob.addProperty(LobIdp);
//
//            PropertyInfo Namep=new PropertyInfo();
//            Namep.setName("Name");
//            Namep.setValue(Name);
//                Namep.setType(String.class);
//                requestupdatelob.addProperty(Namep);
//
//            PropertyInfo TotalAttributep=new PropertyInfo();
//            TotalAttributep.setName("TotalAttribute");
//            TotalAttributep.setValue(TotalAttribute);
//                TotalAttributep.setType(String.class);
//                requestupdatelob.addProperty(TotalAttributep);






                requestupdatelob.addProperty("LobId", lobid);
                requestupdatelob.addProperty("Name", Name);
                requestupdatelob.addProperty("TotalAttribute", TotalAttribute);
                // requestAddlob.addProperty("CreatedBy", "CreatedBy");
                // requestAddlob.addProperty("UpdatedBy", "UpdatedBy");

                // adding method property here serially

                SoapSerializationEnvelope envelopeAddlob = new SoapSerializationEnvelope(SoapEnvelope.VER11);


                envelopeAddlob.encodingStyle = "utf-8";
                envelopeAddlob.implicitTypes = true;
                envelopeAddlob.setOutputSoapObject(requestupdatelob);
                envelopeAddlob.dotNet = true;
                Object result1=null;

                try {
                    HttpTransportSE httpTransportAddlob = new HttpTransportSE(urlupdate);
                    httpTransportAddlob.debug = true;
                    httpTransportAddlob.call(soapactionupdatelob, envelopeAddlob);
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


    public void getlobid()
    {
        new getLobidAsyncTask().execute();

    }

    public class getLobidAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateLobActivity.this);
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
            idEditTextIDLOB.setText(lobid);
            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String URL = Config.Url+"lob.asmx";
            String SOAP_ACTION = "http://tempuri.org/GetData_ByID";
            String METHOD_NAME = "GetData_ByID";
            String NAMESPACE = "http://tempuri.org/";

            //for linear parameter
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("LobId", Lobid); // adding method property here serially

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
}
