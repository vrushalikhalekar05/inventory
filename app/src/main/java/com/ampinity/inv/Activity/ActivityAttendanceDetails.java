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
import com.ampinity.inv.Utility.ConnectionDetector;

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
import java.util.Calendar;

public class ActivityAttendanceDetails extends AppCompatActivity {
    TextView editTextdate;
    Calendar calendar;
    int mYear, mMonth, mDay;
    Button idButtonCancel, idButtonSave;
    String lobid;
    EditText idEditTextID, idEditTextName, idEditTextAttendance, idEditTextDate;
    ConnectionDetector cd;
    String ID, Name, Attendance, Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Employee Attendance");

        idEditTextID = (EditText) findViewById(R.id.idEditTextIDLOB);
        idEditTextName = (EditText) findViewById(R.id.idEditTextLobName);
        idEditTextAttendance = (EditText) findViewById(R.id.idEditTextAttendance);
        editTextdate = (TextView) findViewById(R.id.idEditTextDate);

        idButtonSave = (Button) findViewById(R.id.idButtonSave);
        idButtonCancel = (Button) findViewById(R.id.idButtonCancel);

        calendar = Calendar.getInstance();
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);
        mMonth = mMonth + 1;

        editTextdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityAttendanceDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        editTextdate.setText(year + "-" + month + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }
        });
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
        new getBankidAsyncTask().execute();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
    public void validate() {
        cd = new ConnectionDetector(getApplicationContext());
        if (!cd.isConnectingToInternet()) {
            Toast.makeText(getApplicationContext(), "Network Unavailable", Toast.LENGTH_SHORT).show();
        } else if (idEditTextName.getText().toString().length() == 0) {
            Toast.makeText(this, "Please Enter  Name", Toast.LENGTH_SHORT).show();
        } else if (idEditTextAttendance.getText().toString().length() == 0) {
            Toast.makeText(this, "Please Enter Attendance Name", Toast.LENGTH_SHORT).show();
        }
        if (editTextdate.getText().toString().length() == 0) {
            Toast.makeText(this, "Please Enter Date", Toast.LENGTH_SHORT).show();
        } else {
            Name = idEditTextName.getText().toString();
            Attendance = idEditTextAttendance.getText().toString();
            Date = editTextdate.getText().toString();
            new AddBankInsertDetailtAsyncTask().execute();
        }

    }
    //getid
    public class getBankidAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(ActivityAttendanceDetails.this);
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
                lobid = jsonObject.getString("Column1");
                int idd = Integer.parseInt(lobid) + 1;
                if (lobid.equals("null")) {
                    idEditTextID.setText("1");
                } else {
                    idEditTextID.setText("" + idd);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        }
        @Override
        protected String doInBackground(String... arg) {
            String URL = Config.Url +"EmployeeAttendance.asmx";
            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "GetAutonumber");
            // request.addProperty("Celsius", "48"); // adding method property here serially
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;
            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.debug = true;
            try {
                httpTransport.call("http://tempuri.org/GetAutonumber", envelope);
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
            Object result = null;
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
    public class AddBankInsertDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(ActivityAttendanceDetails.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            finish();
            Toast.makeText(ActivityAttendanceDetails.this,"Added Succesfully",Toast.LENGTH_SHORT).show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String url =Config.Url+"EmployeeAttendance.asmx";
            String soapactionaddlob ="http://tempuri.org/Insert";
            String methodname="Insert";
            String namespacesaalob="http://tempuri.org/";
            try {
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                requestAddlob.addProperty("EmployeeName",Name );
                requestAddlob.addProperty("Attendance",Attendance);
                requestAddlob.addProperty("AttDate", Date);
               requestAddlob.addProperty("CompanyID","1");

                // requestAddlob.addProperty("UpdatedBy", "UpdatedBy");
                // adding method property here serially


                SoapSerializationEnvelope envelopeAddlob = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelopeAddlob.encodingStyle  = "utf-8";
                envelopeAddlob.implicitTypes  = true;
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


                    e.printStackTrace();

                }

            } catch (Exception e) {


                e.printStackTrace();

            }

            return null;

        }


    }
}
