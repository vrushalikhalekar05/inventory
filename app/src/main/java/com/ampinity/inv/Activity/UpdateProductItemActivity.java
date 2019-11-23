package com.ampinity.inv.Activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.Utility.SharedPreferencesConstants;
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

public class UpdateProductItemActivity extends AppCompatActivity {


    Spinner spinnercategory,PaymentBy,spinnergst,spinnervendername;
    String [] Lobname,atributename,GST;
    long TotalAttribute;
    float SGST,IGST,CGST;
    String strSGST,strIGST,strCGST;
    String id,selcetedlobname,selcetedGst;
    LinearLayout idlayoutpurchase, layoutl1,layoutl2,layoutl3,layoutl4,layoutl5,layoutl6,layoutl7,layoutl8,layoutl12,layoutl34,layoutl56,layoutl78;
    Spinner editText,editTextlable2,editTextlable3,editTextlable4,editTextlable5,editTextlable6,editTextlable7,editTextlable8;
    EditText idEditTextName,idEditTextItemCode,idEditTextHSNSACCode,idEditTextPurchasePrice,idEditTextper,idEditTextSalePrice,idEditTextSaleDesPer,idEditTextQuantity,idEditTextSGSTPer,idEditTextIGSTPer,idEditTextCGSTPer;
    String stritemid,strpid,stridEditTextName,stridEditTextItemCode,stridEditTextHSNSACCode,streditText,streditTextlable2,streditTextlable3,streditTextlable4,streditTextlable5,streditTextlable6,streditTextlable7,streditTextlable8,stridEditTextPurchasePrice,stridEditTextper,stridEditTextSalePrice,stridEditTextSaleDesPer,stridEditTextQuantity,stridEditTextSGSTPer,stridEditTextIGSTPer,Gst,stridEditTextCGSTPer;
    float PurchasePrice,Discountper,DiscountAmount,SalesPrice,SalesDiscount,QTY,TotalDiscount,Singleproductprice,AllPoductprice,SGSTPER,SGSTAMT,IGSTPER,IGSTAMT,CGSTPER,CGSTAMT,TotalSubprice;
    Button idDialogButtonAdd,idDialogButtonCancel;
    int length;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Product");

        bundle = new Bundle();

        bundle = getIntent().getExtras();

        stritemid=bundle.getString("ID").toString();
        strpid=bundle.getString("PID").toString();
        stridEditTextName=bundle.getString("Name").toString();
        stridEditTextItemCode=bundle.getString("ItemCode").toString();
        stridEditTextHSNSACCode=bundle.getString("HSNSACCode").toString();
        streditText=bundle.getString("lable1").toString();
        streditTextlable2=bundle.getString("lable2").toString();
        streditTextlable3=bundle.getString("lable3").toString();
        streditTextlable4=bundle.getString("lable4").toString();
        streditTextlable5=bundle.getString("lable5").toString();
        streditTextlable6=bundle.getString("lable6").toString();
        streditTextlable7=bundle.getString("lable7").toString();
        streditTextlable8=bundle.getString("lable8").toString();
        stridEditTextPurchasePrice=bundle.getString("PurchasePrice").toString();
        stridEditTextper=bundle.getString("per").toString();
        stridEditTextSalePrice=bundle.getString("SalePrice").toString();
        stridEditTextSaleDesPer=bundle.getString("SaleDesPer").toString();
        stridEditTextQuantity=bundle.getString("Quantity").toString();
        stridEditTextSGSTPer=bundle.getString("SGSTPERItem").toString();
        stridEditTextIGSTPer=bundle.getString("IGSTPERItem").toString();
        stridEditTextCGSTPer=bundle.getString("CGSTPERItem").toString();
        Gst=bundle.getString("Gst").toString();
        Toast.makeText(this, ""+stridEditTextName+""+stridEditTextIGSTPer+""+stridEditTextCGSTPer, Toast.LENGTH_SHORT).show();


        spinnergst= (Spinner) findViewById(R.id.spinnergst);
        spinnercategory = (Spinner) findViewById(R.id.spinnercategory);

