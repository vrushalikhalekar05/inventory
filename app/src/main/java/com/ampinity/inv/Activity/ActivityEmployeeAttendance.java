package com.ampinity.inv.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v4.app.ActivityCompat;
/*import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;*/
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ampinity.inv.Model.DetailsOffer1;
import com.ampinity.inv.Model.EmployeeAttendanceDetails;
import com.ampinity.inv.Model.RackMasterDetails;
import com.ampinity.inv.Model.TableDataPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.adapter.AdapterEmployeeAttendance;
import com.ampinity.inv.adapter.AdapterOffer1;
import com.ampinity.inv.adapter.AdapterRackmaster;
import com.ampinity.inv.fragment.FragmentOffer1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
import java.util.ArrayList;

public class ActivityEmployeeAttendance extends AppCompatActivity {
        Button buttonAddEmployee;

     String sno;
     String CompanyID;
     int length;

       EmployeeAttendanceDetails employeeAttendanceDetails;
       AdapterEmployeeAttendance adapterEmployeeAttendance;
       ArrayList<EmployeeAttendanceDetails> employeeAttendanceDetailsArrayList=new ArrayList<>() ;
       AdapterEmployeeAttendance CompanyAdapte;

      private static final String SOAP_ACTION = "http://tempuri.org/GetData";
      private static final String METHOD_NAME = "GetData";
      private static final String NAMESPACE = "http://tempuri.org/";

      String[] Id;
      String[] EmployeeName;
      String[] Attendance;
      String[] AttDate;
      ListView lview;
      // int MY_PERMISSIONS_REQUEST_READ_CONTACTS;

    @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_attendance);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //   getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

           getSupportActionBar().setTitle("Add Employee Attendance");

        buttonAddEmployee= (Button) findViewById(R.id.ButtonEmployeeAttendance);

        lview = (ListView) findViewById(R.id.listview);

        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent=new Intent(ActivityEmployeeAttendance.this,ActivityAttendanceDetails.class);
                  startActivity(intent);
              }
          });
          lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
             //   sno = ((TextView)view.findViewById(R.id.ID)).getText().toString();
                ((ImageView)view.findViewById(R.id.idimageviewdeletered)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertMessage(sno);
                        }

                        });
                }

                });

        populateList();

      }
        public  void populateList() {
        new myBankAsyncTask().execute();
        }


    @Override
    public void onResume() {
        super.onResume();
        new myBankAsyncTask().execute();
    }

    public class myBankAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPostExecute(String  result) {
            super.onPostExecute(result);
            employeeAttendanceDetailsArrayList.clear();
            try {
                JSONArray jsonArray = new JSONArray(String.valueOf(result));
                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                length = jsonArray.length();
                Log.e("length", "length" + length);
                Id= new String[length];
               EmployeeName= new String[length];
               Attendance=new String[length];
               AttDate=new String[length];

                for (int i = 0; i < length; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Id[i] = jsonObject.getString("Id");
                    EmployeeName[i] = jsonObject.getString("EmployeeName");
                    Attendance[i]=jsonObject.getString("Attendance");
                    AttDate[i]=jsonObject.getString("AttDate");

                    employeeAttendanceDetails = new EmployeeAttendanceDetails();
                    employeeAttendanceDetails.setId(Id[i]);
                    employeeAttendanceDetails.setName(EmployeeName[i]);
                    employeeAttendanceDetails.setAttendance(Attendance[i]);
                    employeeAttendanceDetails.setDate(AttDate[i]);

                    employeeAttendanceDetailsArrayList.add(employeeAttendanceDetails);
                    CompanyAdapte = new AdapterEmployeeAttendance(employeeAttendanceDetailsArrayList,ActivityEmployeeAttendance.this);
                    lview.setAdapter(CompanyAdapte);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        protected String doInBackground(String... arg) {
            String URL = Config.Url+"EmployeeAttendance.asmx";
            //for linear parameter
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            // request.addProperty("Celsius", "48"); // adding method property here serially
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
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                Log.e("XMLLOG", e.getMessage());
                e.printStackTrace();
            } //send request
            Object  result = null;
            try {
                result = (Object)envelope.getResponse();
                Log.i("RESPONSE",String.valueOf(result));
            } catch (SoapFault e) {
                // TODO Auto-generated catch block
                Log.e("SOAPLOG", e.getMessage());
                e.printStackTrace();
            }
            return String.valueOf(result);
        }
    }
    public void alertMessage(String SNO) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                switch (which)

                {
                            case DialogInterface.BUTTON_POSITIVE: // Yes button clicked
                            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();
                            new DeleteLobAsyncTask().execute();
                            break;
                            case DialogInterface.BUTTON_NEGATIVE: // No button clicked //
                            Toast.makeText(getApplicationContext(), "Cannot be deleted", Toast.LENGTH_LONG).show();
                            break;
                }

            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage("Are you sure to delete record ?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
        }
        public class DeleteLobAsyncTask extends AsyncTask<Void, Void, Void> {
          ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(),"Deleted Succesfully",Toast.LENGTH_SHORT).show();
            new myBankAsyncTask().execute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String url =Config.Url+"EmloyeeAttendance.asmx";
            String soapactionaddlob = "http://tempuri.org/Delete";
            String methodname= "Delete";
            String namespacesaalob= "http://tempuri.org/";
            try {
                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                int lobid= Integer.parseInt(sno);
                //      PropertyInfo LobIdp=new PropertyInfo();
//            LobIdp.setName("LobId");
//            LobIdp.setValue(lobint);
//
//           requestAddlob.addProperty(LobIdp);
//
                requestAddlob.addProperty("Id", Id);
                requestAddlob.addProperty("CompanyID","1");
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
                    Log.i("RESPONSE after delete", String.valueOf(result1));


                } catch (Exception e) {

                    e.printStackTrace();

                }

                }
            catch (Exception e) {

                e.printStackTrace();


            }
            return null;
            }
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
}
