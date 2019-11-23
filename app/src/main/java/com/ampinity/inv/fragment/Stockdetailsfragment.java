package com.ampinity.inv.fragment;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ampinity.inv.Activity.AddSalesCustomerActivity;
import com.ampinity.inv.Activity.AddSalesReturnActivity;
import com.ampinity.inv.Model.BankPojo;
import com.ampinity.inv.Model.PurchaseEntryPojo;
import com.ampinity.inv.Model.StrockDetailsPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.adapter.BankAdapter;
import com.ampinity.inv.adapter.CustomizedSpinnerAdapter;
import com.ampinity.inv.adapter.PurchaseEntryAdapter;
import com.ampinity.inv.adapter.StockDetailsAdapter;

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





public class Stockdetailsfragment extends Fragment {
    Button idButtonAddSalesReturn;
    String [] CompanyName={
            "Select SearchBy",
            "Name",
            "ID",
            "Brand",
            "Color",
            "Size"};

    ListView lview;
    ArrayList<StrockDetailsPojo> items = new ArrayList<>();
    StrockDetailsPojo StrockDetailsPojo;
    static StockDetailsAdapter CompanyAdapte;
    String [] id,name,billdate,billno,inwords,paymentby,paidamt,remainamt,paid,unpaid,subtotal,discount,sgst,cgst,igst,totalamt;
    int length;


    private static final String SOAP_ACTION = "http://tempuri.org/GetData";
    private static final String METHOD_NAME = "GetData";
    private static final String NAMESPACE = "http://tempuri.org/";
    String selcetedspinnerSearchBy;
Spinner spinnerSearchBy;
    TextView idTextViewName;
    public Stockdetailsfragment() {
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
        return inflater.inflate(R.layout.fragment_stock_details, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idButtonAddSalesReturn=(Button)view.findViewById(R.id.idButtonAddSalesReturn);
        spinnerSearchBy = (Spinner)view. findViewById(R.id.spinnerSearchBy);
        idTextViewName=(TextView)view.findViewById(R.id.idTextViewName) ;

        lview = (ListView) view.findViewById(R.id.listview);
        new myStockdetailsAsyncTask().execute();

        final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item,
                CompanyName);
        spinnerSearchBy.setAdapter(adapter1);


        spinnerSearchBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selcetedspinnerSearchBy=spinnerSearchBy.getSelectedItem().toString();
                if(selcetedspinnerSearchBy.equals("Select SearchBy"))
                {
                    idTextViewName.setText("");
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
                    idTextViewName.setText(""+selcetedspinnerSearchBy);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        idButtonAddSalesReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new myStockdetailsAsyncTask().execute();
               // startActivity(new Intent(getActivity(),AddSalesCustomerActivity.class));

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

    public class myStockdetailsAsyncTask extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPostExecute(String  result) {
            super.onPostExecute(result);
            items.clear();
            System.out.println("Result of StockDetails"+result);
            //Toast.makeText(getActivity(), ""+result, Toast.LENGTH_SHORT).show();
           // items.clear();

            try {


                JSONArray jsonArray = new JSONArray(String.valueOf(result));
                length=jsonArray.length();

                id=new String[length];
                name = new String[length];
                billdate=new String[length];
                billno=new String[length];
                inwords=new String[length];
                paymentby=new String[length];
                paidamt=new String[length];
                remainamt=new String[length];
                paid=new String[length];
                unpaid=new String[length];
                subtotal=new String[length];
                discount=new String[length];
                sgst=new String[length];
                cgst=new String[length];
                igst=new String[length];
                totalamt=new String[length];

                for(int i=0;i<length;i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    id[i]=jsonObject.getString("ItemCode");
                    name[i]=jsonObject.getString("ItemName");
                    billdate[i]=jsonObject.getString("Column1");
                    billno[i]=jsonObject.getString("DisPer");
                    inwords[i]=jsonObject.getString("GST");
                    paymentby[i]=jsonObject.getString("Stock");


                    StrockDetailsPojo=new StrockDetailsPojo();

                    StrockDetailsPojo.setid(id[i]);
                    StrockDetailsPojo.setSuppName(name[i]);
                   StrockDetailsPojo.setSuppBillDate(billdate[i]);
                    StrockDetailsPojo.setSuppBillNo(billno[i]);
                    StrockDetailsPojo.setinwords(inwords[i]);
                    StrockDetailsPojo.setpaymentBy(paymentby[i]);


                    items.add(StrockDetailsPojo);
                    CompanyAdapte=new StockDetailsAdapter(items,getActivity());
                    lview.setAdapter(CompanyAdapte);

                }

                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));


                    /*items.add(purchaseEntryPojo);
                    CompanyAdapte = new PurchaseEntryAdapter(items,getActivity());
                    lview.setAdapter(CompanyAdapte);*/






            } catch (JSONException e) {
                e.printStackTrace();
            }




        }

        @Override
        protected String doInBackground(String... arg) {

            String URL = Config.Url+"stockdetails.asmx";

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

}




