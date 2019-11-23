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
import com.ampinity.inv.Utility.ConnectionDetector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class UpdateGstActivity extends AppCompatActivity {

    Button idButtonCancel,idButtonSave;
    EditText idEditTextIDGST,idEditTextRate,idEditTextGSTName;
    String id;
    ConnectionDetector cd;
    String Rate,GstName;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_gst);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update TaxGroup Details");



        bundle = new Bundle();
        bundle = getIntent().getExtras();
        id = bundle.getString("id").toString();
        Rate = bundle.getString("rate").toString();
        GstName = bundle.getString("gsts").toString();


        idButtonCancel=(Button)findViewById(R.id.idButtonCancel);
        idButtonSave=(Button)findViewById(R.id.idButtonSave);
        idEditTextRate=(EditText)findViewById(R.id.idEditTextRate);
        idEditTextGSTName=(EditText)findViewById(R.id.idEditTextGSTName);
        idEditTextIDGST=(EditText)findViewById(R.id.idEditTextIDGST);
        idEditTextIDGST.setText(id);
        idEditTextGSTName.setText(GstName);
        idEditTextRate.setText(Rate);


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
        if (idEditTextGSTName.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter GST Name",Toast.LENGTH_SHORT).show();
        }
        else{
            Rate=idEditTextRate.getText().toString();
            GstName=idEditTextGSTName.getText().toString();
            new UpdateGstTaxGroupInsertDetailtAsyncTask().execute();
        }
    }

    public class UpdateGstTaxGroupInsertDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    {

        ProgressDialog dialog;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateGstActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            // new AddAttributeDetailtAsyncTask().execute();
            Toast.makeText(UpdateGstActivity.this,"Updated Succesfully",Toast.LENGTH_SHORT).show();
            finish();

        }

        @Override
        protected Void doInBackground(Void... params) {

            String url = Config.Url+"TaxGroup.asmx";
            String soapactionaddlob ="http://tempuri.org/Update_New";
            String methodname="Update_New";
            String namespacesaalob="http://tempuri.org/";
            try {


                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);







                requestAddlob.addProperty("ID", id);
                requestAddlob.addProperty("TaxName",GstName );
                requestAddlob.addProperty("Rate", Rate);

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
}