        layoutl1=(LinearLayout)findViewById(R.id.layoutl1) ;
        layoutl2=(LinearLayout)findViewById(R.id.layoutl2) ;
        layoutl3=(LinearLayout)findViewById(R.id.layoutl3) ;
        layoutl4=(LinearLayout)findViewById(R.id.layoutl4) ;
        layoutl5=(LinearLayout)findViewById(R.id.layoutl1) ;
        layoutl6=(LinearLayout)findViewById(R.id.layoutl2) ;
        layoutl7=(LinearLayout)findViewById(R.id.layoutl3) ;
        layoutl8=(LinearLayout)findViewById(R.id.layoutl4) ;
        layoutl12=(LinearLayout)findViewById(R.id.layoutl12) ;
        layoutl34=(LinearLayout)findViewById(R.id.layoutl34) ;
        layoutl56=(LinearLayout)findViewById(R.id.layoutl56) ;
        layoutl78=(LinearLayout)findViewById(R.id.layoutl78) ;

        idEditTextName=(EditText) findViewById(R.id.idEditTextName) ;
        idEditTextItemCode=(EditText) findViewById(R.id.idEditTextItemCode) ;
        idEditTextHSNSACCode=(EditText)findViewById(R.id.idEditTextHSNSACCode) ;
        editText=(Spinner) findViewById(R.id.editText) ;
        editTextlable2=(Spinner) findViewById(R.id.editTextlable2) ;
        editTextlable3=(Spinner)findViewById(R.id.editTextlable3) ;
        editTextlable4=(Spinner)findViewById(R.id.editTextlable4) ;
        editTextlable5=(Spinner) findViewById(R.id.editTextlable5) ;
        editTextlable6=(Spinner) findViewById(R.id.editTextlable6) ;
        editTextlable7=(Spinner)findViewById(R.id.editTextlable7) ;
        editTextlable8=(Spinner) findViewById(R.id.editTextlable8) ;

        idEditTextPurchasePrice=(EditText) findViewById(R.id.idEditTextPurchasePrice) ;
        idEditTextper=(EditText)findViewById(R.id.idEditTextper) ;
        idEditTextSalePrice=(EditText)findViewById(R.id.idEditTextSalePrice) ;
        idEditTextSaleDesPer=(EditText) findViewById(R.id.idEditTextSaleDesPer) ;
        idEditTextQuantity=(EditText) findViewById(R.id.idEditTextQuantity) ;

        idEditTextSGSTPer=(EditText)findViewById(R.id.idEditTextSGSTPer) ;
        idEditTextIGSTPer=(EditText)findViewById(R.id.idEditTextIGSTPer) ;
        idEditTextCGSTPer=(EditText) findViewById(R.id.idEditTextCGSTPer) ;

        idEditTextName.setText(stridEditTextName);
        idEditTextItemCode.setText(strpid);
        idEditTextHSNSACCode.setText(stridEditTextHSNSACCode);
        idEditTextPurchasePrice.setText(stridEditTextPurchasePrice);
        idEditTextper.setText(stridEditTextper);
        idEditTextSalePrice.setText(stridEditTextSalePrice);
        idEditTextSaleDesPer.setText(stridEditTextSaleDesPer);
        idEditTextQuantity.setText(stridEditTextQuantity);

       /* idEditTextSGSTPer.setText(stridEditTextSGSTPer);
        idEditTextCGSTPer.setText(stridEditTextCGSTPer);
        idEditTextIGSTPer.setText(stridEditTextIGSTPer);*/



        layoutl1.setVisibility(View.GONE);
        layoutl2.setVisibility(View.GONE);
        layoutl3.setVisibility(View.GONE);
        layoutl4.setVisibility(View.GONE);
        layoutl5.setVisibility(View.GONE);
        layoutl6.setVisibility(View.GONE);
        layoutl7.setVisibility(View.GONE);
        layoutl8.setVisibility(View.GONE);
        layoutl12.setVisibility(View.GONE);
        layoutl34.setVisibility(View.GONE);
        layoutl56.setVisibility(View.GONE);
        layoutl78.setVisibility(View.GONE);


        spinnercategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selcetedlobname=spinnercategory.getSelectedItem().toString();
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
        spinnergst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selcetedGst=spinnergst.getSelectedItem().toString();
                if(selcetedGst.equals("Select GST"))
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
                    new getAllGSTspinnerdataAsyncTask().execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        idDialogButtonCancel= (Button)findViewById(R.id.idDialogButtonCancel);
        idDialogButtonAdd= (Button)findViewById(R.id.idDialogButtonAdd1);




        idDialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               finish();
            }
        });
        idDialogButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  idlayoutpurchase.setVisibility(View.VISIBLE);
              //  ValidateAddProduct();
                SharedPreferencesConstants.selcetedlobname=selcetedlobname;
                Toast.makeText(UpdateProductItemActivity.this, ""+ SharedPreferencesConstants.selcetedlobname, Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        new getlobspinnerdataAsyncTask().execute();
        new getGSTspinnerdataAsyncTask().execute();

    }
    public class getlobspinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateProductItemActivity.this);
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
                Lobname=new String[length];

                int j=0;
                Lobname[0]="Select Lobid";

                //  Namearray = new ArrayList<String>();
                for (int i = 0; i < length-1; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    j=j+1;
//                    Namearray.add (jsonObject.getString("Name"));
                    Lobname[j] = jsonObject.getString("Name");
//
//
//
//
                    Log.e("Name", "Name" + Lobname[j]);



                }




                final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
                        UpdateProductItemActivity.this, android.R.layout.simple_spinner_item,
                        Lobname);
                spinnercategory.setAdapter(adapter1);


                if ( SharedPreferencesConstants.selcetedlobname != null) {
                    int spinnerPosition = adapter1.getPosition( SharedPreferencesConstants.selcetedlobname);
                    spinnercategory.setSelection(spinnerPosition);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url = Config.Url+"addproduct.asmx";


            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "BindLobName");
            // request.addProperty("Celsius", "48"); // adding method property here serially

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            HttpTransportSE httpTransport = new HttpTransportSE(url);
            httpTransport.debug = true;

            try {
                httpTransport.call("http://tempuri.org/BindLobName", envelope);

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
    public class getGSTspinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateProductItemActivity.this);
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
                GST=new String[length];

                int j=0;
                GST[0]="Select GST";

                //  Namearray = new ArrayList<String>();
                for (int i = 0; i < length-1; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    j=j+1;
//                    Namearray.add (jsonObject.getString("Name"));
                    GST[j] = jsonObject.getString("TaxGroupName");
//
//
//
//
                    Log.e("GST", "GST" + GST[j]);



                }





                final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
                        UpdateProductItemActivity.this, android.R.layout.simple_spinner_item,
                        GST);
                spinnergst.setAdapter(adapter1);

                if (Gst != null) {
                    int spinnerPosition = adapter1.getPosition(Gst);
                    spinnergst.setSelection(spinnerPosition);
                }

//                if (selectedspinnervendername != null) {
//                    int spinnerPosition = adapter1.getPosition(selectedspinnervendername);
//                    spinnervendername.setSelection(spinnerPosition);
//                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url =Config.Url+"addproduct.asmx";


            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "BindGST");
            // request.addProperty("Celsius", "48"); // adding method property here serially

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            HttpTransportSE httpTransport = new HttpTransportSE(url);
            httpTransport.debug = true;

            try {
                httpTransport.call("http://tempuri.org/BindGST", envelope);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    public class getAllGSTspinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateProductItemActivity.this);
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
                if(length>0)
                {

                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    strSGST=jsonObject.getString("SGST");
                    strIGST=jsonObject.getString("IGST");
                    strCGST=jsonObject.getString("CGST");

                }
                Log.e("strSGST", "strSGST" + strSGST);

                idEditTextSGSTPer.setText(strSGST);
                idEditTextIGSTPer.setText(strIGST);
                idEditTextCGSTPer.setText(strCGST);


                new getattributespinnerdataAsyncTask().execute();


            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url =Config.Url+"addproduct.asmx";


            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "BindGST_ByType");
            request.addProperty("TaxGroupName", selcetedGst); // adding method property here serially

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            HttpTransportSE httpTransport = new HttpTransportSE(url);
            httpTransport.debug = true;

            try {
                httpTransport.call("http://tempuri.org/BindGST_ByType", envelope);

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

/*
    public class getAllGSTspinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateProductItemActivity.this);
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
                if(length>0)
                {

                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    strSGST=jsonObject.getString("SGST");
                    strIGST=jsonObject.getString("IGST");
                    strCGST=jsonObject.getString("CGST");

                }
                Log.e("strSGST", "strSGST" + strSGST);

                idEditTextSGSTPer.setText(strSGST);
                idEditTextIGSTPer.setText(strIGST);
                idEditTextCGSTPer.setText(strCGST);


                new getattributespinnerdataAsyncTask().execute();


            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url =Config.Url+"addproduct.asmx";


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
*/

    public class gettotalattributespinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdateProductItemActivity.this);
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
                if(length>0)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    TotalAttribute=jsonObject.getLong("TotalAttribute");

                }
                Log.e("TotalAttribute1", "TotalAttribute1" + TotalAttribute);




                new getattributespinnerdataAsyncTask().execute();


            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url =Config.Url+"addproduct.asmx";


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
            dialog = new ProgressDialog(UpdateProductItemActivity.this);
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
                atributename=new String[8];



                //  Namearray = new ArrayList<String>();
                for (int i = 0; i < length; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);


