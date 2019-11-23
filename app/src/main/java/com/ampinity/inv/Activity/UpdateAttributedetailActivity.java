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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
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

public class UpdateAttributedetailActivity extends AppCompatActivity {
    Bundle bundle;
    Spinner spinnerlobname,spinneratributename;
    Button idButtonCancel;
    Button idButtonSave;
    int lobid2,attrid1;
    long TotalAttribute;
    int length;
    String attrdid,lobid1,attributeid1,name1,shortname1;
    String [] Lobname,atributename;
    String selectedatributename;
    TextView idTextViewName;
    EditText idEditTextIDLOB,idEditTextShortName,idEditTextName;
    int testPos,attributeid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_attributedetail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Attribute Details");
        idTextViewName=(TextView) findViewById(R.id.idTextViewName) ;
        idEditTextIDLOB=(EditText) findViewById(R.id.idEditTextIDLOB);
        spinnerlobname = (Spinner) findViewById(R.id.spinnerlobname);

        spinneratributename = (Spinner) findViewById(R.id.spinneratributename);
        idEditTextName=(EditText) findViewById(R.id.idEditTextName);
        idEditTextShortName=(EditText) findViewById(R.id.idEditTextShortName);

        bundle = new Bundle();
        bundle = getIntent().getExtras();

        attrdid = bundle.getString("AttrDId").toString();
        lobid1 = bundle.getString("LobId").toString();
        //lobid2=Integer.parseInt(lobid1);
        attrid1=Integer.parseInt(attrdid);
        attributeid1 = bundle.getString("AttributeID").toString();
        name1= bundle.getString("Name").toString();
        shortname1 = bundle.getString("ShortName").toString();

        idEditTextIDLOB.setText(attrdid);
       // spinnerlobname.setText(lobname);
        //idEditTextTotalAttribute.setText(totalattribute);
        idEditTextName.setText(name1);
        idEditTextShortName.setText(shortname1);


