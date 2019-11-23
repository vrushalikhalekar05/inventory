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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;
import com.ampinity.inv.adapter.CustomizedSpinnerAdapter;

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

public class AddSalesCustomerActivity extends AppCompatActivity {


    private static final String SOAP_ACTION = "http://tempuri.org/GetAutonumber";
    private static final String METHOD_NAME = "GetAutonumber";
    private static final String NAMESPACE = "http://tempuri.org/";


    String [] CompanyName={
            "Select Group Name",
            "ABCA",
            "PQRS",
            "LNMO",
            "XYZA"};
    Button idButtonCancel,idButtonSave;
    int length;
    ConnectionDetector cd;
    String id;
    String selcetedspinnergroupname,selcetedspinnerState,selcetedspinnerCity;
    EditText idEditTextIDVenderDetail,idEditTextVenderName,idEditTextAddress,idEditTextEmail,idEditTextPhoneNo,idEditTextGSTINNo;
    String   stridEditTextIDVenderDetail,stridEditTextVenderName,stridEditTextAddress,stridEditTextEmail,stridEditTextPhoneNo,stridEditTextGSTINNo;

    Spinner spinnergroupname,spinnerState,spinnerCity;
    String [] City,State,GroupName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales_customer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Customer Details");
        //spinnergroupname = (Spinner) findViewById(R.id.spinnergroupname);
        spinnerState= (Spinner) findViewById(R.id.spinnerState);
        spinnerCity= (Spinner) findViewById(R.id.spinnerCity);


