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
import android.widget.TextView;
import android.widget.Toast;

import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

public class AddLobActivity extends AppCompatActivity {
    private static final String SOAP_ACTION = "http://tempuri.org/GetAutonumber";
    private static final String METHOD_NAME = "GetAutonumber";
    private static final String NAMESPACE = "http://tempuri.org/";
    //EditText idEditTextIDLOB,idEditTextLobName,idEditTextTotalAttribute;
    EditText idEditTextLobName,idEditTextTotalAttribute;
    TextView idEditTextIDLOB;
    Button idButtonSave;
    String lobid;
    String Name,TotalAttribute;
    Button idButtonCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lob);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Add Lob Details");

        idButtonCancel=(Button)findViewById(R.id.idButtonCancel);

        idEditTextIDLOB=(TextView) findViewById(R.id.idEditTextIDLOB);

        idEditTextLobName=(EditText)findViewById(R.id.idEditTextLobName);

        idEditTextTotalAttribute=(EditText)findViewById(R.id.idEditTextTotalAttribute);

        idButtonSave=(Button)findViewById(R.id.idButtonSave);

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
        getlobid();
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
    public void getlobid()
    {
        new getLobidAsyncTask().execute();

        }
        public class getLobidAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddLobActivity.this);
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
                int lobidd= Integer.parseInt(lobid)+1;
                if(lobid.equals("null")){
                    idEditTextIDLOB.setText("1");
                }
                else {
                  // int lobidd= Integer.parseInt(lobid)+1;
                   idEditTextIDLOB.setText(""+lobidd);
                }
                }
            catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
            }
            @Override
            protected String doInBackground(String... arg) {
            String URL = Config.Url+"lob.asmx";
            //for linear parameter
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            // request.addProperty("Celsius", "48");
            // adding method property here serially
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
        public void ValidateLob() {
        if (idEditTextLobName.getText().toString().length()==0){
           Toast.makeText(this,"Please Enter Lob Name",Toast.LENGTH_SHORT).show();
       }
       else
       if (idEditTextTotalAttribute.getText().toString().length()==0){
           Toast.makeText(this,"Please Enter Total Attribute",Toast.LENGTH_SHORT).show();
       }
       else
           {
           Name=idEditTextLobName.getText().toString();
           TotalAttribute=idEditTextTotalAttribute.getText().toString();
           new AddLobtestAsyncTask().execute();
       }
   }
   public class AddLobAsyncTask extends AsyncTask<Void, Void, Void> {
             ProgressDialog dialog;
             @Override
            protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddLobActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            dialog.show();
        }
            @Override
            protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(AddLobActivity.this,"Added Succesfully",Toast.LENGTH_SHORT).show();
            getlobid();
            }
            @Override
            protected Void doInBackground(Void... args) {
            String url="http://ampinityinventory.ampinityit.com/";
            String soapactionaddlob = "http://tempuri.org/Insert_LobDetails";
            String methodname="Insert_LobDetails";
            String namespacesaalob="http://tempuri.org/";
            try {
                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"
            //   SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                int lobint= Integer.parseInt(lobid);
                //            PropertyInfo LobIdp=new PropertyInfo();
//            LobIdp.setName("LobId");
//            LobIdp.setValue(lobint);
//
//            requestAddlob.addProperty(LobIdp);
//
//            PropertyInfo Namep=new PropertyInfo();
//            Namep.setName("Name");
//            Namep.setValue(Name);
//                Namep.setType(String.class);
//                requestAddlob.addProperty(Namep);
//
//            PropertyInfo TotalAttributep=new PropertyInfo();
//            TotalAttributep.setName("TotalAttribute");
//            TotalAttributep.setValue(TotalAttribute);
//                TotalAttributep.setType(String.class);
//            requestAddlob.addProperty(TotalAttributep);
//
//            PropertyInfo CreatedByp=new PropertyInfo();
//            CreatedByp.setName("CreatedBy");
//            CreatedByp.setValue("CreatedBy");
//
//            requestAddlob.addProperty(CreatedByp);
//
//            PropertyInfo UpdatedByp=new PropertyInfo();
//            UpdatedByp.setName("UpdatedBy");
//            UpdatedByp.setValue("UpdatedBy");
//
//            requestAddlob.addProperty(UpdatedByp);
// <MilkatdaracheNav>ritesh</MilkatdaracheNav>
//      <milatno>1014</milatno>
//      <mobileno>9075385284</mobileno>
//      <takrarprakar>string</takrarprakar>
//      <Address>string</Address>
//      <takarar>string</takarar>
//      <photo>string</photo>
//      <CreatedBy>string</CreatedBy>


                SoapObject requestAddlob1 = new SoapObject(namespacesaalob, methodname);
                requestAddlob1.addProperty("LobId", lobid);
                requestAddlob1.addProperty("Name", Name);
                requestAddlob1.addProperty("TotalAttribute", TotalAttribute);
                requestAddlob1.addProperty("CreatedBy", "CreatedBy");
                requestAddlob1.addProperty("UpdatedBy", "UpdatedBy");

               // adding method property here serially

               SoapSerializationEnvelope envelopeAddlob = new SoapSerializationEnvelope(SoapEnvelope.VER12);
               envelopeAddlob.encodingStyle = "utf-8";
               envelopeAddlob.implicitTypes = true;
               envelopeAddlob.setOutputSoapObject(requestAddlob1);
               envelopeAddlob.dotNet = true;
               SoapPrimitive result1=null;

               try {
                   HttpTransportSE httpTransportAddlob = new HttpTransportSE(url);
                   httpTransportAddlob.debug = true;
                   httpTransportAddlob.call(soapactionaddlob, envelopeAddlob);
                   result1 = (SoapPrimitive) envelopeAddlob.getResponse();
                   Log.i("RESPONSE after add", String.valueOf(result1));

                   //     jsonResponce= result1.toString();
                   //     Log.i("RESPONSE after add", jsonResponce);

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

            public class AddLobtestAsyncTask extends AsyncTask<Void, Void, Void>
    {

        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddLobActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);
            }
            @Override
            protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(AddLobActivity.this,"Added Succesfully",Toast.LENGTH_SHORT).show();
            finish();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String url =Config.Url+"lob.asmx";
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
                int lobint= Integer.parseInt(lobid);

            //            PropertyInfo LobIdp=new PropertyInfo();
//            LobIdp.setName("LobId");
//            LobIdp.setValue(lobint);
//
//            requestAddlob.addProperty(LobIdp);
//
//            PropertyInfo Namep=new PropertyInfo();
//            Namep.setName("Name");
//            Namep.setValue(Name);
//                Namep.setType(String.class);
//                requestAddlob.addProperty(Namep);
//
//            PropertyInfo TotalAttributep=new PropertyInfo();
//            TotalAttributep.setName("TotalAttribute");
//            TotalAttributep.setValue(TotalAttribute);
//                TotalAttributep.setType(String.class);
//            requestAddlob.addProperty(TotalAttributep);
//
//            PropertyInfo CreatedByp=new PropertyInfo();
//            CreatedByp.setName("CreatedBy");
//            CreatedByp.setValue("CreatedBy");
//
//            requestAddlob.addProperty(CreatedByp);
//
//            PropertyInfo UpdatedByp=new PropertyInfo();
//            UpdatedByp.setName("UpdatedBy");
//            UpdatedByp.setValue("UpdatedBy");
//
//           requestAddlob.addProperty(UpdatedByp);
                // requestAddlob.addProperty("LobId", 6);
                requestAddlob.addProperty("Name", Name);
                requestAddlob.addProperty("TotalAttribute", TotalAttribute);

           // requestAddlob.addProperty("CreatedBy", "CreatedBy");
           // requestAddlob.addProperty("UpdatedBy", "UpdatedBy");
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
        @Override
    protected void onResume() {
        super.onResume();
        getlobid();
    }
}
