package com.ampinity.inv.fragment;

//import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.EditText;
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
import android.text.TextWatcher;
import android.text.Editable;

import androidx.fragment.app.Fragment;

import com.ampinity.inv.Activity.AddSalesCustomerActivity;
import com.ampinity.inv.Activity.AddSalesReturnActivity;
import com.ampinity.inv.Model.GstPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.adapter.CustomizedSpinnerAdapter;
import com.ampinity.inv.adapter.GstAdapter;
import com.ampinity.inv.adapter.GstAdapterWithFilter;

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





public class ReportGstfragment extends Fragment {
    Button idButtonAddSalesReturn;
    String [] CompanyName={
            "Select SearchBy",
            "Name",
            "ID",
            "Rate"};
    public static  String  selcetedspinnerSearchBy;
Spinner spinnerSearchBy;
    TextView idTextViewName;
    Button idButtonAddgst;
EditText idEditTextRate;

    ListView lview;
    ArrayList<GstPojo> items = new ArrayList<>();
    GstPojo gstPojo;
    static GstAdapterWithFilter CompanyAdapte;
    String [] Tableid,GstName,Rate;
    int length;
    private static final String SOAP_ACTION = "http://tempuri.org/GetDataLObDetails";
    private static final String METHOD_NAME = "GetDataLObDetails";
    private static final String NAMESPACE = "http://tempuri.org/";
    String sno;

    public ReportGstfragment() {
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
        return inflater.inflate(R.layout.fragment_report_gst, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idButtonAddSalesReturn=(Button)view.findViewById(R.id.idButtonAddSalesReturn);
        spinnerSearchBy = (Spinner)view. findViewById(R.id.spinnerSearchBy);
        idTextViewName=(TextView)view.findViewById(R.id.idTextViewName) ;

        lview = (ListView) view.findViewById(R.id.listview);
        idEditTextRate=(EditText)view.findViewById(R.id.idEditTextRate);
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
//

                    idEditTextRate.setEnabled(false);
                }
                else
                {
                    idTextViewName.setText(""+selcetedspinnerSearchBy);
                    idEditTextRate.setEnabled(true);
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

               // startActivity(new Intent(getActivity(),AddSalesCustomerActivity.class));

            }
        });

        idEditTextRate.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                //CompanyAdapte.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
        //populateList();
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

        new myTaxGroupAsyncTask().execute();
    }

    public class myTaxGroupAsyncTask extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPostExecute(String  result) {
            super.onPostExecute(result);
            items.clear();

            try {


                JSONArray jsonArray = new JSONArray(String.valueOf(result));


                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                length = jsonArray.length();
                Log.e("length", "length" + length);
                Tableid= new String[length];
                GstName= new String[length];
                Rate= new String[length];



                for (int i = 0; i < length; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Tableid[i] = jsonObject.getString("Id");
                    GstName[i] = jsonObject.getString("TaxName");
                    Rate[i] = jsonObject.getString("Rate");


                    Log.e("GstName", "GstName" + GstName[i]);


                    gstPojo = new GstPojo();
                    gstPojo.setGstid(Tableid[i]);
                    gstPojo.setGst( GstName[i]);
                    gstPojo.setRate(Rate[i]);


                    items.add(gstPojo);
                    CompanyAdapte = new GstAdapterWithFilter(items,getActivity());
                    lview.setAdapter(CompanyAdapte);



                }


            } catch (JSONException e) {
                e.printStackTrace();
            }




        }

        @Override
        protected String doInBackground(String... arg) {

            String URL = "http://ampinventory.ampinityit.com/TaxGroup.asmx";

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
       // new myTaxGroupAsyncTask().execute();
    }
}




