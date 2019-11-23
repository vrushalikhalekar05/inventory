package com.ampinity.inv.Activity;

import android.app.DatePickerDialog;
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
import java.util.Calendar;

public class ActivityAddDateWise extends AppCompatActivity {
    private static final String SOAP_ACTION = "http://tempuri.org/GetAutonumber";
    private static final String METHOD_NAME = "GetAutonumber";
    private static final String NAMESPACE = "http://tempuri.org/";
    Button buttonAddoffer;
    Button buttonSave,buttonCancel;
    EditText idEditTextOffername, idEditTextIDLOB,idEdiTTextPercentageOffer,textViewfromDate,textViewTodate;
  //  EditText textViewfromDate;
    Calendar calendar;
    String lobid,Name,TotalAttribute,PercentageOffer,FromDate,ToDate;
    int mYear, mMonth, mDay;
   // TextView textViewfromDate,textViewTodate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date_wise);
       // buttonAddoffer=findViewById(R.id.)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add DateWise Data");

        calendar=Calendar.getInstance();
        mDay=calendar.get(Calendar.DAY_OF_MONTH);
        mMonth=calendar.get(Calendar.MONTH);
        mYear=calendar.get(Calendar.YEAR);

        mMonth=mMonth+1;
        buttonSave= (Button) findViewById(R.id.idButtonSave);
        buttonCancel= (Button) findViewById(R.id.idButtonCancel);
        idEditTextIDLOB= (EditText) findViewById(R.id.idEditTextIDLOB);
        idEdiTTextPercentageOffer= (EditText) findViewById(R.id.idEditTextPercetageOffer);
        idEditTextOffername= (EditText) findViewById(R.id.idEditTextLobName);

       // idEditTextOffername= (EditText) findViewById(R.id.edttextOfferName);

        textViewTodate= (EditText) findViewById(R.id.idEditTextTODate);
        textViewfromDate= (EditText) findViewById(R.id.idEditTextFromDate);

      //  textViewToDate1= (TextView) findViewById(R.id.TextViewTodate);

        calendar=Calendar.getInstance();
        mDay=calendar.get(Calendar.DAY_OF_MONTH);
        mMonth=calendar.get(Calendar.MONTH);
        mYear=calendar.get(Calendar.YEAR);
        mMonth=mMonth+1;

        // textViewTodate.setText(mDay+"/"+mMonth+"/"+mYear);

        textViewTodate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(ActivityAddDateWise.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        textViewTodate.setText(dayOfMonth+"/"+month+"/"+year);

                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
                }
        });

       buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValidateLob();

               // Toast.makeText(ActivityAddDateWise.this, "click", Toast.LENGTH_SHORT).show();
            }
            });


        getlobid();

        textViewfromDate.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                DatePickerDialog datePickerDialog=new DatePickerDialog(ActivityAddDateWise.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        textViewfromDate.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },mYear,mMonth,mDay);

                datePickerDialog.show();
                }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        public void getlobid() {

        new getLobidAsyncTask().execute();

    }
         public class getLobidAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(ActivityAddDateWise.this);
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
                    idEditTextIDLOB.setText("1");
                }
                else {
                    int lobidd= Integer.parseInt(lobid)+1;
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
            String URL = Config.Url+"DatewiseDiscount.asmx";
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
        if (idEditTextOffername.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Offer Name",Toast.LENGTH_SHORT).show();
        }
        else if (idEdiTTextPercentageOffer.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Percentage Offer",Toast.LENGTH_SHORT).show();
            }
            else {
            Name=idEditTextOffername.getText().toString();
            PercentageOffer=idEdiTTextPercentageOffer.getText().toString();
            FromDate=textViewfromDate.getText().toString();
            ToDate=textViewTodate.getText().toString();
            new  AddLobAsyncTask().execute();

        }
        }
        public class AddLobAsyncTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(ActivityAddDateWise.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            dialog.show();
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(ActivityAddDateWise.this,"Added Succesfully",Toast.LENGTH_SHORT).show();
            getlobid();
        }
        @Override
        protected Void doInBackground(Void... args) {
            String url=Config.Url+"DatewiseDiscount.asmx";
            String soapactionaddlob = "http://tempuri.org/Insert";
            String methodname="Insert";
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
                requestAddlob1.addProperty("OfferName", Name);
                requestAddlob1.addProperty("DateFrom",FromDate);
                requestAddlob1.addProperty("DateTo",ToDate);
                requestAddlob1.addProperty("PercentageOffer",PercentageOffer);
                requestAddlob1.addProperty("CompanyID","1");
                //requestAddlob1.addProperty("TotalAttribute", TotalAttribute);
               // requestAddlob1.addProperty("CreatedBy", "CreatedBy");
              //  requestAddlob1.addProperty("UpdatedBy", "UpdatedBy");
                // adding method property here serially
                SoapSerializationEnvelope envelopeAddlob = new SoapSerializationEnvelope(SoapEnvelope.VER11);
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
  /*  public void AddDate(){
       DatePickerDialog datePickerDialog=new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener(){
           @Override
           public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=mMonth+1;
               textViewTodate.setText(dayOfMonth+"/"+month+"/"+year);

           }
       },mYear,mMonth,mDay);

       datePickerDialog.show();

   }*/

    }

