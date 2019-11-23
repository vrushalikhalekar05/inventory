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

public class UpdateEmployeeAttendance extends AppCompatActivity {
    Button idButtonCancel,idButtonSave;
    EditText idEditTextName,idEditTextId,idEmployeeAttendance,idEmployeeDate;
    String lobid;
    ConnectionDetector cd;
    String Id,Name,CompanyID,Attendance,Date;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee_attendance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Employee Attendance Details");

        bundle = new Bundle();
        bundle = getIntent().getExtras();
        lobid = bundle.getString("Id").toString();
        Name = bundle.getString("Name").toString();
        Attendance=bundle.getString("Attendance").toString();
        Date=bundle.getString("Date").toString();

        idButtonCancel=(Button)findViewById(R.id.idButtonCancel);
        idButtonSave=(Button)findViewById(R.id.idButtonSave);
        idEditTextName=(EditText)findViewById(R.id.idEditTextName);
        idEmployeeAttendance= (EditText) findViewById(R.id.idEditTextAttendance);
        idEmployeeDate= (EditText) findViewById(R.id.idEditTextAttendance);
        idEditTextId=(EditText)findViewById(R.id.idEditTextIDOffer);
        idEditTextId.setText(lobid);
        idEditTextName.setText(Name);
        idEmployeeAttendance.setText(Attendance);
        idEmployeeDate.setText(Date);


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
        if (idEditTextId.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Id",Toast.LENGTH_SHORT).show();
        }
        else
        if (idEditTextName.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter  Name",Toast.LENGTH_SHORT).show();
        }
        else if(idEmployeeAttendance.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Attendance",Toast.LENGTH_SHORT).show();

            }
        else if(idEmployeeDate.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Date",Toast.LENGTH_SHORT).show();

            }
        else{
            Id=idEditTextId.getText().toString();
          Name=idEditTextName.getText().toString();
          Attendance=idEmployeeAttendance.getText().toString();
          Date=idEmployeeDate.getText().toString();
          new AddLobtestAsyncTask().execute();
        }
    }
    public class AddLobtestAsyncTask extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateEmployeeAttendance.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(UpdateEmployeeAttendance.this,"Updated Succesfully",Toast.LENGTH_SHORT).show();
            finish();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String urlupdate = Config.Url+"EmployeeAttendace.asmx";
            String soapactionupdatelob ="http://tempuri.org/Update";
            String methodnameupdate="Update";
            String namespacesaupdatelob="http://tempuri.org/";
            try {
                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"
                SoapObject requestupdatelob = new SoapObject(namespacesaupdatelob, methodnameupdate);
                int lobint= Integer.parseInt(lobid);
                //            PropertyInfo LobIdp=new PropertyInfo();
//            LobIdp.setName("LobId");
//            LobIdp.setValue(lobid);
//                LobIdp.setType(String.class);
//                requestupdatelob.addProperty(LobIdp);
//
//            PropertyInfo Namep=new PropertyInfo();
//            Namep.setName("Name");
//            Namep.setValue(Name);
//                Namep.setType(String.class);
//                requestupdatelob.addProperty(Namep);
//
//            PropertyInfo TotalAttributep=new PropertyInfo();
//            TotalAttributep.setName("TotalAttribute");
//            TotalAttributep.setValue(TotalAttribute);
//                TotalAttributep.setType(String.class);
//                requestupdatelob.addProperty(TotalAttributep);


                requestupdatelob.addProperty("ID", lobid);
                requestupdatelob.addProperty("Name", Name);
                requestupdatelob.addProperty("Attendance",Attendance);
                requestupdatelob.addProperty("CompanyID","1");


                // requestAddlob.addProperty("CreatedBy", "CreatedBy");
                // requestAddlob.addProperty("UpdatedBy", "UpdatedBy");
                // adding method property here serially


                SoapSerializationEnvelope envelopeAddlob = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelopeAddlob.encodingStyle = "utf-8";
                envelopeAddlob.implicitTypes = true;
                envelopeAddlob.setOutputSoapObject(requestupdatelob);
                envelopeAddlob.dotNet = true;
                Object result1=null;


                try {
                    HttpTransportSE httpTransportAddlob = new HttpTransportSE(urlupdate);
                    httpTransportAddlob.debug = true;
                    httpTransportAddlob.call(soapactionupdatelob, envelopeAddlob);
                    result1 = (Object) envelopeAddlob.getResponse();
                    Log.i("RESPONSE after add", String.valueOf(result1));


                }
                catch (Exception e) {
                    //   Log.e("IOLOG", e.getMessage());
                    e.printStackTrace();
                    //                dialog.dismiss();
                }


            }
            catch (Exception e) {
                //   Log.e("IOLOG", e.getMessage());
                e.printStackTrace();
                //                dialog.dismiss();

            }
            return null;
        }
    }
    }

