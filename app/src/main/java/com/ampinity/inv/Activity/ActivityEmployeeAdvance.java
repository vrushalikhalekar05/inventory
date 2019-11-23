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
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
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

import com.ampinity.inv.Model.EmployeeAdvanceDetails;
import com.ampinity.inv.Model.EmployeeAttendanceDetails;
import com.ampinity.inv.Model.TableDataPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.adapter.AdapterEmployeeAdvance;
import com.ampinity.inv.adapter.AdapterEmployeeAttendance;
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

public class ActivityEmployeeAdvance extends AppCompatActivity {
    Button buttonEmployeeAdavnce;
    Button buttonAddEmployee;
    Button idButtonAddLob,idButtonExportLob;
    String sno;
    String[] Salary;
    String[] TotalAdvance;
    String[] RemainingSalary;
    String[] TodaysAdvance;
    String[] Date;
    String CompanyID;
    String Name1;
    int length;
    static AdapterEmployeeAdvance CompanyAdapte;
    private static final String SOAP_ACTION = "http://tempuri.org/GetData";
    private static final String METHOD_NAME = "GetData";
    private static final String NAMESPACE = "http://tempuri.org/";
    private ArrayList<EmployeeAdvanceDetails> productList;
    String[] Id;
    String[] Name;
    String[] TotalAttribute;
    AdapterEmployeeAdvance adapterEmployeeAdvance;
    ArrayList<EmployeeAdvanceDetails> employeeAdvanceDetailsArrayList = new ArrayList<>();
    ListView lview;
    TableDataPojo tableDataPojo;
    int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_advance);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //   getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add employee Adavnce");
        buttonEmployeeAdavnce= (Button) findViewById(R.id.ButtonEmployeeAdvance);
        productList = new ArrayList<EmployeeAdvanceDetails>();
        lview = (ListView) findViewById(R.id.listview);
        adapterEmployeeAdvance=new AdapterEmployeeAdvance(productList,getApplicationContext());

        lview.setAdapter(adapterEmployeeAdvance);

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                sno = ((TextView)view.findViewById(R.id.ID)).getText().toString();
                ((ImageView)view.findViewById(R.id.idimageviewdeletered)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertMessage(sno);
                    }
                });


            }

        });
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions((Activity) this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else
            {
            }

        populateList();

        adapterEmployeeAdvance.notifyDataSetChanged();
        buttonEmployeeAdavnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityEmployeeAdvance.this,ActivityEmployeeAdvanceDetails.class);
                startActivity(intent);
            }
        });
    }
    public  void populateList() {
        new myLobAsyncTask().execute();
    }
    @Override
    public void onResume() {
        super.onResume();
        new myLobAsyncTask().execute();
    }
    public void alertMessage(String SNO) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                switch (which)
                {
                    case DialogInterface.BUTTON_POSITIVE: // Yes button clicked
                        Toast.makeText(ActivityEmployeeAdvance.this, "Deleted Successfully", Toast.LENGTH_LONG).show();
                        new  DeleteLobAsyncTask().execute();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE: // No button clicked //
                        // do nothing
                        Toast.makeText(ActivityEmployeeAdvance.this, "Cannot be deleted", Toast.LENGTH_LONG).show();
                        break;
                }

            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage("Are you sure to delete record ?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
    public class DeleteLobAsyncTask extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(),"Deleted Succesfully",Toast.LENGTH_SHORT).show();
            new myLobAsyncTask().execute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String url = Config.Url+"EmloyeeAdvance.asmx";
            String soapactionaddlob = "http://tempuri.org/Delete";
            String methodname="Delete";
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

                requestAddlob.addProperty("d1", sno);
                requestAddlob.addProperty("CompanyID",CompanyID);
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
                    //   Log.e("IOLOG", e.getMessage());
                    e.printStackTrace();
                    //               dialog.dismiss();
                }
            }
            catch (Exception e)
            {
                //   Log.e("IOLOG", e.getMessage());
                e.printStackTrace();
                //                dialog.dismiss();
            }
            return null;
        }
    }
    public class myLobAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPostExecute(String  result) {
            super.onPostExecute(result);
           employeeAdvanceDetailsArrayList.clear();
           try {
                JSONArray jsonArray = new JSONArray(String.valueOf(result));
                Log.i("RESPONSE jsonArray",String.valueOf(jsonArray));
                length = jsonArray.length();
                Log.e("length", "length" + length);
                Id= new String[length];
                Name= new String[length];
                Salary=new String[length];
                TodaysAdvance=new String[length];
                TotalAdvance= new String[length];
                RemainingSalary=new String[length];
                Date=new String[length];
                for (int i = 0; i < length; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Id[i] = jsonObject.getString("Id");
                    Name[i] = jsonObject.getString("EmployeeName");
                    Salary[i] = jsonObject.getString("Salary");
                    TotalAdvance[i]=jsonObject.getString("TotalAdvance");
                    TodaysAdvance[i]=jsonObject.getString("TodaysAdvance");
                    RemainingSalary[i]=jsonObject.getString("RemainingSalary");
                   // Date[i]=jsonObject.getString("Todaysdate");

                    //  TotalAttribute[i] = jsonObject.getString("TotalAttribute");


                    Log.e("Name", "Name" + Name[i]);
                    Log.e("LobId", "LobId" + Id[i]);

                    //   Log.e("TotalAttribute", "TotalAttribute" + TotalAttribute[i]);
                    // tableDataPojo = new TableDataPojo();

                    EmployeeAdvanceDetails employeeAdvanceDetails=new EmployeeAdvanceDetails();
                    employeeAdvanceDetails.setId(Id[i]);
                    employeeAdvanceDetails.setName(Name[i]);
                    employeeAdvanceDetails.setSalary(Salary[i]);
                    employeeAdvanceDetails.setTotalAdvancement(TotalAdvance[i]);
                    employeeAdvanceDetails.setRemainingSalary(RemainingSalary[i]);

                    //  tableDataPojo.setTotalAttribute(TotalAttribute[i]);

                    employeeAdvanceDetailsArrayList.add(employeeAdvanceDetails);
                    CompanyAdapte = new AdapterEmployeeAdvance(employeeAdvanceDetailsArrayList,ActivityEmployeeAdvance.this);
                    lview.setAdapter(CompanyAdapte);

                }

            }
            catch (JSONException e)
            {
                e.printStackTrace();

            }
        }
        @Override
        protected String doInBackground(String... arg) {
            String URL = Config.Url+"EmployeeAdvance.asmx";
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
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
            }
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
