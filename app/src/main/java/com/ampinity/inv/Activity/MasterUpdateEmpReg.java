package com.ampinity.inv.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.Utility.ConnectionDetector;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/*import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;*/
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.ampinity.inv.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MasterUpdateEmpReg extends AppCompatActivity {
    Button idButtonCancel,idButtonSave;

    ConnectionDetector cd;
    EditText idEditTextID,idEditTextName,idEditTextAddress,idEditTextState,idEditTextCity,idEditTextPhoneNo,idEditTextEmail,idEditTextSalary,idEditTextTarget;
    String empid,empname,empaddress,empstate,empcity,empphone,empemail,empsalary,emptarget;
    String lobid;
    int idd;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_update_emp_reg);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Employee Details");

        bundle = new Bundle();
        bundle = getIntent().getExtras();
        empid = bundle.getString("Id").toString();
        empname = bundle.getString("Name").toString();
        empaddress = bundle.getString("Address").toString();
        empstate = bundle.getString("State").toString();
        empcity = bundle.getString("City").toString();
        empphone = bundle.getString("Phone").toString();
        empemail = bundle.getString("Email").toString();
        empsalary = bundle.getString("Salary").toString();
        emptarget = bundle.getString("Target").toString();

        idEditTextID=(EditText) findViewById(R.id.idEditTextID);
        idEditTextName=(EditText) findViewById(R.id.idEditTextName);
        idEditTextAddress=(EditText) findViewById(R.id.idEditTextAddress);
        idEditTextState=(EditText) findViewById(R.id.idEditTextState);
        idEditTextCity=(EditText) findViewById(R.id.idEditTextCity);
        idEditTextPhoneNo=(EditText) findViewById(R.id.idEditTextPhoneNo);
        idEditTextEmail=(EditText) findViewById(R.id.idEditTextEmail);
        idEditTextSalary=(EditText) findViewById(R.id.idEditTextSalary);
        idEditTextTarget=(EditText) findViewById(R.id.idEditTextTarget);

        idEditTextID.setText(empid);
        idEditTextName.setText(empname);
        idEditTextAddress.setText(empaddress);
        idEditTextState.setText(empstate);
        idEditTextCity.setText(empcity);
        idEditTextPhoneNo.setText(empphone);
        idEditTextEmail.setText(empemail);
        idEditTextSalary.setText(empsalary);
        idEditTextTarget.setText(emptarget);

        idButtonCancel=(Button) findViewById(R.id.idButtonCancel);
        idButtonSave=(Button) findViewById(R.id.idButtonSave);
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
        /*else
        if (idEditTextRate.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Rate",Toast.LENGTH_SHORT).show();
        }else
        if (idEditTextGSTName.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter GST Name",Toast.LENGTH_SHORT).show();
        }*/
        else{
            idd=Integer.parseInt(empid);
            empname=idEditTextName.getText().toString();
            empaddress=idEditTextAddress.getText().toString();
            empstate=idEditTextState.getText().toString();
            empcity=idEditTextCity.getText().toString();
            empphone=idEditTextPhoneNo.getText().toString();
            empemail=idEditTextEmail.getText().toString();
            empsalary=idEditTextSalary.getText().toString();
            emptarget=idEditTextTarget.getText().toString();

            new UpdateEmpRegActivity().execute();
        }
    }

    public class UpdateEmpRegActivity extends AsyncTask<Void, Void, Void>
    {

        ProgressDialog dialog;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MasterUpdateEmpReg.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            // new AddAttributeDetailtAsyncTask().execute();
            Toast.makeText(MasterUpdateEmpReg.this,"Updated Succesfully",Toast.LENGTH_SHORT).show();
            finish();

        }

        @Override
        protected Void doInBackground(Void... params) {

            String url = Config.Url+"empregi.asmx";
            String soapactionaddlob ="http://tempuri.org/Update_New";
            String methodname="Update_New";
            String namespacesaalob="http://tempuri.org/";
            try {


                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);

                requestAddlob.addProperty("id", idd);
                requestAddlob.addProperty("EmployeeName",empname );
                requestAddlob.addProperty("Address", empaddress);
                requestAddlob.addProperty("State", empstate);
                requestAddlob.addProperty("City", empcity);
                requestAddlob.addProperty("PhoneNo", empphone);
                requestAddlob.addProperty("Email", empemail);
                requestAddlob.addProperty("Salary", empsalary);
                requestAddlob.addProperty("BasicTarget", emptarget);
                requestAddlob.addProperty("WorkingDays", "2");

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
