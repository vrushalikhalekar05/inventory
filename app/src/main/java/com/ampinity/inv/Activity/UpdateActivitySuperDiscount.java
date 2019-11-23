package com.ampinity.inv.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
/*
import android.support.v7.app.AppCompatActivity;
*/
import android.os.Bundle;
import android.util.Log;
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

public class UpdateActivitySuperDiscount extends AppCompatActivity {
    Button idButtonCancel,idButtonSave;
    String lobid;
    Bundle bundle;
    ConnectionDetector cd;
    EditText idEditTextId,idEditOfferName,idEditTextDisper;
    String offername,Disper,ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_update_super_discount3);
        idEditTextId=(EditText)findViewById(R.id.idEditTextIDLOB);
        idEditOfferName=(EditText)findViewById(R.id.idEditTextLobName);
        idEditTextDisper=(EditText)findViewById(R.id.idEditTextDiscountPercentage);

        idButtonCancel=(Button)findViewById(R.id.idButtonCancel);
        idButtonSave=(Button)findViewById(R.id.idButtonSave);

        bundle = new Bundle();
        bundle = getIntent().getExtras();

        offername = bundle.getString("Name").toString();
        Disper = bundle.getString("DiscountPercentage").toString();
        ID = bundle.getString("Id").toString();

        idEditTextId.setText(ID);
        idEditOfferName.setText(offername);
        idEditTextDisper.setText(Disper);

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
    public void validate()
    {
        cd=new ConnectionDetector(getApplicationContext());
        if (!cd.isConnectingToInternet()) {
            Toast.makeText(getApplicationContext(), "Network Unavailable", Toast.LENGTH_SHORT).show();
        }
        else
        if (idEditOfferName.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Offer Name",Toast.LENGTH_SHORT).show();
        }else
        if (idEditTextDisper.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Discount Percentage",Toast.LENGTH_SHORT).show();
        }

        else{
            offername=idEditOfferName.getText().toString();
            Disper=idEditTextDisper.getText().toString();

            new UpdateBankInsertDetailtAsyncTask().execute();
        }

    }
    public class UpdateBankInsertDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateActivitySuperDiscount.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            finish();
            Toast.makeText(UpdateActivitySuperDiscount.this,"Updated Succesfully",Toast.LENGTH_SHORT).show();
            // new AddAttributeDetailtAsyncTask().execute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String url = Config.Url+"SuperDiscount.asmx";
            String soapactionaddlob ="http://tempuri.org/Update";
            String methodname="Update";
            String namespacesaalob="http://tempuri.org/";
            try {
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                requestAddlob.addProperty("Id",ID);
                requestAddlob.addProperty("SchemeName",offername );
                requestAddlob.addProperty("Disper",  Disper);
                requestAddlob.addProperty("CompanyID",  "1");

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