//                    Namearray.add (jsonObject.getString("Name"));
                    atributename[i] = jsonObject.getString("Name");
//
//
//
//
                    Log.e("Name", "Name" + atributename[i]);



                }


                if(TotalAttribute==1)
                {
                    layoutl1.setVisibility(View.VISIBLE);
                    layoutl2.setVisibility(View.GONE);
                    layoutl3.setVisibility(View.GONE);
                    layoutl4.setVisibility(View.GONE);
                    layoutl5.setVisibility(View.GONE);
                    layoutl6.setVisibility(View.GONE);
                    layoutl7.setVisibility(View.GONE);
                    layoutl8.setVisibility(View.GONE);
                    layoutl12.setVisibility(View.VISIBLE);
                    layoutl34.setVisibility(View.GONE);
                    layoutl56.setVisibility(View.GONE);
                    layoutl78.setVisibility(View.GONE);


                   /* editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);
*/
                }
                else
                if(TotalAttribute==2)
                {
                    layoutl1.setVisibility(View.VISIBLE);
                    layoutl2.setVisibility(View.VISIBLE);
                    layoutl3.setVisibility(View.GONE);
                    layoutl4.setVisibility(View.GONE);
                    layoutl5.setVisibility(View.GONE);
                    layoutl6.setVisibility(View.GONE);
                    layoutl7.setVisibility(View.GONE);
                    layoutl8.setVisibility(View.GONE);
                    layoutl12.setVisibility(View.VISIBLE);
                    layoutl34.setVisibility(View.GONE);
                    layoutl56.setVisibility(View.GONE);
                    layoutl78.setVisibility(View.GONE);


                   /* editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);*/
                }
                else
                if(TotalAttribute==3)
                {
                    layoutl1.setVisibility(View.VISIBLE);
                    layoutl2.setVisibility(View.VISIBLE);
                    layoutl3.setVisibility(View.VISIBLE);
                    layoutl4.setVisibility(View.GONE);
                    layoutl5.setVisibility(View.GONE);
                    layoutl6.setVisibility(View.GONE);
                    layoutl7.setVisibility(View.GONE);
                    layoutl8.setVisibility(View.GONE);
                    layoutl12.setVisibility(View.VISIBLE);
                    layoutl34.setVisibility(View.VISIBLE);
                    layoutl56.setVisibility(View.GONE);
                    layoutl78.setVisibility(View.GONE);


                    /*editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);*/
                }
                else
                if(TotalAttribute==4)
                {
                    layoutl1.setVisibility(View.VISIBLE);
                    layoutl2.setVisibility(View.VISIBLE);
                    layoutl3.setVisibility(View.VISIBLE);
                    layoutl4.setVisibility(View.VISIBLE);
                    layoutl5.setVisibility(View.GONE);
                    layoutl6.setVisibility(View.GONE);
                    layoutl7.setVisibility(View.GONE);
                    layoutl8.setVisibility(View.GONE);
                    layoutl12.setVisibility(View.VISIBLE);
                    layoutl34.setVisibility(View.VISIBLE);
                    layoutl56.setVisibility(View.GONE);
                    layoutl78.setVisibility(View.GONE);


                  /*  editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);*/
                }
                else
                if(TotalAttribute==5)
                {
                    layoutl1.setVisibility(View.VISIBLE);
                    layoutl2.setVisibility(View.VISIBLE);
                    layoutl3.setVisibility(View.VISIBLE);
                    layoutl4.setVisibility(View.VISIBLE);
                    layoutl5.setVisibility(View.VISIBLE);
                    layoutl6.setVisibility(View.GONE);
                    layoutl7.setVisibility(View.GONE);
                    layoutl8.setVisibility(View.GONE);
                    layoutl12.setVisibility(View.VISIBLE);
                    layoutl34.setVisibility(View.VISIBLE);
                    layoutl56.setVisibility(View.VISIBLE);
                    layoutl78.setVisibility(View.GONE);


                   /* editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);*/
                }else
                if(TotalAttribute==6)
                {
                    layoutl1.setVisibility(View.VISIBLE);
                    layoutl2.setVisibility(View.VISIBLE);
                    layoutl3.setVisibility(View.VISIBLE);
                    layoutl4.setVisibility(View.VISIBLE);
                    layoutl5.setVisibility(View.VISIBLE);
                    layoutl6.setVisibility(View.VISIBLE);
                    layoutl7.setVisibility(View.GONE);
                    layoutl8.setVisibility(View.GONE);
                    layoutl12.setVisibility(View.VISIBLE);
                    layoutl34.setVisibility(View.VISIBLE);
                    layoutl56.setVisibility(View.VISIBLE);
                    layoutl78.setVisibility(View.GONE);


                   /* editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);*/
                }
                else
                if(TotalAttribute==7)
                {
                    layoutl1.setVisibility(View.VISIBLE);
                    layoutl2.setVisibility(View.VISIBLE);
                    layoutl3.setVisibility(View.VISIBLE);
                    layoutl4.setVisibility(View.VISIBLE);
                    layoutl5.setVisibility(View.VISIBLE);
                    layoutl6.setVisibility(View.VISIBLE);
                    layoutl7.setVisibility(View.VISIBLE);
                    layoutl8.setVisibility(View.GONE);
                    layoutl12.setVisibility(View.VISIBLE);
                    layoutl34.setVisibility(View.VISIBLE);
                    layoutl56.setVisibility(View.VISIBLE);
                    layoutl78.setVisibility(View.VISIBLE);


                  /*  editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);*/
                }
                else
                if(TotalAttribute==8)
                {
                    layoutl1.setVisibility(View.VISIBLE);
                    layoutl2.setVisibility(View.VISIBLE);
                    layoutl3.setVisibility(View.VISIBLE);
                    layoutl4.setVisibility(View.VISIBLE);
                    layoutl5.setVisibility(View.VISIBLE);
                    layoutl6.setVisibility(View.VISIBLE);
                    layoutl7.setVisibility(View.VISIBLE);
                    layoutl8.setVisibility(View.VISIBLE);
                    layoutl12.setVisibility(View.VISIBLE);
                    layoutl34.setVisibility(View.VISIBLE);
                    layoutl56.setVisibility(View.VISIBLE);
                    layoutl78.setVisibility(View.VISIBLE);


                  /*  editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);*/
                }else

                {
                    layoutl1.setVisibility(View.GONE);
                    layoutl2.setVisibility(View.GONE);
                    layoutl3.setVisibility(View.GONE);
                    layoutl4.setVisibility(View.GONE);
                    layoutl5.setVisibility(View.GONE);
                    layoutl6.setVisibility(View.GONE);
                    layoutl7.setVisibility(View.GONE);
                    layoutl8.setVisibility(View.GONE);
                    layoutl12.setVisibility(View.GONE);
                    layoutl34.setVisibility(View.GONE);
                    layoutl56.setVisibility(View.GONE);
                    layoutl78.setVisibility(View.GONE);
                }












            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url =Config.Url+"addproduct.asmx";


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
}
