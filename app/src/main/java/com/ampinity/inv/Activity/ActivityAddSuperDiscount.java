package com.ampinity.inv.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
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

public class ActivityAddSuperDiscount extends AppCompatActivity {
    private static final String SOAP_ACTION = "http://tempuri.org/GetAutonumber";
    private static final String METHOD_NAME = "GetAutonumber";
    private static final String NAMESPACE = "http://tempuri.org/";

    Button buttonSave, buttonCancel;
    EditText idEditTextOffername,idEditTextOfferId;
    TextView textViewTodate, textViewfromDate;
    DatePicker datePickerToDate, datePickerFromdate, picker;
    String lobid,Name,SchemeName,CompanyID,TotalAttribute,DisPer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_super_discount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Super Discount");
        buttonSave = (Button) findViewById(R.id.idButtonSave);
        buttonCancel = (Button) findViewById(R.id.idButtonCancel);
        idEditTextOffername = (EditText) findViewById(R.id.idEditTextOfferName);
        idEditTextOfferId= (EditText) findViewById(R.id.idEditTextIDLOB);
        buttonCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
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
            dialog = new ProgressDialog(ActivityAddSuperDiscount.this);
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
                if(lobid.equals("null")){
                    idEditTextOfferId.setText("1");
                }
                else {
                    int lobidd= Integer.parseInt(lobid)+1;
                    idEditTextOfferId.setText(""+lobidd);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        }
        @Override
        protected String doInBackground(String... arg) {
            String URL = Config.Url+"SuperDiscount.asmx";
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
              //  Log.e("IOLOG", e.getMessage());
                Log.d("request!", "starting");
                dialog.dismiss();
               // e.printStackTrace();
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
              //  Log.e("XMLLOG", e.getMessage());
                Log.d("request!", "starting");
             //   e.printStackTrace();
                dialog.dismiss();
            } //send request
            Object  result = null;
            try {
                result = (Object) envelope.getResponse();
                Log.i("RESPONSE", String.valueOf(result));
                //                lobid=String.valueOf(result);
            } catch (SoapFault e) {
                // TODO Auto-generated catch block
            //    Log.e("SOAPLOG", e.getMessage());
                Log.d("request!", "starting");
              //  e.printStackTrace();
                dialog.dismiss();
            }
            return String.valueOf(result);
        }
        }
        public void ValidateLob() {
        if (idEditTextOffername.getText().toString().length() == 0) {
            Toast.makeText(this, "Please Enter Offer Name", Toast.LENGTH_SHORT).show();
        } else {
            Name = idEditTextOffername.getText().toString();
            new  AddoffernametestAsyncTask().execute();
        }
    }
    public class AddoffernametestAsyncTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(ActivityAddSuperDiscount.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);
            }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(ActivityAddSuperDiscount.this, "Added Succesfully", Toast.LENGTH_SHORT).show();
            finish();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String url = Config.Url +"Super Discount.asmx";
            String soapactionaddlob ="http://tempuri.org/Insert";
            String methodname ="Insert";

            String namespacesaalob = "http://tempuri.org/";
            try {
                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"
                // SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                int lobint = Integer.parseInt(lobid);
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
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                // requestAddlob.addProperty("LobId", 6);
                requestAddlob.addProperty("@id", lobid);
                // requestAddlob.addProperty("Name", Name);
                requestAddlob.addProperty("@SchemeName",SchemeName);
                requestAddlob.addProperty("@CompanyID",CompanyID);
                requestAddlob.addProperty("@DisPer",DisPer);
                //   requestAddlob.addProperty("TotalAttribute", TotalAttribute);
                // requestAddlob.addProperty("CreatedBy", "CreatedBy");
                // requestAddlob.addProperty("UpdatedBy", "UpdatedBy");
                // adding method property here serially
                SoapSerializationEnvelope envelopeAddlob = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelopeAddlob.encodingStyle = "utf-8";
                envelopeAddlob.implicitTypes = true;
                envelopeAddlob.setOutputSoapObject(requestAddlob);
                envelopeAddlob.dotNet = true;
                Object result1 = null;

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