        idButtonCancel=(Button)findViewById(R.id.idButtonCancel);
        idButtonSave=(Button)findViewById(R.id.idButtonSave);
        idButtonCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View v)
            {

                finish();

            }

        });
        idButtonSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //ValidateAttributedetail();
               new UpdateAttributeDetailtAsyncTask().execute();
            }
        });
        new getlobspinnerdataAsyncTask().execute();
        spinneratributename.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                //new gettotalattributespinnerdataAsyncTask().execute();
                selectedatributename=spinneratributename.getSelectedItem().toString();
                //attributeid=spinneratributename.getSelectedItemPosition();
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

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
       // new getattributespinnerdataAsyncTask().execute();
        spinnerlobname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                new gettotalattributespinnerdataAsyncTask().execute();
                lobid1=spinnerlobname.getSelectedItem().toString();
                testPos=spinnerlobname.getSelectedItemPosition();
                if(lobid1.equals("Select Select Lobid"))
                {
                    //idTextViewName.setText("Name");
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
                   // idTextViewName.setText(lobid1);
                    new gettotalattributespinnerdataAsyncTask().execute();
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
    public class gettotalattributespinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateAttributedetailActivity.this);
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
            //updated
            //request.addProperty("Name", lobid1); // adding method property here serially
            request.addProperty("Name", attributeid1); // adding method property here serially

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
                //Log.e("IOLOG", e.getMessage());
                dialog.dismiss();
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
               // Log.e("XMLLOG", e.getMessage());
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
//garment kirana
    public class getlobspinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateAttributedetailActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            //  dialog.show();
        }
        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
           System.out.println("value of attributeid in spinner"+attributeid1);
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
                        UpdateAttributedetailActivity.this, android.R.layout.simple_spinner_item,
                        Lobname);
                spinnerlobname.setAdapter(adapter1);
                if (attributeid1 != null) {
                    int spinnerPosition = adapter1.getPosition(attributeid1);
                    spinnerlobname.setSelection(spinnerPosition);
                }

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
//category
    public class getattributespinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateAttributedetailActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.setCancelable(false);
            //  dialog.show();
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            System.out.println("value of attribute name using attrdid"+attrdid);
            System.out.println("value of attribute name using lobid1"+lobid1);
            System.out.println("value of attribute name using attributeid1"+attributeid1);
            System.out.println("value of attribute name using name1"+name1);
            System.out.println("value of attribute name using shortname1"+shortname1);
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
                        UpdateAttributedetailActivity.this, android.R.layout.simple_spinner_item,
                        atributename);
                spinneratributename.setAdapter(adapter1);
            /*    lobid1*/
                if (lobid1 != null) {
                    int spinnerPosition = adapter1.getPosition(lobid1);
                    spinneratributename.setSelection(spinnerPosition);
                }
                /*if (attributeid1 != null) {
                    int spinnerPosition = adapter1.getPosition(attributeid1);
                    spinneratributename.setSelection(spinnerPosition);
                }*/

                /*if (lobid1 != null) {
                    String spinnerPosition = adapter1.getItem(0);
                    spinneratributename.setSelection(spinnerPosition);
                }*/

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
               // Log.e("SOAPLOG", e.getMessage());
                e.printStackTrace();
                dialog.dismiss();
            }

            return String.valueOf(result);
        }



    }

    public class UpdateAttributeDetailtAsyncTask extends AsyncTask<Void, Void, Void>
            //public class AddAttributeDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    {

        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateAttributedetailActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);

        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(UpdateAttributedetailActivity.this,"Updated Succesfully",Toast.LENGTH_SHORT).show();


            System.out.println("Result of attribute details"+aVoid);
            finish();
        }
        @Override
        protected Void doInBackground(Void... params) {

            String url = Config.Url+"attributedetails.asmx";
            String soapactionaddlob ="http://tempuri.org/Update_New";
            String methodname="Update_New";
            String namespacesaalob="http://tempuri.org/";
            System.out.println("in doin background of AddAttributeUpdateDetailtAsyncTask");
            try {
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                System.out.println("value of requestAddlob in addattribute in background of insert"+requestAddlob);


                //requestAddlob.addProperty("LobId",lobid1);
                //requestAddlob.addProperty("AttrDId",attrid1);
                requestAddlob.addProperty("AttrDId",attrdid);
                requestAddlob.addProperty("LobId",testPos);
                // requestAddlob.addProperty("Name",selcetedlobname);
                //UPDATED
                //requestAddlob.addProperty("AttributeID",attributeid1);
                requestAddlob.addProperty("AttributeId",1);

                //updated
                requestAddlob.addProperty("Name",name1);
                requestAddlob.addProperty("ShortName",shortname1);

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
    }


    public void ValidateAttributedetail()
    {

//        if (idEditTextLobName.getText().toString().length()==0){
//            Toast.makeText(this,"Please Enter Lob Name",Toast.LENGTH_SHORT).show();
//        }else
//        if (idEditTextTotalAttribute.getText().toString().length()==0){
//            Toast.makeText(this,"Please Enter Total Attribute",Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Name=idEditTextLobName.getText().toString();
//            TotalAttribute=idEditTextTotalAttribute.getText().toString();
//            new AddLobtestAsyncTask().execute();
//        }
    }
  /*  public class AddLobtestAsyncTask extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateAttributedetailActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(UpdateAttributedetailActivity.this,"Updated Succesfully",Toast.LENGTH_SHORT).show();
            finish();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String urlupdate = Config.Url+"lob.asmx";
            String soapactionupdatelob ="http://tempuri.org/Update_New";
            String methodnameupdate="Update_New";
            String namespacesaupdatelob="http://tempuri.org/";
            try {
                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"
                SoapObject requestupdatelob = new SoapObject(namespacesaupdatelob, methodnameupdate);
                //   int lobint= Integer.parseInt(lobid);
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
                //   requestupdatelob.addProperty("LobId", lobid);
              //  requestupdatelob.addProperty("Name", Name);
              //  requestupdatelob.addProperty("TotalAttribute", TotalAttribute);
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

    }*/




}
