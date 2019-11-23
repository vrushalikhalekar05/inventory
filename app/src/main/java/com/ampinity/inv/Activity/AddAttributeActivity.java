package com.ampinity.inv.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
/*
import android.support.v7.app.AppCompatActivity;
*/
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ampinity.inv.Model.AttributeDetailPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.adapter.AttributeDetailAdapter;
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
import java.util.ArrayList;
import java.util.jar.Attributes;

public class AddAttributeActivity extends AppCompatActivity {

    Spinner spinnerlobname,spinnerTotalAttribute,spinneratributename;
    Button idButtonCancel;
    Button idButtonSave;
    int lobid1,cat1;
    String [] CompanyName={
            "Select Company Name",
            "ABCA",
            "PQRS",
            "LNMO",
            "XYZA"
    };

    EditText idEditTextIDLOB,idEditTextShortName,idEditTextName;
    String lobid;
    int length;
    ArrayList<String> Namearray;
    String [] Lobname,atributename;
    TextView idTextViewName;
    long TotalAttribute;
    int inttotalatribute;
    int flagws=0;
    String methodnameinsert;
    String selcetedlobname,selectedTotalAttribute,selectedatributename,CAT,ShortName,AttributeName;
    String CatName;
    int testPos,attributeid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attribute);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Attribute Details");



        spinnerlobname = (Spinner) findViewById(R.id.spinnerlobname);
        idEditTextIDLOB=(EditText) findViewById(R.id.idEditTextIDLOB);
        spinneratributename = (Spinner) findViewById(R.id.spinneratributename);
        idTextViewName=(TextView) findViewById(R.id.idTextViewName);
        idEditTextShortName=(EditText) findViewById(R.id.idEditTextShortName);
        idEditTextName=(EditText) findViewById(R.id.idEditTextName);
        idButtonSave=(Button)findViewById(R.id.idButtonSave);



                final ArrayAdapter<String> adapter2 = new CustomizedSpinnerAdapter(
               AddAttributeActivity.this, android.R.layout.simple_spinner_item,
                CompanyName);
       spinneratributename.setAdapter(adapter2);




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
        System.out.println("on save called to getattributeid");
                getAttributedetailid();
                ValidateAttributeDetail();
            }
        });

        getAttributedetailid();
        spinnerlobname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                System.out.println("in gettotalattributespinnerdataAsyncTask lobid");
                new gettotalattributespinnerdataAsyncTask().execute();
                // your code here
                selcetedlobname=spinnerlobname.getSelectedItem().toString();
                System.out.println("value from "+selcetedlobname);
                testPos=spinnerlobname.getSelectedItemPosition();
                if(selcetedlobname.equals("Select Lobid"))
                {
//                    Lobname=new String[1];
//                    Lobname[0]=
//
//                    final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
//                            AddAttributeActivity.this, android.R.layout.simple_spinner_item,
//                            Lobname);
//                    spinnerlobname.setAdapter(adapter1);
                }

                else

                {

                    new gettotalattributespinnerdataAsyncTask().execute();

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        spinneratributename.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selectedatributename=spinneratributename.getSelectedItem().toString();
                attributeid=spinneratributename.getSelectedItemPosition();
                // Toast.makeText(AddAttributeActivity.this,"Position"+position,Toast.LENGTH_SHORT).show();
                if(selectedatributename.equals("Select Attribute Details"))
                {
                    idTextViewName.setText("Name");
//                    Lobname=new String[1];
//                    Lobname[0]=
//
//                    final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
//                            AddAttributeActivity.this, android.R.layout.simple_spinner_item,
//                            Lobname);
//                    spinnerlobname.setAdapter(adapter1);
                }
                else
                {

                    idTextViewName.setText(selectedatributename);
                    // new gettotalattributespinnerdataAsyncTask().execute();
                }
                if(position==0)
                {

                    flagws=0;
                }
                else if(position==1)
                {

                    flagws=1;
                }
                else if(position==2)
                {

                    flagws=2;
                }
                else if(position==3)
                {
                    flagws=3;
                }
                else if(position==4)
                {

                    flagws=4;

                }
                else if(position==5)
                {
                    flagws=5;
                }
                else if(position==6)
                {

                    flagws=6;
                }
                else if(position==7)
                {

                    flagws=7;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
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
    public void ValidateAttributeDetail()
    {

        selectedatributename=spinneratributename.getSelectedItem().toString();
        selcetedlobname=spinnerlobname.getSelectedItem().toString();
        CAT=idEditTextName.getText().toString();
        //cat1=Integer.parseInt(CAT);
        ShortName=idEditTextShortName.getText().toString();
        lobid=idEditTextIDLOB.getText().toString();
        lobid1=Integer.parseInt(lobid);
        if (idEditTextShortName.getText().toString().length()==0){

           Toast.makeText(this,"Please Enter Short  Name",Toast.LENGTH_SHORT).show();
        }else
            if (idEditTextName.getText().toString().length()==0){
          Toast.makeText(this,"Please Enter CAT",Toast.LENGTH_SHORT).show();
       }
       else
           if (selcetedlobname.equals("Select Lobid")){
           Toast.makeText(this,"Please Select Lobid",Toast.LENGTH_SHORT).show();
       }
       else
           if (selectedatributename.equals("Select Attribute Details")){
           Toast.makeText(this,"Please Select Attribute Details",Toast.LENGTH_SHORT).show();
       }
       else{

           if(flagws==1)
           {
               methodnameinsert="Insert_new1";
               CatName="CatName";
               AttributeName="AttributeName";
               new AddAttributeInsertDetailtAsyncTask().execute();


           }
           else if(flagws==2)
           {
               AttributeName="AttributeName";
               methodnameinsert="Insert_new2";
               CatName="SubCatName";
               new AddAttributeInsertDetailtAsyncTask().execute();
           }
           else if(flagws==3)
           {
               AttributeName="AttributeName";
               methodnameinsert="Insert_new3";
               CatName="BrandName";
               new AddAttributeInsertDetailtAsyncTask().execute();
           }
           else if(flagws==4)
           {
               AttributeName="AttributeName";
               methodnameinsert="Insert_new4";
               CatName="SizeName";
               new AddAttributeInsertDetailtAsyncTask().execute();
           }
           else if(flagws==5)
           {
               AttributeName="AttributeName";
               methodnameinsert="Insert_new5";
               CatName="CatName";
               new AddAttributeInsertDetailtAsyncTask().execute();
           }
           else if(flagws==6)
           {
               AttributeName="AttributeName";
               methodnameinsert="Insert_new6";
               CatName="CatName";
               new AddAttributeInsertDetailtAsyncTask().execute();
           }
           else if(flagws==7)
           {
               AttributeName="AttributeName";
               methodnameinsert="Insert_new7";
               CatName="CatName";
               new AddAttributeInsertDetailtAsyncTask().execute();
           }


     }
    }

    public class AddAttributeInsertDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    //public class AddAttributeDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    {

        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddAttributeActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);

            }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(AddAttributeActivity.this,"Added Succesfully",Toast.LENGTH_SHORT).show();


            System.out.println("Result of attribute details"+aVoid);
            finish();
        }
        @Override
        protected Void doInBackground(Void... params) {

            String url = Config.Url+"attributedetails.asmx";
            String soapactionaddlob ="http://tempuri.org/Insert_details";
            String methodname="Insert_details";
            String namespacesaalob="http://tempuri.org/";
            System.out.println("in doin background of AddAttributeInsertDetailtAsyncTask");
            /*try {
                JSONArray jsonArray = new JSONArray(String.valueOf(params));
                System.out.println("in doin background of AddAttributeInsertDetailtAsyncTask");
                System.out.println("value in jsonArray"+jsonArray);

                *//*Log.i("RESPONSEjsonArray", String.valueOf(jsonArray));
                length = jsonArray.length();
                if (length > 0) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    TotalAttribute = jsonObject.getLong("TotalAttribute");

                }
                Log.e("TotalAttribute1", "TotalAttribute1" + TotalAttribute);*//*

            }
            catch (Exception e) {

                //   Log.e("IOLOG", e.getMessage());
                e.printStackTrace();
//                dialog.dismiss();
            }*/

                try {

                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                System.out.println("value of requestAddlob in addattribute in background of insert"+requestAddlob);
                // int lobint= Integer.parseInt(lobid);
//            PropertyInfo LobIdp=new PropertyInfo();
//            LobIdp.setName("LobId");
//            LobIdp.setValue(lobint);
//
//          requestAddlob.addProperty(LobIdp);
//
//          PropertyInfo Namep=new PropertyInfo();
//          Namep.setName("Name");
//          Namep.setValue(Name);
//           Namep.setType(String.class);
//           requestAddlob.addProperty(Namep);
//
//            PropertyInfo TotalAttributep=new PropertyInfo();
//            TotalAttributep.setName("TotalAttribute");
//            TotalAttributep.setValue(TotalAttribute);
//            TotalAttributep.setType(String.class);
//            requestAddlob.addProperty(TotalAttributep);
//
//            PropertyInfo CreatedByp=new PropertyInfo();
//            CreatedByp.setName("CreatedBy");
//            CreatedByp.setValue("CreatedBy");
//
//            requestAddlob.addProperty(CreatedByp);
//
//            PropertyInfo UpdatedByp=new PropertyInfo();
//            UpdatedByp.setName("UpdatedBy");
//            UpdatedByp.setValue("UpdatedBy");
//
//              requestAddlob.addProperty(UpdatedByp);
               /* String a;
                a=requestAddlob.getAttribute("LobId").toString();
                System.out.println("value of a"+a);*/
                requestAddlob.addProperty("AttrDId",lobid1);
                //requestAddlob.addProperty("LobId",lobid1);
                requestAddlob.addProperty("LobId",testPos);
               // requestAddlob.addProperty("Name",selcetedlobname);

                requestAddlob.addProperty("AttributeID",attributeid);
                requestAddlob.addProperty("Alias_A",CAT);
                requestAddlob.addProperty("ShortName",ShortName);
//                System.out.println(requestAddlob.getAttribute("Name"));
                System.out.println("Result of requestAddlob "+requestAddlob);
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
                    System.out.println("in doinbackground"+result1);
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
        }//end of addAttribute async task
        /*public class AddAttributeInsertDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddAttributeActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);
            }
            @Override
            protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(),"added succesfully",Toast.LENGTH_SHORT);
            System.out.println("in AddAttributeInsertDetailtAsyncTask result"+aVoid);
            dialog.dismiss();
            //new AddAttributeDetailtAsyncTask().execute();

        }
           @Override
           protected Void doInBackground(Void... params) {
            String url =Config.Url+"attributedetails.asmx";
            String soapactionaddlob ="http://tempuri.org/"+methodnameinsert;
            String methodname=methodnameinsert;
            String namespacesaalob="http://tempuri.org/";
            try {

                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                // int lobint= Integer.parseInt(lobid);

//            PropertyInfo LobIdp=new PropertyInfo();
//            LobIdp.setName("LobId");
//            LobIdp.setValue(lobint);
//
//            requestAddlob.addProperty(LobIdp);
//
//            PropertyInfo Namep=new PropertyInfo();
//            Namep.setName("Name");
//            Namep.setValue(Name);
//                Namep.setType(String.class);
//                requestAddlob.addProperty(Namep);
//
//            PropertyInfo TotalAttributep=new PropertyInfo();
//            TotalAttributep.setName("TotalAttribute");
//            TotalAttributep.setValue(TotalAttribute);
//                TotalAttributep.setType(String.class);
//            requestAddlob.addProperty(TotalAttributep);
//
//            PropertyInfo CreatedByp=new PropertyInfo();
//            CreatedByp.setName("CreatedBy");
//            CreatedByp.setValue("CreatedBy");
//
//            requestAddlob.addProperty(CreatedByp);
//
//            PropertyInfo UpdatedByp=new PropertyInfo();
//            UpdatedByp.setName("UpdatedBy");
//            UpdatedByp.setValue("UpdatedBy");
//
//           requestAddlob.addProperty(UpdatedByp);
               // requestAddlob.addProperty("AttrDId",selcetedlobname );
                requestAddlob.addProperty("Name",selcetedlobname );
                requestAddlob.addProperty("Name1", selectedatributename);
                requestAddlob.addProperty("Alias",CAT );
                requestAddlob.addProperty("ShortName", ShortName);
                *//*requestAddlob.addProperty("LobId",selcetedlobname );
                requestAddlob.addProperty("AttributeName", selectedatributename);
                requestAddlob.addProperty("CatName",CAT );
                requestAddlob.addProperty("ShortName", ShortName);*//*
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
                    Log.i("RESPONSE of insertion ", String.valueOf(result1));
                    }
                    catch (Exception e)
                    {
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
            }*/
    public void getAttributedetailid()
    {
        new getAttributedetailidAsyncTask().execute();
        new getlobspinnerdataAsyncTask().execute();
        }
    public class getAttributedetailidAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddAttributeActivity.this);

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
                lobid= jsonObject.getString("Column1");
                if(lobid.equals("null")){
                    idEditTextIDLOB.setText("1");
                }
                else {
                    int lobidd= Integer.parseInt(lobid)+1;
                    idEditTextIDLOB.setText(""+lobidd);
                }
                }
                catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
            }
        @Override
        protected String doInBackground(String... arg) {

            String URL = Config.Url+"attributedetails.asmx";

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
        //garment kirana
    public class getlobspinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddAttributeActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            //  dialog.show();
        }
        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            try {
                JSONArray jsonArray = new JSONArray(String.valueOf(aVoid));

                System.out.println("value of jsonArray in addAttribute of getlobspinnerdataAsyncTask "+jsonArray);

                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                length = jsonArray.length()+1;
                Log.e("length", "length" + length);
                Lobname=new String[length];

                int j=0;
                Lobname[0]="Select Lobid";

               //  Namearray = new ArrayList<String>();
                for (int i = 0; i < length-1; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                            j=j+1;
//                    Namearray.add (jsonObject.getString("Name"));
                  // right Lobname[j] = jsonObject.getString("Name");
                   //Lobname[j] = jsonObject.getString("LobID");
                   Lobname[j] = jsonObject.getString("Name");
//
//

//
                 Log.e("Name", "Name" + Lobname[j]);
                }
                final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
                        AddAttributeActivity.this, android.R.layout.simple_spinner_item,
                        Lobname);
                spinnerlobname.setAdapter(adapter1);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url =Config.Url+"attributedetails.asmx";
            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "Bindlob");
            // request.addProperty("Celsius", "48"); // adding method property here serially

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;
            HttpTransportSE httpTransport = new HttpTransportSE(url);
            httpTransport.debug = true;
            try {
                httpTransport.call("http://tempuri.org/Bindlob", envelope);
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
    public class gettotalattributespinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddAttributeActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            //  dialog.show();
        }
        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            try {
                JSONArray jsonArray = new JSONArray(String.valueOf(aVoid));
                System.out.println("value of jsonarray in add"+jsonArray);
                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                length = jsonArray.length();
                if(length>0)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    TotalAttribute=jsonObject.getLong("TotalAttribute");
                    /*String garment="GARMENT";
                    String selcetedlobname1;
                    if(garment.equals("GARMENT")) {
                        selcetedlobname1= jsonObject.getString("LobID");
                        System.out.println(selcetedlobname1);
                    }*/

                }
                Log.e("TotalAttribute1", "TotalAttribute1" + TotalAttribute);

