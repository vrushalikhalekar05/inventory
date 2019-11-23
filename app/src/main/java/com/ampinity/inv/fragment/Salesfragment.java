package com.ampinity.inv.fragment;

import android.app.AlertDialog;
//import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
/*
import android.support.v7.app.AlertDialog;
*/
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ampinity.inv.Activity.AddSalesActivity;
import com.ampinity.inv.Model.Model;
import com.ampinity.inv.Model.PurchaseEntryPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.adapter.PurchaseEntryAdapter;

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

/**
 * Created by peritis1 on 2/2/2018.
 */












public class Salesfragment extends Fragment {
    Button idButtonAddSales;
    String [] NO,ProductName,Rate,Qty,Discount,CGST,SGST,IGST;



    int length;
    static PurchaseEntryAdapter CompanyAdapte;
    private static final String SOAP_ACTION = "http://tempuri.org/GetDataDetails";
    private static final String METHOD_NAME = "GetDataDetails";
    private static final String NAMESPACE = "http://tempuri.org/";
    private ArrayList<Model> productList;

    String[] vendername,PurchaseEntryName,SupplierBillNo,Date,IDPurchaseEntry,InWord,PaidAmount,RemainingAmount,TotalSub,TotalAmount,DatePaid,PaymentBy,SGSTAmount,CGSTAmount,IGSTAmount;
    ArrayList<PurchaseEntryPojo> items = new ArrayList<>();
    ListView lview;
    PurchaseEntryPojo purchaseEntryPojo;
    String sno;
    public Salesfragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sales, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idButtonAddSales=(Button)view.findViewById(R.id.idButtonAddSales);

        idButtonAddSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),AddSalesActivity.class));

            }
        });

        lview = (ListView) view.findViewById(R.id.listview);
        //listviewAdapter adapter = new listviewAdapter(getActivity(), productList);
        // lview.setAdapter(adapter);

        populateList();

        //  adapter.notifyDataSetChanged();

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

               /* sno = ((TextView)view.findViewById(R.id.SNO)).getText().toString();
//                String VGroup = ((TextView)view.findViewById(R.id.VenderGroup)).getText().toString();
//                String Desc = ((TextView)view.findViewById(R.id.Description)).getText().toString();
                ((ImageView)view.findViewById(R.id.idimageviewdeletered)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertMessage(sno);

                    }
                });*/



            }
        });

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public  void populateList() {

        new mySalesAsyncTask().execute();
    }

    public class mySalesAsyncTask extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPostExecute(String  result) {
            super.onPostExecute(result);
            items.clear();

            try {


                JSONArray jsonArray = new JSONArray(String.valueOf(result));


                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                length = jsonArray.length();
                Log.e("length", "length" + length);


                vendername= new String[length];

                PurchaseEntryName= new String[length];
                SupplierBillNo= new String[length];
                Date= new String[length];
                IDPurchaseEntry= new String[length];
                InWord= new String[length];
                PaidAmount= new String[length];
                RemainingAmount= new String[length];
                TotalSub= new String[length];
                Discount= new String[length];
                TotalAmount= new String[length];
                DatePaid= new String[length];
                PaymentBy= new String[length];
                SGSTAmount= new String[length];
                CGSTAmount= new String[length];
                IGSTAmount= new String[length];




                for (int i = 0; i < length; i++) {
 /*"Id": 1,
    "SupplierName": "string",
    "SupplierBillDate": "string",
    "SupplierBillNo": "string",
    "Inwords": "string",
    "PaymentBy": "string",
    "PaidAmount": "string",
    "RemainingAmount": "string",
    "Paid": "string",
    "UnPaid": "string",
    "SubTotal": "string",
    "Discount": "string",
    "SGSTAmount": "string",
    "CGSTAmount": "string",
    "IGSTAmount": "string",
    "TotalAmount": "string"*/
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    IDPurchaseEntry[i] = jsonObject.getString("Id");
                    vendername[i] = jsonObject.getString("SupplierName");
//                    PurchaseEntryName[i] = jsonObject.getString("SupplierName");
                    SupplierBillNo[i] = jsonObject.getString("SupplierBillNo");
                    Date[i] = jsonObject.getString("SupplierBillDate");
                    InWord[i] = jsonObject.getString("Inwords");
                    PaymentBy[i]= jsonObject.getString("PaymentBy");
                    PaidAmount[i] = jsonObject.getString("PaidAmount");
                    RemainingAmount[i] = jsonObject.getString("RemainingAmount");
                    TotalSub[i] = jsonObject.getString("SubTotal");
                    Discount[i] = jsonObject.getString("Discount");
                    TotalAmount[i] = jsonObject.getString("TotalAmount");
//                    DatePaid[i] = jsonObject.getString("DatePaid");
                    SGSTAmount[i]= jsonObject.getString("SGSTAmount");
                    CGSTAmount[i]= jsonObject.getString("CGSTAmount");
                    IGSTAmount[i]= jsonObject.getString("IGSTAmount");




                    Log.e("vendername", "vendername" + vendername[i]);
                    Log.e("TotalSub", "TotalSub" + TotalSub[i]);
                    Log.e("DatePaid", "DatePaid" + DatePaid[i]);

                    purchaseEntryPojo = new PurchaseEntryPojo();
                    purchaseEntryPojo.setIDPurchaseEntry(IDPurchaseEntry[i]);
                    purchaseEntryPojo.setVendername(vendername[i]);
                    purchaseEntryPojo.setSupplierBillNo(SupplierBillNo[i]);
                    purchaseEntryPojo.setDate(Date[i]);
                    purchaseEntryPojo.setInWord(InWord[i]);
                    purchaseEntryPojo.setPaymentBy(PaymentBy[i]);
                    purchaseEntryPojo.setPaidAmount(PaidAmount[i]);
                    purchaseEntryPojo.setRemainingAmount(RemainingAmount[i]);
                    purchaseEntryPojo.setTotalSub(TotalSub[i]);
                    purchaseEntryPojo.setDiscount(Discount[i]);
                    purchaseEntryPojo.setTotalAmount(TotalAmount[i]);
                    purchaseEntryPojo.setDatePaid("");
                    purchaseEntryPojo.setSGSTAmount(SGSTAmount[i]);
                    purchaseEntryPojo.setCGSTAmount(CGSTAmount[i]);
                    purchaseEntryPojo.setIGSTAmount(IGSTAmount[i]);





                    items.add(purchaseEntryPojo);
                    CompanyAdapte = new PurchaseEntryAdapter(items,getActivity());
                    lview.setAdapter(CompanyAdapte);



                }


            } catch (JSONException e) {
                e.printStackTrace();
            }




        }

        @Override
        protected String doInBackground(String... arg) {

            String URL = Config.Url+"purchasedetails.asmx";

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

    @Override
    public void onResume() {
        super.onResume();
        new mySalesAsyncTask().execute();
    }


    public void alertMessage(String SNO) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
        { public void onClick(DialogInterface dialog, int which)
        { switch (which)
        { case DialogInterface.BUTTON_POSITIVE: // Yes button clicked
            Toast.makeText(getActivity(), "Yes Clicked", Toast.LENGTH_LONG).show();
            new DeleteVenderDetailAsyncTask().execute();

            break;
            case DialogInterface.BUTTON_NEGATIVE: // No button clicked //
                // do nothing
                Toast.makeText(getActivity(), "No Clicked", Toast.LENGTH_LONG).show();
                break;
        }
        }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

            Toast.makeText(getActivity(),"Deleted Succesfully",Toast.LENGTH_SHORT).show();
            new mySalesAsyncTask().execute();


        }

        @Override
        protected Void doInBackground(Void... params) {

            String url = Config.Url+"purchasedetails.asmx";
            String soapactionaddlob ="http://tempuri.org/Delete_New";
            String methodname="Delete_New";
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
}



