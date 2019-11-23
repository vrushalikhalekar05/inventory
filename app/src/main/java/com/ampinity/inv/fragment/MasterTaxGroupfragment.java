package com.ampinity.inv.fragment;

/**
 * Created by peritis1 on 1/30/2018.
 */
import androidx.fragment.app.Fragment;
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

import com.ampinity.inv.Activity.AddAttributeActivity;
import com.ampinity.inv.Activity.AddGstActivity;
import com.ampinity.inv.Activity.AddTaxGroupActivity;
import com.ampinity.inv.Model.AttributeDetailPojo;
import com.ampinity.inv.Model.GstPojo;
import com.ampinity.inv.Model.TaxGroupPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.adapter.AttributeDetailAdapter;
import com.ampinity.inv.adapter.GstAdapter;
import com.ampinity.inv.adapter.TaxGroupAdapter;
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


public class MasterTaxGroupfragment extends Fragment {
    Button idButtonAddTaxGroup;

    ListView lview;
    ArrayList<TaxGroupPojo> items = new ArrayList<>();
    TaxGroupPojo taxGroupPojo;
    static TaxGroupAdapter CompanyAdapte;
    String [] Tableid,GstName,Rate,CGST,IGST,SGST;
    int length;
    private static final String SOAP_ACTION = "http://tempuri.org/GetDataDetails";
    private static final String METHOD_NAME = "GetDataDetails";
    private static final String NAMESPACE = "http://tempuri.org/";
    String sno;





    public MasterTaxGroupfragment() {
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
        return inflater.inflate(R.layout.fragment_master_tax_group, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idButtonAddTaxGroup=(Button)view.findViewById(R.id.idButtonAddTaxGroup);
        lview = (ListView) view.findViewById(R.id.listview);
        idButtonAddTaxGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AddTaxGroupActivity.class));
            }
        });
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                sno = ((TextView)view.findViewById(R.id.idtaxgroup)).getText().toString();
//                String product = ((TextView)view.findViewById(R.id.product)).getText().toString();
//                String category = ((TextView)view.findViewById(R.id.category)).getText().toString();
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

        new myGSTAsyncTask().execute();
    }
    public class myGSTAsyncTask extends AsyncTask<String, Integer, String> {
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
                CGST= new String[length];
                IGST= new String[length];
                SGST= new String[length];

                for (int i = 0; i < length; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Tableid[i] = jsonObject.getString("Id");
                    GstName[i] = jsonObject.getString("TaxGroupName");
                    SGST[i] = jsonObject.getString("SGST");
                    CGST[i] = jsonObject.getString("CGST");
                    IGST[i] = jsonObject.getString("IGST");
                    Rate[i] = jsonObject.getString("GSTRate");
                    Log.e("GstName", "GstName" + GstName[i]);

                    taxGroupPojo = new TaxGroupPojo();
                    taxGroupPojo.setTaxgroupid(Tableid[i]);
                    taxGroupPojo.setTaxgroupname( GstName[i]);
                    taxGroupPojo.setRate(Rate[i]);
                    taxGroupPojo.setCgst(CGST[i]);
                    taxGroupPojo.setIgst(IGST[i]);
                    taxGroupPojo.setSgst(SGST[i]);

                    items.add(taxGroupPojo);

                    CompanyAdapte = new TaxGroupAdapter(items,getActivity());
                    lview.setAdapter(CompanyAdapte);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        protected String doInBackground(String... arg) {
            String URL = Config.Url+"gstdetails.asmx";
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
            Toast.makeText(getActivity(), "Yes Clicked", Toast.LENGTH_LONG).show();
            new DeleteLobAsyncTask().execute();
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
            Toast.makeText(getActivity(),"Deleted Succesfully",Toast.LENGTH_SHORT).show();
            new myGSTAsyncTask().execute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String url =Config.Url+"gstdetails.asmx";
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


    @Override
    public void onResume() {
        super.onResume();
        new myGSTAsyncTask().execute();
    }
}

