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

public class UpdateVenderTypeActivity extends AppCompatActivity {

    private static final String SOAP_ACTION = "http://tempuri.org/GetAutonumber";
    private static final String METHOD_NAME = "GetAutonumber";
    private static final String NAMESPACE = "http://tempuri.org/";
    EditText idEditTextIDVenderType,idEditTextVenderGroup,idEditTextDescription;
    Button idButtonSave;
    String id,VGroup,Description;
    Bundle bundle;
    ConnectionDetector cd;

    Button idButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_vender_type);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Vender Type Details");



        idEditTextIDVenderType=(EditText)findViewById(R.id.idEditTextIDVenderType);
        idEditTextVenderGroup=(EditText)findViewById(R.id.idEditTextVenderGroup);
        idEditTextDescription=(EditText)findViewById(R.id.idEditTextDescription);


        idButtonSave=(Button)findViewById(R.id.idButtonSave);
        idButtonCancel=(Button)findViewById(R.id.idButtonCancel);
        idButtonCancel.setOnClickListener(new View.OnClickListener() {
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

        bundle = new Bundle();
        bundle = getIntent().getExtras();


        id= bundle.getString("id").toString();
        VGroup = bundle.getString("VenderGroup").toString();
        Description= bundle.getString("Description").toString();

        idEditTextIDVenderType.setText(id);
        idEditTextVenderGroup.setText(VGroup);
        idEditTextDescription.setText(Description);

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




    public void Validate()
    {
        cd=new ConnectionDetector(getApplicationContext());
        if (!cd.isConnectingToInternet()){
            Toast.makeText(getApplicationContext(), "Network Unavailable", Toast.LENGTH_SHORT).show();
        }
        else
        if (idEditTextVenderGroup.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Vender Group",Toast.LENGTH_SHORT).show();
        }else
        if (idEditTextDescription.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Description",Toast.LENGTH_SHORT).show();
        }
        else{
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
            dialog = new ProgressDialog(UpdateVenderTypeActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(UpdateVenderTypeActivity.this,"Updated Succesfully",Toast.LENGTH_SHORT).show();

            finish();
        }

        @Override
        protected Void doInBackground(Void... params) {

            String url = Config.Url+"vendertype.asmx";
            String soapactionaddlob ="http://tempuri.org/Update_New";
            String methodname="Update_New";
            String namespacesaalob="http://tempuri.org/";
            try {

                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);



                int lobint= Integer.parseInt(id);






                requestAddlob.addProperty("ID", id);
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
