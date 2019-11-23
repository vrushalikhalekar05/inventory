package com.ampinity.inv.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
/*import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;*/
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ampinity.inv.Model.AddProductPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.Utility.ConnectionDetector;
import com.ampinity.inv.adapter.AddProductAdapter;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class UpdatePurchaseEntryActivity extends AppCompatActivity {


    EditText edittext;
    AlertDialog alertDialog1;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    Spinner spinnercategory,PaymentBy,spinnergst,spinnervendername;
    RadioGroup selectMode;
    ConnectionDetector cd;
    RadioButton radio_Paid,radio_UnPaid;
    int PIDint;
    String [] CompanyName={
            "Select Payment Mode",
            "Cash",
            "Cheque",
            "Net Banking"
    };
    int length;
    String [] VName;
    private static final String NAMESPACE = "http://tempuri.org/";

    String [] Lobname,atributename,GST;
    long TotalAttribute;
    float SGST,IGST,CGST;
    float totalsumsub;
    String strSGST,strIGST,strCGST;
    String selcetedlobname,selcetedGst;
    LinearLayout layoutl5,layoutl6,layoutl7,layoutl8,layoutl12,layoutl34,layoutl56,layoutl78;
    EditText idEditTextName,idEditTextItemCode,idEditTextHSNSACCode,editText,editTextlable2,editTextlable3,editTextlable4,editTextlable5,editTextlable6,editTextlable7,editTextlable8,idEditTextPurchasePrice,idEditTextper,idEditTextSalePrice,idEditTextSaleDesPer,idEditTextQuantity,idEditTextSGSTPer,idEditTextIGSTPer,idEditTextCGSTPer;
    String stridEditTextName,stridEditTextItemCode,stridEditTextHSNSACCode,streditText,streditTextlable2,streditTextlable3,streditTextlable4,streditTextlable5,streditTextlable6,streditTextlable7,streditTextlable8,stridEditTextPurchasePrice,stridEditTextper,stridEditTextSalePrice,stridEditTextSaleDesPer,stridEditTextQuantity,stridEditTextSGSTPer,stridEditTextIGSTPer,stridEditTextCGSTPer;
    float PurchasePrice,Discountper,DiscountAmount,SalesPrice,SalesDiscount,TotalDiscount,Singleproductprice,AllPoductprice,SGSTPER,SGSTAMT,IGSTPER,IGSTAMT,CGSTPER,CGSTAMT,TotalSubprice;
    String strDiscountAmount,strTotalDiscount,strSGSTAMT,strIGSTAMT,strCGSTAMT,strTotalSubprice;
    int QTY;
    boolean paid,unpaid;
    float TotalPaidAmount,TotalSGSTAMTItem,TotalIGSTAMTItem,TotalCGSTAMTItem,TotalDiscountItem;
    Button idButtonCancel,idDialogButtonCancel,idDialogButtonAdd,idButtonAddProduct,idButtonRemoveProduct,idButtonSave;
    EditText idEditTextPurchaseEntryName,idEditTextSupplierBillNo,idEditTextDate,idEditTextIDPurchaseEntry,idEditTextInWord,idEditTextPaidAmount,idEditTextRemainingAmount,idEditTextTotalSub,idEditTextDiscount,idEditTextTotalAmount,idEditTextDatePaid;
    String selectedspinnervendername, selectedspinnerpaymentby,stridEditTextPurchaseEntryName,stridEditTextSupplierBillNo,stridEditTextDate,stridEditTextIDPurchaseEntry,stridEditTextInWord,stridEditTextPaidAmount,stridEditTextRemainingAmount,stridEditTextTotalSub,stridEditTextDiscount,stridEditTextTotalAmount,stridEditTextDatePaid;

    LinearLayout idlayoutpurchase, layoutl1,layoutl2,layoutl3,layoutl4;
    String id;
    Bundle bundle;
    int mHeigh;
    LinearLayout.LayoutParams params,params1;


    String[] ID,PID,Name,ItemCode,HSNSACCode,lable1,lable2,lable3,lable4,lable5,lable6,lable7,lable8,PurchasePriceitem,per,SalePrice,SaleDesPer,Quantity,Gst,LobName,Discount,SGSTPERItem,SGSTAMTItem,IGSTPERItem,IGSTAMTItem,CGSTPERItem,CGSTAMTItem,SubPriceItem;
    ArrayList<AddProductPojo> items = new ArrayList<>();
    ListView lview;
    AddProductPojo addProductPojo;
    String sno;
    static AddProductAdapter CompanyAdapte;

    LinearLayout listviewlinearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_purchase_entry);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Purchase Entry");

        new myProductAsyncTask().execute();

        PaymentBy = (Spinner) findViewById(R.id.PaymentBy);
        spinnervendername = (Spinner) findViewById(R.id.spinnervendername);
        final ArrayAdapter<String> adapter2 = new CustomizedSpinnerAdapter(
                UpdatePurchaseEntryActivity.this, android.R.layout.simple_spinner_item,
                CompanyName);
        PaymentBy.setAdapter(adapter2);

        lview = (ListView) findViewById(R.id.listview);

        myCalendar = Calendar.getInstance();

        selectMode = (RadioGroup) findViewById(R.id.selectMode);
        radio_Paid= (RadioButton) findViewById(R.id.radio_Paid);
        radio_UnPaid= (RadioButton) findViewById(R.id.radio_UnPaid);

        idEditTextIDPurchaseEntry= (EditText) findViewById(R.id.idEditTextIDPurchaseEntry);
        idEditTextPurchaseEntryName= (EditText) findViewById(R.id.idEditTextPurchaseEntryName);
        idEditTextSupplierBillNo= (EditText) findViewById(R.id.idEditTextSupplierBillNo);
        idEditTextDatePaid= (EditText) findViewById(R.id.idEditTextDatePaid);
        idEditTextDate= (EditText) findViewById(R.id.idEditTextDate);
        idEditTextInWord= (EditText) findViewById(R.id.idEditTextInWord);
        idEditTextPaidAmount= (EditText) findViewById(R.id.idEditTextPaidAmount);
        idEditTextRemainingAmount= (EditText) findViewById(R.id.idEditTextRemainingAmount);
        idEditTextTotalSub= (EditText) findViewById(R.id.idEditTextTotalSub);
        idEditTextDiscount= (EditText) findViewById(R.id.idEditTextDiscount);
        idEditTextTotalAmount= (EditText) findViewById(R.id.idEditTextTotalAmount);

        idlayoutpurchase=(LinearLayout)findViewById(R.id.idlayoutpurchase);
        idlayoutpurchase.setVisibility(View.GONE);
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        idEditTextDatePaid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(UpdatePurchaseEntryActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        idEditTextDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(UpdatePurchaseEntryActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        idButtonRemoveProduct=(Button)findViewById(R.id.idButtonRemoveProduct);
        idButtonAddProduct=(Button)findViewById(R.id.idButtonAddProduct);
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
        idButtonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idEditTextPurchaseEntryName.setEnabled(false);
                idEditTextPurchaseEntryName.setInputType(InputType.TYPE_NULL);
                idEditTextPurchaseEntryName.setFocusable(false);
                idEditTextSupplierBillNo.setEnabled(false);
                idEditTextSupplierBillNo.setInputType(InputType.TYPE_NULL);
                idEditTextSupplierBillNo.setFocusable(false);
                idEditTextDate.setEnabled(false);
                idEditTextDate.setInputType(InputType.TYPE_NULL);
                idEditTextDate.setFocusable(false);
                dialogproduct();


            }
        });
        idButtonRemoveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idlayoutpurchase.setVisibility(View.GONE);

            }
        });
        bundle = new Bundle();
        bundle = getIntent().getExtras();
        selectedspinnervendername=bundle.getString("vendername").toString();
        selectedspinnerpaymentby=bundle.getString("paymentby").toString();
        stridEditTextSupplierBillNo=bundle.getString("SupplierBillNo").toString();
        stridEditTextDate=bundle.getString("Date").toString();
        stridEditTextIDPurchaseEntry=bundle.getString("IDPurchaseEntry").toString();
        stridEditTextInWord=bundle.getString("InWord").toString();
        stridEditTextPaidAmount=bundle.getString("PaidAmount").toString();
        stridEditTextRemainingAmount=bundle.getString("RemainingAmount").toString();
        stridEditTextTotalSub=bundle.getString("TotalSub").toString();
        stridEditTextDiscount=bundle.getString("Discount").toString();
        stridEditTextTotalAmount=bundle.getString("TotalAmount").toString();
        stridEditTextDatePaid=bundle.getString("DatePaid").toString();


        idEditTextSupplierBillNo.setText(stridEditTextSupplierBillNo);
        idEditTextDate.setText(stridEditTextDate);
        idEditTextIDPurchaseEntry.setText(stridEditTextIDPurchaseEntry);
        idEditTextInWord.setText(stridEditTextInWord);
        idEditTextPaidAmount.setText(stridEditTextPaidAmount);
        idEditTextRemainingAmount.setText(stridEditTextRemainingAmount);
        idEditTextTotalSub.setText(stridEditTextTotalSub);
        idEditTextDiscount.setText(stridEditTextDiscount);
        idEditTextTotalAmount.setText(stridEditTextTotalAmount);
        idEditTextDatePaid.setText(stridEditTextDatePaid);





        if (selectedspinnerpaymentby != null) {
            int spinnerPosition = adapter2.getPosition(selectedspinnerpaymentby);
            PaymentBy.setSelection(spinnerPosition);
        }

        new getvendernamespinnerdataAsyncTask().execute();
        populateList();
    }



    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        idEditTextDate.setText(sdf.format(myCalendar.getTime()));
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
    public void  paidradio(View view)
    {
        radio_UnPaid.setChecked(false);
        radio_Paid.setChecked(true);
    }
    public void  Unpaidradio(View view)
    {
        radio_UnPaid.setChecked(true);
        radio_Paid.setChecked(false);
    }

    public class getvendernamespinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdatePurchaseEntryActivity.this);
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
                VName=new String[length];

                int j=0;
                VName[0]="Select Name";

                //  Namearray = new ArrayList<String>();
                for (int i = 0; i < length-1; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    j=j+1;
//                    Namearray.add (jsonObject.getString("Name"));
                    VName[j] = jsonObject.getString("Name");
//
//
//
//
                    Log.e("VName", "VName" + VName[j]);



                }






                final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(UpdatePurchaseEntryActivity.this, android.R.layout.simple_spinner_item,
                        VName);
                spinnervendername.setAdapter(adapter1);


                if (selectedspinnervendername != null) {
                    int spinnerPosition = adapter1.getPosition(selectedspinnervendername);
                    spinnervendername.setSelection(spinnerPosition);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url = Config.Url+"PurchaseDetails.asmx";


            //for linear parameter
            SoapObject request = new SoapObject("http://tempuri.org/", "BindVendor");
            // request.addProperty("Celsius", "48"); // adding method property here serially

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            HttpTransportSE httpTransport = new HttpTransportSE(url);
            httpTransport.debug = true;

            try {
                httpTransport.call("http://tempuri.org/BindVendor", envelope);

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

    public void validate()
    {
        cd=new ConnectionDetector(getApplicationContext());

        selectedspinnervendername = spinnervendername.getSelectedItem().toString();
        selectedspinnerpaymentby = PaymentBy.getSelectedItem().toString();


        if (!cd.isConnectingToInternet()) {
            Toast.makeText(getApplicationContext(), "Network Unavailable", Toast.LENGTH_SHORT).show();

        }
        else
        if (idEditTextSupplierBillNo.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter SupplierBillNo",Toast.LENGTH_SHORT).show();
        } else
        if (idEditTextDate.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Date",Toast.LENGTH_SHORT).show();
        }

        else if (selectedspinnervendername.equals("Select Name")){
            Toast.makeText(this,"Please Select Name",Toast.LENGTH_SHORT).show();
        }

        else{

            stridEditTextPurchaseEntryName=idEditTextPurchaseEntryName.getText().toString();
            stridEditTextSupplierBillNo=idEditTextSupplierBillNo.getText().toString();
            stridEditTextDate=idEditTextDate.getText().toString();
            stridEditTextIDPurchaseEntry=idEditTextIDPurchaseEntry.getText().toString();
            stridEditTextInWord=idEditTextInWord.getText().toString();
            stridEditTextPaidAmount=idEditTextPaidAmount.getText().toString();
            stridEditTextRemainingAmount=idEditTextRemainingAmount.getText().toString();
            stridEditTextTotalSub=idEditTextTotalSub.getText().toString();
            stridEditTextDiscount=idEditTextDiscount.getText().toString();
            stridEditTextTotalAmount=idEditTextTotalAmount.getText().toString();
            stridEditTextDatePaid=idEditTextDatePaid.getText().toString();




            new UpdatePurchaseEntryInsertDetailtAsyncTask().execute();
        }
    }

    public class UpdatePurchaseEntryInsertDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    {

        ProgressDialog dialog;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdatePurchaseEntryActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            finish();
            Toast.makeText(UpdatePurchaseEntryActivity.this,"Updated Succesfully",Toast.LENGTH_SHORT).show();
            new LastUpdateProductEntryInsertDetailttestAsyncTask().execute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            String url =Config.Url+"PurchaseDetails.asmx";
            String soapactionaddlob ="http://tempuri.org/Update_New";
            String methodname="Update_New";
            String namespacesaalob="http://tempuri.org/";
            try {


                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);




                requestAddlob.addProperty("Id",stridEditTextIDPurchaseEntry );
                requestAddlob.addProperty("SupplierName", selectedspinnervendername);
                requestAddlob.addProperty("SupplierBillDate", stridEditTextDate);
                requestAddlob.addProperty("SupplierBillNo",stridEditTextSupplierBillNo );
                requestAddlob.addProperty("Inwords", stridEditTextInWord);
                requestAddlob.addProperty("PaymentBy", selectedspinnerpaymentby);
                requestAddlob.addProperty("PaidAmount", stridEditTextPaidAmount);
                requestAddlob.addProperty("RemainingAmount",stridEditTextRemainingAmount );
                requestAddlob.addProperty("Paid",paid);
                requestAddlob.addProperty("UnPaid",unpaid );
                requestAddlob.addProperty("SubTotal", stridEditTextTotalSub);
                requestAddlob.addProperty("Discount", stridEditTextDiscount);
                requestAddlob.addProperty("SGSTAmount",TotalSGSTAMTItem );
                requestAddlob.addProperty("CGSTAmount", TotalCGSTAMTItem);
                requestAddlob.addProperty("IGSTAmount", TotalIGSTAMTItem);
                requestAddlob.addProperty("TotalAmount",stridEditTextTotalAmount );



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

//dia



    public void dialogproduct(){

        alertDialog1 = new AlertDialog.Builder( UpdatePurchaseEntryActivity.this).create();
        LayoutInflater layoutInflater = (LayoutInflater)  UpdatePurchaseEntryActivity.this.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        View view1 = layoutInflater.inflate(R.layout.dialogproduct, null);
        alertDialog1.setView(view1);
        alertDialog1.show();
        alertDialog1.setCancelable(true);
        spinnergst= (Spinner) view1.findViewById(R.id.spinnergst);
        spinnercategory = (Spinner) view1.findViewById(R.id.spinnercategory);

        layoutl1=(LinearLayout)view1.findViewById(R.id.layoutl1) ;
        layoutl2=(LinearLayout)view1.findViewById(R.id.layoutl2) ;
        layoutl3=(LinearLayout)view1.findViewById(R.id.layoutl3) ;
        layoutl4=(LinearLayout)view1.findViewById(R.id.layoutl4) ;
        layoutl5=(LinearLayout)view1.findViewById(R.id.layoutl5) ;
        layoutl6=(LinearLayout)view1.findViewById(R.id.layoutl6) ;
        layoutl7=(LinearLayout)view1.findViewById(R.id.layoutl7) ;
        layoutl8=(LinearLayout)view1.findViewById(R.id.layoutl8) ;
        layoutl12=(LinearLayout)view1.findViewById(R.id.layoutl12) ;
        layoutl34=(LinearLayout)view1.findViewById(R.id.layoutl34) ;
        layoutl56=(LinearLayout)view1.findViewById(R.id.layoutl56) ;
        layoutl78=(LinearLayout)view1.findViewById(R.id.layoutl78) ;

        idEditTextName=(EditText) view1.findViewById(R.id.idEditTextName) ;
        idEditTextItemCode=(EditText) view1.findViewById(R.id.idEditTextItemCode) ;
        idEditTextHSNSACCode=(EditText) view1.findViewById(R.id.idEditTextHSNSACCode) ;
        editText=(EditText) view1.findViewById(R.id.editText) ;
        editTextlable2=(EditText) view1.findViewById(R.id.editTextlable2) ;
        editTextlable3=(EditText) view1.findViewById(R.id.editTextlable3) ;
        editTextlable4=(EditText) view1.findViewById(R.id.editTextlable4) ;
        editTextlable5=(EditText) view1.findViewById(R.id.editTextlable5) ;
        editTextlable6=(EditText) view1.findViewById(R.id.editTextlable6) ;
        editTextlable7=(EditText) view1.findViewById(R.id.editTextlable7) ;
        editTextlable8=(EditText) view1.findViewById(R.id.editTextlable8) ;
        idEditTextPurchasePrice=(EditText) view1.findViewById(R.id.idEditTextPurchasePrice) ;
        idEditTextper=(EditText) view1.findViewById(R.id.idEditTextper) ;
        idEditTextSalePrice=(EditText) view1.findViewById(R.id.idEditTextSalePrice) ;
        idEditTextSaleDesPer=(EditText) view1.findViewById(R.id.idEditTextSaleDesPer) ;
        idEditTextQuantity=(EditText) view1.findViewById(R.id.idEditTextQuantity) ;
        idEditTextSGSTPer=(EditText) view1.findViewById(R.id.idEditTextSGSTPer) ;
        idEditTextIGSTPer=(EditText) view1.findViewById(R.id.idEditTextIGSTPer) ;
        idEditTextCGSTPer=(EditText) view1.findViewById(R.id.idEditTextCGSTPer) ;


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

        idDialogButtonCancel= (Button) view1.findViewById(R.id.idDialogButtonCancel);
        idDialogButtonAdd= (Button) view1.findViewById(R.id.idDialogButtonAdd);




        idDialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                alertDialog1.dismiss();
            }
        });
        idDialogButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  idlayoutpurchase.setVisibility(View.VISIBLE);
                ValidateAddProduct();
                alertDialog1.dismiss();
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
            dialog = new ProgressDialog(UpdatePurchaseEntryActivity.this);
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
                        UpdatePurchaseEntryActivity.this, android.R.layout.simple_spinner_item,
                        Lobname);
                spinnercategory.setAdapter(adapter1);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            dialog.dismiss();
        }

        @Override
        protected String doInBackground(String... arg) {

            String url =Config.Url+"addproduct.asmx";


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

    public class gettotalattributespinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdatePurchaseEntryActivity.this);
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
            dialog = new ProgressDialog(UpdatePurchaseEntryActivity.this);
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


                    editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);

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


                    editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);
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


                    editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);
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


                    editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);
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


                    editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);
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


                    editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);
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


                    editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);
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


                    editText.setHint(atributename[0]);
                    editTextlable2.setHint(atributename[1]);
                    editTextlable3.setHint(atributename[2]);
                    editTextlable4.setHint(atributename[3]);
                    editTextlable5.setHint(atributename[4]);
                    editTextlable6.setHint(atributename[5]);
                    editTextlable7.setHint(atributename[6]);
                    editTextlable8.setHint(atributename[7]);
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

    public class getGSTspinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdatePurchaseEntryActivity.this);
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
                        UpdatePurchaseEntryActivity.this, android.R.layout.simple_spinner_item,
                        GST);
                spinnergst.setAdapter(adapter1);

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

    public class getAllGSTspinnerdataAsyncTask extends AsyncTask<String, Integer, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdatePurchaseEntryActivity.this);
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


    public void ValidateAddProduct()
    {
        cd=new ConnectionDetector(getApplicationContext());

        selcetedGst =spinnergst .getSelectedItem().toString();
        selcetedlobname = spinnercategory.getSelectedItem().toString();

        stridEditTextName=idEditTextName.getText().toString();
        stridEditTextItemCode=idEditTextItemCode.getText().toString();
        stridEditTextHSNSACCode=idEditTextHSNSACCode.getText().toString();


        if (editText.getText().toString().length()==0){
            streditText="-";
        }
        else
        {
            streditText=editText.getText().toString();
        }


        if (editTextlable2.getText().toString().length()==0){
            streditTextlable2="-";
        }
        else
        {
            streditTextlable2=editText.getText().toString();
        }

        if (editTextlable3.getText().toString().length()==0){
            streditTextlable3="-";
        }
        else
        {
            streditTextlable3=editText.getText().toString();
        }



        if (editTextlable4.getText().toString().length()==0){
            streditTextlable4="-";
        }
        else
        {
            streditTextlable4=editTextlable4.getText().toString();
        }

        if (editTextlable5.getText().toString().length()==0){
            streditTextlable5="-";
        }
        else
        {
            streditTextlable5=editTextlable5.getText().toString();
        }


        if (editTextlable6.getText().toString().length()==0){
            streditTextlable6="-";
        }
        else
        {
            streditTextlable6=editTextlable6.getText().toString();
        }
        if (editTextlable7.getText().toString().length()==0){
            streditTextlable7="-";
        }
        else
        {
            streditTextlable7=editTextlable7.getText().toString();
        }
        if (editTextlable8.getText().toString().length()==0){
            streditTextlable8="-";
        }
        else
        {
            streditTextlable8=editTextlable8.getText().toString();
        }

        stridEditTextPurchasePrice=idEditTextPurchasePrice.getText().toString();
        stridEditTextper=idEditTextper.getText().toString();
        stridEditTextSalePrice=idEditTextSalePrice.getText().toString();
        stridEditTextSaleDesPer=idEditTextSaleDesPer.getText().toString();
        stridEditTextQuantity=idEditTextQuantity.getText().toString();
        stridEditTextSGSTPer=idEditTextSGSTPer.getText().toString();
        stridEditTextIGSTPer=idEditTextIGSTPer.getText().toString();
        stridEditTextCGSTPer=idEditTextCGSTPer.getText().toString();

        if (!cd.isConnectingToInternet()) {
            Toast.makeText(getApplicationContext(), "Network Unavailable", Toast.LENGTH_SHORT).show();

        }
        else
        if (idEditTextName.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Name",Toast.LENGTH_SHORT).show();
        } else
        if (idEditTextName.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Name",Toast.LENGTH_SHORT).show();
        } else
        if (idEditTextHSNSACCode.getText().length()==0){
            Toast.makeText(this,"Please Enter HSNSAC Code",Toast.LENGTH_SHORT).show();
        }
        else
        if (idEditTextQuantity.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Quantity",Toast.LENGTH_SHORT).show();
        }
        else
        if (idEditTextPurchasePrice.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Purchase Price.",Toast.LENGTH_SHORT).show();
        }else
        if (idEditTextSalePrice.getText().toString().length()==0){
            Toast.makeText(this,"Please Enter Sale Price",Toast.LENGTH_SHORT).show();
        }


        else if (selcetedGst.equals("Select GST")){
            Toast.makeText(this,"Please Select GST",Toast.LENGTH_SHORT).show();
        }
        else if (selcetedlobname.equals("Select Lobid")){
            Toast.makeText(this,"Please Select GST",Toast.LENGTH_SHORT).show();
        }


        else{




            PurchasePrice= Float.parseFloat(stridEditTextPurchasePrice);


            Log.e("PurchasePrice", "PurchasePrice: "+PurchasePrice );
            if(stridEditTextper.length()==0)
            {
                Discountper=0;
            }
            else
            {
                Discountper= Float.parseFloat(stridEditTextper);
            }
            Log.e("Discountper", "Discountper: "+Discountper );

            if( Discountper==0)
            {
                DiscountAmount=0;
            }
            else
            {
                DiscountAmount=(PurchasePrice*Discountper)/100;
            }
            Log.e("DiscountAmount", "DiscountAmount: "+DiscountAmount );
            SalesPrice= Float.parseFloat(stridEditTextSalePrice);
            Log.e("SalesPrice", "SalesPrice: "+SalesPrice );
            if(stridEditTextSaleDesPer.length()==0)
            {
                SalesDiscount=0;
            }
            else
            {
                SalesDiscount= Float.parseFloat(stridEditTextSaleDesPer);
            }

            QTY= Integer.parseInt(stridEditTextQuantity);
            Log.e("QTY", "QTY: "+QTY );
            Singleproductprice=PurchasePrice-DiscountAmount;
            Log.e("Singleproductprice", "Singleproductprice: "+Singleproductprice );
            AllPoductprice=Singleproductprice*QTY;
            Log.e("AllPoductprice", "AllPoductprice: "+AllPoductprice );
            TotalDiscount=DiscountAmount*QTY;
            Log.e("TotalDiscount", "TotalDiscount: "+TotalDiscount );

            strTotalDiscount= String.valueOf(TotalDiscount);

            if(stridEditTextSGSTPer.length()==0)
            {
                SGSTPER=0;
            }
            else
            {
                SGSTPER= Float.parseFloat(stridEditTextSGSTPer);
            }

            Log.e("SGSTPER", "SGSTPER: "+SGSTPER );
            if( SGSTPER==0)
            {
                SGSTAMT=0;
            }
            else
            {
                SGSTAMT=(AllPoductprice*SGSTPER)/100;
            }
            Log.e("SGSTAMT", "SGSTAMT: "+SGSTAMT );
            strSGSTAMT= String.valueOf(SGSTAMT);
            if(stridEditTextIGSTPer.length()==0)
            {
                IGSTPER=0;
            }
            else
            {
                IGSTPER= Float.parseFloat(stridEditTextSGSTPer);
            }
            Log.e("IGSTPER", "IGSTPER: "+IGSTPER );
            if( IGSTPER==0)
            {
                IGSTAMT=0;
            }
            else

            {
                IGSTAMT=(AllPoductprice*IGSTPER)/100;
            }
            Log.e("IGSTAMT", "IGSTAMT: "+IGSTAMT );
            strIGSTAMT= String.valueOf(IGSTAMT);
            if(stridEditTextCGSTPer.length()==0)
            {
                CGSTPER=0;
            }
            else
            {
                CGSTPER= Float.parseFloat(stridEditTextCGSTPer);
            }
            Log.e("CGSTPER", "CGSTPER: "+CGSTPER );
            if( CGSTPER==0)
            {
                CGSTAMT=0;
            }
            else
            {
                CGSTAMT=(AllPoductprice*CGSTPER)/100;
            }
            Log.e("CGSTAMT", "CGSTAMT: "+CGSTAMT );
            strCGSTAMT= String.valueOf(CGSTAMT);


            TotalSubprice=AllPoductprice+CGSTAMT+IGSTAMT+SGSTAMT;
            Log.e("TotalSubprice", "TotalSubprice: "+TotalSubprice );

            strTotalSubprice= String.valueOf(TotalSubprice);

            PIDint=Integer.parseInt(stridEditTextIDPurchaseEntry);


            Log.e("PId",stridEditTextIDPurchaseEntry );
            Log.e("ItemName", stridEditTextName);
            Log.e("ItemCode", stridEditTextItemCode);
            Log.e("Hsnscode",stridEditTextHSNSACCode );
            Log.e("Size",streditTextlable3 );
            Log.e("Unit", streditTextlable4);
            Log.e("Qty", stridEditTextQuantity);
            Log.e("PurchasePrice", stridEditTextPurchasePrice );
            Log.e("DisPer",stridEditTextper);
            Log.e("DisAmount",strTotalDiscount );
            Log.e("GST", selcetedGst);
            Log.e("SGSTPer", stridEditTextSGSTPer);
            Log.e("SGSTAmount",strSGSTAMT );
            Log.e("CGSTPer", stridEditTextCGSTPer);
            Log.e("CGSTAmount", strCGSTAMT);
            Log.e("IGSTper", stridEditTextIGSTPer);
            Log.e("IGSTAmount", strIGSTAMT);
            Log.e("SalePrice",stridEditTextSalePrice );
            Log.e("SaleDisper",stridEditTextSaleDesPer );
            Log.e("SubTotal",strTotalSubprice );
            Log.e("Opt1", streditText);
            Log.e("Opt2",streditTextlable2 );
            Log.e("Opt3", streditTextlable5);
            Log.e("Opt4", streditTextlable6);
            Log.e("Opt5", streditTextlable7);
            Log.e("Opt6", streditTextlable8);

            /*
             "Id": 4,

    "PurchasePrice": 1.0,
    "DisPer": 1.0,
    "DisAmount": 1.0,

    "SGSTPer": 1.0,
    "SGSTAmount": 1.0,
    "CGSTPer": 1.0,
    "CGSTAmount": 1.0,
    "IGSTper": 1.0,
    "IGSTAmount": 1.0,
    "SalePrice": 1.0,
    "SaleDisper": 1.0,
    "SubTotal": 1.0,
    "Opt1": null,
    "Opt2": null,
    "Opt3": null,
    "Opt4": null,
    "Opt5": null,
    "Opt6": null
             */

            new AddProductEntryInsertDetailttestAsyncTask().execute();
        }
    }

    public class AddProductEntryInsertDetailtAsyncTask extends AsyncTask<Void, Void, Void>
    {

        ProgressDialog dialog;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdatePurchaseEntryActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();

            Toast.makeText(UpdatePurchaseEntryActivity.this,"Added Succesfully",Toast.LENGTH_SHORT).show();
            // new AddAttributeDetailtAsyncTask().execute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            String url =Config.Url+"addproduct.asmx";
            String soapactionaddlob ="http://tempuri.org/Insert_Purchase_relation_new";
            String methodname="Insert_Purchase_relation_new";
            String namespacesaalob="http://tempuri.org/";
            try {

//    float PurchasePrice,Discountper,DiscountAmount,SalesPrice,SalesDiscount,QTY,TotalDiscount,Singleproductprice,AllPoductprice,SGSTPER,SGSTAMT,IGSTPER,
// IGSTAMT,CGSTPER,CGSTAMT,TotalSubprice;

                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);

                // String PId, String ItemName, String ItemCode, String Hsnscode,
                // String Size, String Unit, String Qty, String PurchasePrice,
                // String DisPer, String DisAmount, String GST,
                // String SGSTPer, String SGSTAmount, String CGSTPer, String CGSTAmount,
                // String IGSTper, String IGSTAmount, String SalePrice, String SaleDisper,
                // String SubTotal, String Opt1, String Opt2, String Opt3, String Opt4, String Opt5, String Opt6
                // ) in D:\INETPUB\VHOSTS\peritistech.co.in\ampinventory.ampinityit.com\App_Code\addproduct.vb:line 61



                requestAddlob.addProperty("PId",stridEditTextIDPurchaseEntry );
                requestAddlob.addProperty("ItemName", stridEditTextName);
                requestAddlob.addProperty("ItemCode", stridEditTextItemCode);
                requestAddlob.addProperty("Hsnscode",stridEditTextHSNSACCode );
                requestAddlob.addProperty("Size",streditTextlable3 );
                requestAddlob.addProperty("Unit", streditTextlable4);
                requestAddlob.addProperty("Qty", stridEditTextQuantity);
                requestAddlob.addProperty("PurchasePrice", stridEditTextPurchasePrice );
                requestAddlob.addProperty("DisPer",stridEditTextper);
                requestAddlob.addProperty("DisAmount",strTotalDiscount );
                requestAddlob.addProperty("GST", selcetedGst);
                requestAddlob.addProperty("SGSTPer", stridEditTextSGSTPer);
                requestAddlob.addProperty("SGSTAmount",strSGSTAMT );
                requestAddlob.addProperty("CGSTPer", stridEditTextCGSTPer);
                requestAddlob.addProperty("CGSTAmount", strCGSTAMT);
                requestAddlob.addProperty("IGSTper", stridEditTextIGSTPer);
                requestAddlob.addProperty("IGSTAmount", strIGSTAMT);
                requestAddlob.addProperty("SalePrice",stridEditTextSalePrice );
                requestAddlob.addProperty("SaleDisper",stridEditTextSaleDesPer );
                requestAddlob.addProperty("SubTotal",strTotalSubprice );
                requestAddlob.addProperty("Opt1", streditText);
                requestAddlob.addProperty("Opt2",streditTextlable2 );
                requestAddlob.addProperty("Opt3", streditTextlable5);
                requestAddlob.addProperty("Opt4", streditTextlable6);
                requestAddlob.addProperty("Opt5", streditTextlable7);
                requestAddlob.addProperty("Opt6", streditTextlable8);




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

    public class AddProductEntryInsertDetailttestAsyncTask extends AsyncTask<Void, Void, Void>
    {

        ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdatePurchaseEntryActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(UpdatePurchaseEntryActivity.this,"Added Succesfully",Toast.LENGTH_SHORT).show();
            new myProductAsyncTask().execute();


        }

        @Override
        protected Void doInBackground(Void... params) {


            String url =Config.Url+"addproduct.asmx";
            String soapactionaddlob ="http://tempuri.org/Insert_Purchase_relation_new";
            String methodname="Insert_Purchase_relation_new";
            String namespacesaalob="http://tempuri.org/";

            try {

                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);








                requestAddlob.addProperty("PId",stridEditTextIDPurchaseEntry );
                requestAddlob.addProperty("ItemName", stridEditTextName);
                requestAddlob.addProperty("ItemCode", stridEditTextItemCode);
                requestAddlob.addProperty("Hsnscode",stridEditTextHSNSACCode );
                requestAddlob.addProperty("Size",streditTextlable3 );
                requestAddlob.addProperty("Unit", streditTextlable4);
                requestAddlob.addProperty("Qty", stridEditTextQuantity);
                requestAddlob.addProperty("PurchasePrice", stridEditTextPurchasePrice );
                requestAddlob.addProperty("DisPer",stridEditTextper);
                requestAddlob.addProperty("DisAmount",strTotalDiscount );
                requestAddlob.addProperty("GST", selcetedGst);
                requestAddlob.addProperty("SGSTPer", stridEditTextSGSTPer);
                requestAddlob.addProperty("SGSTAmount",strSGSTAMT );
                requestAddlob.addProperty("CGSTPer", stridEditTextCGSTPer);
                requestAddlob.addProperty("CGSTAmount", strCGSTAMT);
                requestAddlob.addProperty("IGSTper", stridEditTextIGSTPer);
                requestAddlob.addProperty("IGSTAmount", strIGSTAMT);
                requestAddlob.addProperty("SalePrice",stridEditTextSalePrice );
                requestAddlob.addProperty("SaleDisper",stridEditTextSaleDesPer );
                requestAddlob.addProperty("SubTotal",strTotalSubprice );
                requestAddlob.addProperty("Opt1", streditText);
                requestAddlob.addProperty("Opt2",streditTextlable2 );
                requestAddlob.addProperty("Opt3", streditTextlable5);
                requestAddlob.addProperty("Opt4", streditTextlable6);
                requestAddlob.addProperty("Opt5", streditTextlable7);
                requestAddlob.addProperty("Opt6", streditTextlable8);
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


    public class LastUpdateProductEntryInsertDetailttestAsyncTask extends AsyncTask<Void, Void, Void>
    {

        ProgressDialog dialog;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(UpdatePurchaseEntryActivity.this);
            dialog.setMessage("Please Wait...");
            dialog.show();
            dialog.setCancelable(false);


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            // Toast.makeText(AddPurchaseEntryActivity.this,"Added Succesfully",Toast.LENGTH_SHORT).show();


        }

        @Override
        protected Void doInBackground(Void... params) {


            String url =Config.Url+"PurchaseDetails.asmx";
            String soapactionaddlob ="http://tempuri.org/Update_PurachaseRelaration_New";
            String methodname="Update_PurachaseRelaration_New";
            String namespacesaalob="http://tempuri.org/";

            try {

                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);








                requestAddlob.addProperty("PId",stridEditTextIDPurchaseEntry );
                requestAddlob.addProperty("Qty", "");
                requestAddlob.addProperty("IsAdded", "true");
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



    //listview
    public  void populateList() {

        new myProductAsyncTask().execute();
    }

    public class myProductAsyncTask extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPostExecute(String  result) {
            super.onPostExecute(result);
            items.clear();
            Toast.makeText(UpdatePurchaseEntryActivity.this, ""+result, Toast.LENGTH_SHORT).show();

            try {


                JSONArray jsonArray = new JSONArray(String.valueOf(result));


                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                length = jsonArray.length();
                if(length>0)
                {
                   // mHeigh=length*30;
                    //  params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,mHeigh);
                    //  listviewlinearlayout.setLayoutParams(params);
                   // lview .setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mHeigh));
                    // lview .setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,mHeigh));
                    idlayoutpurchase.setVisibility(View.VISIBLE);
                }
                else
                {
                    idlayoutpurchase.setVisibility(View.GONE);
                }

                Log.e("length", "length" + length);
                ID= new String[length];
                PID= new String[length];
                Name= new String[length];
                ItemCode= new String[length];
                HSNSACCode= new String[length];
                lable1= new String[length];
                lable2= new String[length];
                lable3= new String[length];
                lable4= new String[length];
                lable5= new String[length];
                lable6= new String[length];
                lable7= new String[length];
                lable8= new String[length];
                PurchasePriceitem= new String[length];
                per= new String[length];
                SalePrice= new String[length];
                SaleDesPer= new String[length];
                Quantity= new String[length];
                Gst= new String[length];
                LobName= new String[length];
                Discount= new String[length];
                SGSTPERItem= new String[length];
                SGSTAMTItem= new String[length];
                IGSTPERItem= new String[length];
                IGSTAMTItem= new String[length];
                CGSTPERItem= new String[length];
                CGSTAMTItem= new String[length];
                SubPriceItem= new String[length];

                TotalPaidAmount=0;
                TotalSGSTAMTItem=0;
                TotalIGSTAMTItem=0;
                TotalCGSTAMTItem=0;
                TotalDiscountItem=0;




                for (int i = 0; i < length; i++) {
 /*
      <PaidAmount>string</PaidAmount>
      <RemainingAmount>string</RemainingAmount>
      <Paid>string</Paid>
      <UnPaid>string</UnPaid>
      <SubTotal>string</SubTotal>
      <Discount>string</Discount>
      <SGSTAmount>string</SGSTAmount>
      <CGSTAmount>string</CGSTAmount>
      <IGSTAmount>string</IGSTAmount>
      <TotalAmount>string</TotalAmount>
    Name,ItemCode,HSNSACCode,lable1,lable2,lable3,lable4,lable5,lable6,lable7,lable8,PurchasePrice,per,SalePrice,SaleDesPer,Quantity,Gst,LobName;
*/
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    ID[i] = jsonObject.getString("Id");
                    PID[i] = jsonObject.getString("PId");
                    Name[i] = jsonObject.getString("ItemName");
                    ItemCode[i] = jsonObject.getString("ItemCode");
                    HSNSACCode[i] = jsonObject.getString("Hsnscode");
                    lable3[i] = jsonObject.getString("Size");
                    lable4[i]= jsonObject.getString("Unit");
                    Quantity[i] = jsonObject.getString("Qty");
                    PurchasePriceitem[i] = jsonObject.getString("PurchasePrice");
                    per[i] = jsonObject.getString("DisPer");
                    Discount[i] = jsonObject.getString("DisAmount");
                    Gst[i] = jsonObject.getString("GST");
                    SGSTPERItem[i] = jsonObject.getString("SGSTPer");
                    SGSTAMTItem[i]= jsonObject.getString("SGSTAmount");
                    CGSTPERItem[i] = jsonObject.getString("CGSTPer");
                    CGSTAMTItem[i]= jsonObject.getString("CGSTAmount");
                    IGSTPERItem[i] = jsonObject.getString("IGSTper");
                    IGSTAMTItem[i]= jsonObject.getString("IGSTAmount");
                    SalePrice[i] = jsonObject.getString("SalePrice");
                    SaleDesPer[i] = jsonObject.getString("SaleDisper");
                    SubPriceItem[i] = jsonObject.getString("SubTotal");
                    lable1[i] = jsonObject.getString("Opt1");
                    lable2[i] = jsonObject.getString("Opt2");
                    lable5[i] = jsonObject.getString("Opt3");
                    lable6[i] = jsonObject.getString("Opt4");
                    lable7[i] = jsonObject.getString("Opt5");
                    lable8[i] = jsonObject.getString("Opt6");



                    TotalPaidAmount=TotalPaidAmount+ Float.parseFloat(SubPriceItem[i]);
                    TotalSGSTAMTItem=TotalSGSTAMTItem+ Float.parseFloat(SGSTAMTItem[i]);
                    TotalIGSTAMTItem=TotalIGSTAMTItem+ Float.parseFloat(IGSTAMTItem[i]);
                    TotalCGSTAMTItem=TotalCGSTAMTItem+ Float.parseFloat(CGSTAMTItem[i]);
                    TotalDiscountItem=TotalDiscountItem+ Float.parseFloat(Discount[i]);



                    Log.e("lable1", "lable1" + lable1[i]);
                    Log.e("SalePrice", "SalePrice" + SalePrice[i]);
                    Log.e("SaleDesPer", "SaleDesPer" + SaleDesPer[i]);



                    addProductPojo = new AddProductPojo();
                    addProductPojo.setID(ID[i]);
                    addProductPojo.setPID(PID[i]);
                    addProductPojo.setName(Name[i]);
                    addProductPojo.setItemCode(ItemCode[i]);
                    addProductPojo.setHSNSACCode(HSNSACCode[i]);
                    addProductPojo.setLable1(lable1[i]);
                    addProductPojo.setLable2(lable2[i]);
                    addProductPojo.setLable3(lable3[i]);
                    addProductPojo.setLable4(lable4[i]);
                    addProductPojo.setLable5(lable5[i]);
                    addProductPojo.setLable6(lable6[i]);
                    addProductPojo.setLable7(lable7[i]);
                    addProductPojo.setLable8(lable8[i]);
                    addProductPojo.setPurchasePrice(PurchasePriceitem[i]);
                    addProductPojo.setPer(per[i]);
                    addProductPojo.setSalePrice(SalePrice[i]);
                    addProductPojo.setSaleDesPer(SaleDesPer[i]);
                    addProductPojo.setQuantity(Quantity[i]);
                    addProductPojo.setGst(Gst[i]);
                    addProductPojo.setSGSTPERItem(SGSTPERItem[i]);
                    addProductPojo.setSGSTAMTItem(SGSTAMTItem[i]);
                    addProductPojo.setCGSTPERItem(CGSTPERItem[i]);
                    addProductPojo.setCGSTAMTItem(CGSTAMTItem[i]);
                    addProductPojo.setIGSTPERItem(IGSTPERItem[i]);
                    addProductPojo.setIGSTAMTItem(IGSTAMTItem[i]);
                    addProductPojo.setSubPriceItem(SubPriceItem[i]);





                    items.add(addProductPojo);
                    CompanyAdapte = new AddProductAdapter(items,getApplicationContext());
                    lview.setAdapter(CompanyAdapte);
                    CompanyAdapte.notifyDataSetChanged();

                }
                totalsumsub=TotalPaidAmount+TotalDiscountItem;
                idEditTextTotalSub.setText(""+totalsumsub);
                idEditTextDiscount.setText(""+TotalDiscountItem);
                idEditTextTotalAmount.setText(""+TotalPaidAmount);


            } catch (JSONException e) {
                e.printStackTrace();
            }




        }

        @Override
        protected String doInBackground(String... arg) {

            String URL = Config.Url+"PurchaseDetails.asmx";

            //for linear parameter
            SoapObject request = new SoapObject(NAMESPACE, "GetProduct_details");
            request.addProperty("PId", stridEditTextIDPurchaseEntry); // adding method property here serially

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.debug = true;

            try {
                httpTransport.call("http://tempuri.org/GetProduct_details", envelope);

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
                //  Log.e("SOAPLOG", e.getMessage());
                e.printStackTrace();
            }


            return String.valueOf(result);
        }



    }

    @Override
    public void onResume() {
        super.onResume();
        new myProductAsyncTask().execute();
    }


    public void alertMessage(String SNO) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
        { public void onClick(DialogInterface dialog, int which)
        { switch (which)
        { case DialogInterface.BUTTON_POSITIVE: // Yes button clicked
            Toast.makeText(UpdatePurchaseEntryActivity.this, "Yes Clicked", Toast.LENGTH_LONG).show();
            new DeleteVenderDetailAsyncTask().execute();

            break;
            case DialogInterface.BUTTON_NEGATIVE: // No button clicked //
                // do nothing
                Toast.makeText(UpdatePurchaseEntryActivity.this, "No Clicked", Toast.LENGTH_LONG).show();
                break;
        }
        }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePurchaseEntryActivity.this);
        builder.setMessage("Are you sure to delete record ?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }


    public class DeleteVenderDetailAsyncTask extends AsyncTask<Void, Void, Void>
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
            new myProductAsyncTask().execute();


        }

        @Override
        protected Void doInBackground(Void... params) {

            String url =Config.Url+"PurchaseDetails.asmx";
            String soapactionaddlob ="http://tempuri.org/Delete_Item_grid";
            String methodname="Delete_Item_grid";
            String namespacesaalob="http://tempuri.org/";
            try {

                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);


                int lobid= Integer.parseInt(sno);



//            PropertyInfo LobIdp=new PropertyInfo();
//            LobIdp.setName("LobId");
//            LobIdp.setValue(lobint);
//
//            requestAddlob.addProperty(LobIdp);
//

                requestAddlob.addProperty("Id", sno);



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

    public class ClearBlankDataAsyncTask extends AsyncTask<Void, Void, Void>
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
            finish();


        }

        @Override
        protected Void doInBackground(Void... params) {

            String url =Config.Url+"PurchaseDetails.asmx";
            String soapactionaddlob ="http://tempuri.org/Delete_BlankData";
            String methodname="Delete_BlankData";
            String namespacesaalob="http://tempuri.org/";
            try {

                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"
                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);


//                int lobid= Integer.parseInt(sno);



//            PropertyInfo LobIdp=new PropertyInfo();
//            LobIdp.setName("LobId");
//            LobIdp.setValue(lobint);
//
//            requestAddlob.addProperty(LobIdp);
//

//                requestAddlob.addProperty("Id", sno);



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

   /* public void onBackPressed()
    {
      //  new ClearBlankDataAsyncTask().execute();
    }*/


}