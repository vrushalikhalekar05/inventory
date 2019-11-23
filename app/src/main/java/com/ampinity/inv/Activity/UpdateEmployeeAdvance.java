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

public class UpdateEmployeeAdvance extends AppCompatActivity {
    Button idButtonCancel,idButtonSave;
    String lobid;
    ConnectionDetector cd;

    EditText idEditTextID,idEditTextName,idEditTextSalary,idEditTotalAdvance,idEditTextRemainSal;
    String Name,Salary,TotalAdvance,RemainingSalary,Id;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee_advance);

        idEditTextID=(EditText)findViewById(R.id.idEditTextIDLOB);
        idEditTextName=(EditText)findViewById(R.id.idEditTextName);
        idEditTextSalary=(EditText)findViewById(R.id.idEditTextSalary);
        idEditTotalAdvance=(EditText)findViewById(R.id.idEditTotalAdvance);
        idEditTextRemainSal= (EditText) findViewById(R.id.idEditTextRemainSal);
        idButtonCancel=(Button)findViewById(R.id.idButtonCancel);
        idButtonSave=(Button)findViewById(R.id.idButtonSave);


        bundle = new Bundle();
        bundle = getIntent().getExtras();

        Id = bundle.getString("Id").toString();
        Name = bundle.getString("Name").toString();
        Salary = bundle.getString("Salary").toString();
        TotalAdvance= bundle.getString("TotalAdvancement").toString();
        RemainingSalary=bundle.getString("RemSalary").toString();


        idEditTextID.setText(Id);
        idEditTextName.setText( Name);
        idEditTextSalary.setText(Salary);
        idEditTotalAdvance.setText( TotalAdvance);
        idEditTextRemainSal.setText( RemainingSalary);



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
        if (idEditTextName.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter  Name",Toast.LENGTH_SHORT).show();
        }
        else
        if (idEditTextSalary.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Salary",Toast.LENGTH_SHORT).show();
        }else
        if (idEditTotalAdvance.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Total Advance",Toast.LENGTH_SHORT).show();
        }else
        if (  idEditTextRemainSal.getText().toString().length()==0) {
            Toast.makeText(this, "Please Enter Remaining Salary", Toast.LENGTH_SHORT).show();
        }
            else
        {
           Name=idEditTextName.getText().toString();
            Salary=idEditTextSalary.getText().toString();
            TotalAdvance=idEditTotalAdvance.getText().toString();
            RemainingSalary=idEditTextRemainSal.getText().toString();

            new UpdateBankInsertDetailtAsyncTask().execute();
        }
    }

    public class UpdateBankInsertDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateEmployeeAdvance.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            finish();
            Toast.makeText(UpdateEmployeeAdvance.this,"Updated Succesfully",Toast.LENGTH_SHORT).show();
            // new AddAttributeDetailtAsyncTask().execute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String url = Config.Url+"EmployeeAdvance.asmx";
            String soapactionaddlob ="http://tempuri.org/Update";
            String methodname="Update";
            String namespacesaalob="http://tempuri.org/";
            try {
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                requestAddlob.addProperty("Id",Id);
                requestAddlob.addProperty("EmployeeName",Name);
                requestAddlob.addProperty("Salary", Salary);
                requestAddlob.addProperty("TotalAdvance", TotalAdvance);
                requestAddlob.addProperty("RemainingSalary", RemainingSalary);

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
