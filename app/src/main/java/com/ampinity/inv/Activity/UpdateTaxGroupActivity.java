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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class UpdateTaxGroupActivity extends AppCompatActivity {

    String lobid;
    Button idButtonCancel,idButtonSave;
    ConnectionDetector cd;
    Spinner spinnerTaxGroup;
    String [] TaxName;
    int length;
    Bundle bundle;

    EditText idEditTextIDGST,idEditTextRate,idEditTextCGST,idEditTextSGST,idEditTextIGST;
    String stridEditTextIDGST,stridEditTextRate,stridEditTextCGST,stridEditTextSGST,stridEditTextIGST,selcetedspinnerTaxGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tax_group);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Tax Group Details");
        spinnerTaxGroup = (Spinner) findViewById(R.id.spinnerTaxGroup);



        idEditTextIDGST=(EditText)findViewById(R.id.idEditTextIDGST);
        idEditTextRate=(EditText)findViewById(R.id.idEditTextRate);
        idEditTextCGST=(EditText)findViewById(R.id.idEditTextCGST);
        idEditTextSGST=(EditText)findViewById(R.id.idEditTextSGST);
        idEditTextIGST=(EditText)findViewById(R.id.idEditTextIGST);
        idButtonCancel=(Button)findViewById(R.id.idButtonCancel);
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
                validate();
            }
        });

        bundle = new Bundle();
        bundle = getIntent().getExtras();


        stridEditTextIDGST= bundle.getString("ID").toString();
        stridEditTextRate= bundle.getString("Rate").toString();
        stridEditTextCGST= bundle.getString("CGST").toString();
        stridEditTextSGST= bundle.getString("SGST").toString();
        stridEditTextIGST= bundle.getString("IGST").toString();
        selcetedspinnerTaxGroup= bundle.getString("Name").toString();
        idEditTextIDGST.setText(stridEditTextIDGST);
        idEditTextRate.setText(stridEditTextRate);
                idEditTextCGST.setText(stridEditTextCGST);
        idEditTextSGST.setText(stridEditTextSGST);
                idEditTextIGST.setText(stridEditTextIGST);



        spinnerTaxGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selcetedspinnerTaxGroup=spinnerTaxGroup.getSelectedItem().toString();
                if(selcetedspinnerTaxGroup.equals("Select TaxName"))
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

        });
        new getlobspinnerdataAsyncTask().execute();
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
        cd=new ConnectionDetector(getApplicationContext());



        if (!cd.isConnectingToInternet()) {
            Toast.makeText(getApplicationContext(), "Network Unavailable", Toast.LENGTH_SHORT).show();

        }
        else
        if (idEditTextRate.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Rate",Toast.LENGTH_SHORT).show();
        }else
        if (selcetedspinnerTaxGroup.equals("Select TaxName")){
            Toast.makeText(this,"Please Select TaxName",Toast.LENGTH_SHORT).show();
        }
        else{
            stridEditTextIDGST=idEditTextIDGST.getText().toString();
            stridEditTextRate=idEditTextRate.getText().toString();
            stridEditTextCGST=idEditTextCGST.getText().toString();
            stridEditTextSGST=idEditTextSGST.getText().toString();
            stridEditTextIGST=idEditTextIGST.getText().toString();
            selcetedspinnerTaxGroup=spinnerTaxGroup.getSelectedItem().toString();


            new UpdateTaxGroupInsertDetailtAsyncTask().execute();
        }
    }

    public class UpdateTaxGroupInsertDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    {

        ProgressDialog dialog;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateTaxGroupActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            finish();
            Toast.makeText(UpdateTaxGroupActivity.this,"Updated Succesfully",Toast.LENGTH_SHORT).show();
            // new AddAttributeDetailtAsyncTask().execute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            String url = Config.Url+"gstdetails.asmx";
            String soapactionaddlob ="http://tempuri.org/Update_New";
            String methodname="Update_New";
            String namespacesaalob="http://tempuri.org/";
            try {


                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);







                requestAddlob.addProperty("ID",stridEditTextIDGST );
                requestAddlob.addProperty("TaxGroupName",selcetedspinnerTaxGroup );
                requestAddlob.addProperty("SGST", stridEditTextSGST);
                requestAddlob.addProperty("CGST", stridEditTextCGST);
                requestAddlob.addProperty("IGST",stridEditTextIGST );
                requestAddlob.addProperty("GSTRate", stridEditTextRate);


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


    public class getlobspinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateTaxGroupActivity.this);
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
                TaxName=new String[length];

                int j=0;
                TaxName[0]="Select TaxName";

                //  Namearray = new ArrayList<String>();
                for (int i = 0; i < length-1; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    j=j+1;
//                    Namearray.add (jsonObject.getString("Name"));
                    TaxName[j] = jsonObject.getString("TaxName");
//
//
//
//
                    Log.e("TaxName", "TaxName" + TaxName[j]);



                }






                final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(UpdateTaxGroupActivity.this, android.R.layout.simple_spinner_item,
                        TaxName);
                spinnerTaxGroup.setAdapter(adapter1);


                if (selcetedspinnerTaxGroup != null) {
                    int spinnerPosition = adapter1.getPosition(selcetedspinnerTaxGroup);
                    spinnerTaxGroup.setSelection(spinnerPosition);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url =Config.Url+"gstdetails.asmx";


            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "BindTaxGroup");
            // request.addProperty("Celsius", "48"); // adding method property here serially

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            HttpTransportSE httpTransport = new HttpTransportSE(url);
            httpTransport.debug = true;

            try {
                httpTransport.call("http://tempuri.org/BindTaxGroup", envelope);

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
