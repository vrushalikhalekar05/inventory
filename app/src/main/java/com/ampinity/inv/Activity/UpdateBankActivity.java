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

public class UpdateBankActivity extends AppCompatActivity {

    Button idButtonCancel,idButtonSave;
    String lobid;
    ConnectionDetector cd;
    EditText idEditTextIDBank,idEditTextBankName,idEditTextBranchName,idEditTextIFSCCode;
    String bankname,bankbranch,ifsc,id;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bank);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Bank Details");

        idEditTextIDBank=(EditText)findViewById(R.id.idEditTextIDBank);
        idEditTextBankName=(EditText)findViewById(R.id.idEditTextBankName);
        idEditTextBranchName=(EditText)findViewById(R.id.idEditTextBranchName);
        idEditTextIFSCCode=(EditText)findViewById(R.id.idEditTextIFSCCode);

        bundle = new Bundle();
        bundle = getIntent().getExtras();
        id = bundle.getString("Bankid").toString();
        bankname = bundle.getString("BankName").toString();
        bankbranch = bundle.getString("BranchName").toString();
        ifsc= bundle.getString("IFSC").toString();

        idEditTextIDBank.setText(id);
        idEditTextBankName.setText(bankname);
        idEditTextBranchName.setText(bankbranch);
        idEditTextIFSCCode.setText(ifsc);
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
        if (idEditTextBankName.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Bank Name",Toast.LENGTH_SHORT).show();
        }
        else
        if (idEditTextBranchName.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Branch Name",Toast.LENGTH_SHORT).show();
        }
        if (idEditTextIFSCCode.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter IFSC Code",Toast.LENGTH_SHORT).show();
        }
        else
            {
                bankname=idEditTextBankName.getText().toString();
                bankbranch=idEditTextBranchName.getText().toString();
                ifsc=idEditTextIFSCCode.getText().toString();
                new UpdateBankInsertDetailtAsyncTask().execute();
            }
    }
    public class UpdateBankInsertDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateBankActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            finish();
            Toast.makeText(UpdateBankActivity.this,"Updated Succesfully",Toast.LENGTH_SHORT).show();
            // new AddAttributeDetailtAsyncTask().execute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String url = Config.Url+"BankDetails.asmx";
            String soapactionaddlob ="http://tempuri.org/Update_New";
            String methodname="Update_New";
            String namespacesaalob="http://tempuri.org/";
            try {
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                requestAddlob.addProperty("ID",id);
                requestAddlob.addProperty("BankName",bankname );
                requestAddlob.addProperty("BranchName", bankbranch);
                requestAddlob.addProperty("IFSCCode", ifsc);

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