//                 inttotalatribute= Integer.parseInt(TotalAttribute);
//                TotalAttribute=new String[length];
//
//                int j=0;
//                TotalAttribute[0]="Select Total Attribute";
//
//                //  Namearray = new ArrayList<String>();
//                for (int i = 0; i < length; i++) {
//
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                    j=j+1;
////                    Namearray.add (jsonObject.getString("Name"));
//                    TotalAttribute[j] = jsonObject.getString("Name");
////
////
////
////
//                    Log.e("Name", "Name" + TotalAttribute[j]);
//
//
//
//                }



                new getattributespinnerdataAsyncTask().execute();


            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url =Config.Url+"attributedetails.asmx";

           /* try {
                JSONArray jsonArray = new JSONArray(String.valueOf(arg));
                System.out.println("in doin background of gettotalattributespinnerdataAsyncTask");
                System.out.println("value in jsonArray"+jsonArray);*/


               /* length = jsonArray.length();
                if (length > 0) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    TotalAttribute = jsonObject.getLong("TotalAttribute");

                }
                Log.e("TotalAttribute1", "TotalAttribute1" + TotalAttribute);*/

           /* }
            catch (Exception e) {

                //   Log.e("IOLOG", e.getMessage());
                e.printStackTrace();
//                dialog.dismiss();
            }*/



            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "Bind_TotalAttribute_new");
            request.addProperty("Name", selcetedlobname); // adding method property here serially

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            HttpTransportSE httpTransport = new HttpTransportSE(url);
            httpTransport.debug = true;

            try {
                httpTransport.call("http://tempuri.org/Bind_TotalAttribute_new", envelope);

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

    public class getattributespinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddAttributeActivity.this);
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
                length = jsonArray.length();
                Log.e("length", "length" + length);
                atributename=new String[length+1];

                int j=0;
                atributename[0]="Select Attribute Details";

                //  Namearray = new ArrayList<String>();
                for (int i = 0; i < length; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    j=j+1;
//                    Namearray.add (jsonObject.getString("Name"));
                    atributename[j] = jsonObject.getString("Name");
//
//
//
//
                    Log.e("Name", "Name" + atributename[j]);



                }




                final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
                        AddAttributeActivity.this, android.R.layout.simple_spinner_item,
                        atributename);
                spinneratributename.setAdapter(adapter1);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url =Config.Url+"attributedetails.asmx";


            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "Bind_AttributeDetails_new");
             request.addProperty("totalNumber",TotalAttribute); // adding method property here serially

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            HttpTransportSE httpTransport = new HttpTransportSE(url);
            httpTransport.debug = true;

            try {
                httpTransport.call("http://tempuri.org/Bind_AttributeDetails_new", envelope);

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

    @Override
    protected void onResume() {
        super.onResume();
        getAttributedetailid();
    }
}