        idEditTextIDVenderDetail=(EditText)findViewById(R.id.idEditTextIDVenderDetail);
     //   idEditTextVenderName=(EditText)findViewById(R.id.idEditTextVenderName);
        idEditTextAddress=(EditText)findViewById(R.id.idEditTextAddress);
        idEditTextEmail=(EditText)findViewById(R.id.idEditTextEmail);
        idEditTextPhoneNo=(EditText)findViewById(R.id.idEditTextPhoneNo);
      //  idEditTextGSTINNo=(EditText)findViewById(R.id.idEditTextGSTINNo);
//
//        spinnergroupname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                // your code here
//                selcetedspinnergroupname = spinnergroupname.getSelectedItem().toString();
//                if (selcetedspinnergroupname.equals("Select TaxName")) {
////                    Lobname=new String[1];
////                    Lobname[0]=
////
////                    final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
////                            AddAttributeActivity.this, android.R.layout.simple_spinner_item,
////                            Lobname);
////                    spinnerlobname.setAdapter(adapter1);
//
//                } else {
//                    // new gettotalattributespinnerdataAsyncTask().execute();
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // your code here
//            }
//
//        });
        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selcetedspinnerState = spinnerState.getSelectedItem().toString();
                if (selcetedspinnerState.equals("Select State")) {
                    City=new String[1];
                    City[0]="Select City";

                    final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
                            AddSalesCustomerActivity.this, android.R.layout.simple_spinner_item,
                            City);
                    spinnerCity.setAdapter(adapter1);

                } else {
                    new getCityspinnerdataAsyncTask().execute();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selcetedspinnerCity = spinnerCity.getSelectedItem().toString();
                if (selcetedspinnerCity.equals("Select City")) {
//                    Lobname=new String[1];
//                    Lobname[0]=
//
//                    final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
//                            AddAttributeActivity.this, android.R.layout.simple_spinner_item,
//                            Lobname);
//                    spinnerlobname.setAdapter(adapter1);

                } else {
                    // new gettotalattributespinnerdataAsyncTask().execute();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        //        final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
//                AddVenderDetailActivity.this, android.R.layout.simple_spinner_item,
//                CompanyName);
//        spinnergroupname.setAdapter(adapter1);
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
        // new getgroupnamespinnerdataAsyncTask().execute();
        new getStatespinnerdataAsyncTask().execute();
        getid();
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
    public class getgroupnamespinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddSalesCustomerActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            //  dialog.show();
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            try {
                JSONArray jsonArray = new JSONArray(String.valueOf(aVoid));

                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                length = jsonArray.length()+1;
                Log.e("length", "length" + length);
                GroupName=new String[length];

                int j=0;
                GroupName[0]="Select GroupName";

                //  Namearray = new ArrayList<String>();
                for (int i = 0; i < length-1; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    j=j+1;
//                    Namearray.add (jsonObject.getString("Name"));
                    GroupName[j] = jsonObject.getString("partygroup");
//
//
//
//
                    Log.e("GroupName", "GroupName" + GroupName[j]);



                }






                final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(AddSalesCustomerActivity.this, android.R.layout.simple_spinner_item,
                        GroupName);
                spinnergroupname.setAdapter(adapter1);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url ="http://ampinventory.ampinityit.com/vendardetails.asmx";


            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "Bindvenderdetails");
            // request.addProperty("Celsius", "48"); // adding method property here serially

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            HttpTransportSE httpTransport = new HttpTransportSE(url);
            httpTransport.debug = true;

            try {
                httpTransport.call("http://tempuri.org/Bindvenderdetails", envelope);

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

            Object  result = null;
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
    public class getStatespinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddSalesCustomerActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            //  dialog.show();
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            try {
                JSONArray jsonArray = new JSONArray(String.valueOf(aVoid));

                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                length = jsonArray.length()+1;
                Log.e("length", "length" + length);
                State=new String[length];

                int j=0;
                State[0]="Select State";

                //  Namearray = new ArrayList<String>();
                for (int i = 0; i < length-1; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    j=j+1;
//                    Namearray.add (jsonObject.getString("Name"));
                    State[j] = jsonObject.getString("State");
//
//
//
//
                    Log.e("State", "State" + State[j]);



                }






                final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(AddSalesCustomerActivity.this, android.R.layout.simple_spinner_item,
                        State);
                spinnerState.setAdapter(adapter1);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url ="http://ampinventory.ampinityit.com/vendardetails.asmx";


            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "BindState");
            // request.addProperty("Celsius", "48"); // adding method property here serially

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            HttpTransportSE httpTransport = new HttpTransportSE(url);
            httpTransport.debug = true;

            try {
                httpTransport.call("http://tempuri.org/BindState", envelope);

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

            Object  result = null;
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
    public class getCityspinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddSalesCustomerActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            //  dialog.show();
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            try {
                JSONArray jsonArray = new JSONArray(String.valueOf(aVoid));

                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                length = jsonArray.length()+1;
                Log.e("length", "length" + length);
                City=new String[length];

                int j=0;
                City[0]="Select City";

                //  Namearray = new ArrayList<String>();
                for (int i = 0; i < length-1; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    j=j+1;
//                    Namearray.add (jsonObject.getString("Name"));
                    City[j] = jsonObject.getString("City");
//
//
//
//
                    Log.e("City", "City" + City[j]);



                }






                final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(AddSalesCustomerActivity.this, android.R.layout.simple_spinner_item,
                        City);
                spinnerCity.setAdapter(adapter1);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url ="http://ampinventory.ampinityit.com/vendardetails.asmx";


            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "BindCity_ByState");
            request.addProperty("State", selcetedspinnerState); // adding method property here serially

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            HttpTransportSE httpTransport = new HttpTransportSE(url);
            httpTransport.debug = true;

            try {
                httpTransport.call("http://tempuri.org/BindCity_ByState", envelope);

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

            Object  result = null;
            try {
                result = (Object) envelope.getResponse();
                Log.i("RESPONSE", String.valueOf(result));


//                lobid=String.valueOf(result);





            } catch (SoapFault e) {
                // TODO Auto-generated catch block
//                Log.e("SOAPLOG", e.getMessage());
                e.printStackTrace();
                dialog.dismiss();
            }

            return String.valueOf(result);
        }



    }


    public void validate()
    {
        cd=new ConnectionDetector(getApplicationContext());

     //   selcetedspinnergroupname = spinnergroupname.getSelectedItem().toString();
        selcetedspinnerState = spinnerState.getSelectedItem().toString();
        selcetedspinnerCity = spinnerCity.getSelectedItem().toString();

        if (!cd.isConnectingToInternet()) {
            Toast.makeText(getApplicationContext(), "Network Unavailable", Toast.LENGTH_SHORT).show();

        }
        else
        if (idEditTextVenderName.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter VenderName",Toast.LENGTH_SHORT).show();
        } else
        if (idEditTextEmail.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show();
        }
        else
        if (idEditTextPhoneNo.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter PhoneNo",Toast.LENGTH_SHORT).show();
        }
        else
        if (idEditTextGSTINNo.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter GSTINNo",Toast.LENGTH_SHORT).show();
        }else
        if (idEditTextAddress.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Address",Toast.LENGTH_SHORT).show();
        }
        else if (selcetedspinnerState.equals("Select State")){
            Toast.makeText(this,"Please Select State",Toast.LENGTH_SHORT).show();
        }
        else if (selcetedspinnerCity.equals("Select City")){
            Toast.makeText(this,"Please Select State",Toast.LENGTH_SHORT).show();
        }


        else{


            stridEditTextIDVenderDetail=idEditTextIDVenderDetail.getText().toString();
            stridEditTextVenderName=idEditTextVenderName.getText().toString();
            stridEditTextAddress=idEditTextAddress.getText().toString();
            stridEditTextEmail=idEditTextEmail.getText().toString();
            stridEditTextPhoneNo=idEditTextPhoneNo.getText().toString();
            stridEditTextGSTINNo=idEditTextGSTINNo.getText().toString();


           // new AddVenderDetailInsertDetailtAsyncTask().execute();
        }
    }

    public class AddVenderDetailInsertDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    {

        ProgressDialog dialog;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddSalesCustomerActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            finish();
            Toast.makeText(AddSalesCustomerActivity.this,"Added Succesfully",Toast.LENGTH_SHORT).show();
            // new AddAttributeDetailtAsyncTask().execute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            String url ="http://ampinventory.ampinityit.com/vendardetails.asmx";
            String soapactionaddlob ="http://tempuri.org/Insert_new";
            String methodname="Insert_new";
            String namespacesaalob="http://tempuri.org/";
            try {


                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);







                requestAddlob.addProperty("GroupName",selcetedspinnergroupname );
                requestAddlob.addProperty("Name", stridEditTextVenderName);
                requestAddlob.addProperty("Address", stridEditTextAddress);
                requestAddlob.addProperty("City",selcetedspinnerCity );
                requestAddlob.addProperty("State", selcetedspinnerState);
                requestAddlob.addProperty("Email", stridEditTextEmail);
                requestAddlob.addProperty("PhoneNo",stridEditTextPhoneNo );
                requestAddlob.addProperty("GSTINNumber", stridEditTextGSTINNo);


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


    public void getid()
    {
        new getidAsyncTask().execute();

    }

    public class getidAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddSalesCustomerActivity.this);
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





                id= jsonObject.getString("Column1");
                int idd= Integer.parseInt(id)+1;
                if(id.equals("null")){
                    idEditTextIDVenderDetail.setText("1");
                }
                else {
                    idEditTextIDVenderDetail.setText(""+idd);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //idEditTextIDVenderDetail.setText(""+idd);
            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String URL = "http://ampinventory.ampinityit.com/vendardetails.asmx";

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
                dialog.dismiss();
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                Log.e("XMLLOG", e.getMessage());
                e.printStackTrace();
                dialog.dismiss();
            } //send request

            Object  result = null;
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
}
