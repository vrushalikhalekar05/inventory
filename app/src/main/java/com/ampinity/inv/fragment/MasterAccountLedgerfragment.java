package com.ampinity.inv.fragment;
import androidx.fragment.app.Fragment;
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
import android.widget.Button;
import android.widget.Toast;

import com.ampinity.inv.Activity.AddAccountLedgerActivity;
import com.ampinity.inv.Model.AttributeDetailPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.adapter.AttributeDetailAdapter;
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

/**
 * Created by peritis1 on 2/3/2018.
 */


public class MasterAccountLedgerfragment extends Fragment {
    Button idButtonAddAccountLedger;


    public MasterAccountLedgerfragment() {
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
        return inflater.inflate(R.layout.fragment_master_account_ledger, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idButtonAddAccountLedger=(Button)view.findViewById(R.id.idButtonAddAccountLedger);

        idButtonAddAccountLedger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),AddAccountLedgerActivity.class));

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

//    public  void populateList() {
//
//        new myLobAsyncTask().execute();
//    }
//
//    public class myLobAsyncTask extends AsyncTask<String, Integer, String> {
//
//
//        @Override
//        protected void onPostExecute(String  result) {
//            super.onPostExecute(result);
//            items.clear();
//
//            try {
//
//
//                JSONArray jsonArray = new JSONArray(String.valueOf(result));
//
//
//                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
//                length = jsonArray.length();
//                Log.e("length", "length" + length);
//                Tableid= new String[length];
//                LobName= new String[length];
//                NameAttribute= new String[length];
//                Name= new String[length];
//                ShortName= new String[length];
//
//
//                for (int i = 0; i < length; i++) {
//
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                    Tableid[i] = jsonObject.getString("Tableid");
//                    LobName[i] = jsonObject.getString("LobName");
//                    NameAttribute[i] = jsonObject.getString("NameAttribute");
//                    Name[i] = jsonObject.getString("Name");
//                    ShortName[i] = jsonObject.getString("ShortName");
//
//
//
//
//                    Log.e("Name", "Name" + Name[i]);
//
//
//                    attributeDetailPojo = new AttributeDetailPojo();
//                    attributeDetailPojo.setTableid(Tableid[i]);
//                    attributeDetailPojo.setLobName( LobName[i]);
//                    attributeDetailPojo.setNameAttribute(NameAttribute[i]);
//                    attributeDetailPojo.setName(Name[i]);
//                    attributeDetailPojo.setShortName(ShortName[i]);
//
//                    items.add(attributeDetailPojo);
//                    CompanyAdapte = new AttributeDetailAdapter(items,getActivity());
//                    lview.setAdapter(CompanyAdapte);
//
//
//
//                }
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//
//
//        }
//
//        @Override
//        protected String doInBackground(String... arg) {
//
//            String URL = "http://ampinventory.ampinityit.com/lob.asmx";
//
//            //for linear parameter
//            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
//            // request.addProperty("Celsius", "48"); // adding method property here serially
//
//            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//            envelope.implicitTypes = true;
//            envelope.setOutputSoapObject(request);
//            envelope.dotNet = true;
//
//            HttpTransportSE httpTransport = new HttpTransportSE(URL);
//            httpTransport.debug = true;
//
//            try {
//                httpTransport.call(SOAP_ACTION, envelope);
//
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                Log.e("IOLOG", e.getMessage());
//                e.printStackTrace();
//            } catch (XmlPullParserException e) {
//                // TODO Auto-generated catch block
//                Log.e("XMLLOG", e.getMessage());
//                e.printStackTrace();
//            } //send request
//
//            Object  result = null;
//            try {
//                result = (Object)envelope.getResponse();
//                Log.i("RESPONSE",String.valueOf(result));
//
//
//
//            } catch (SoapFault e) {
//                // TODO Auto-generated catch block
//                Log.e("SOAPLOG", e.getMessage());
//                e.printStackTrace();
//            }
//
//
//            return String.valueOf(result);
//        }
//
//
//
//    }
//
//    public void alertMessage(String SNO) {
//        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
//        { public void onClick(DialogInterface dialog, int which)
//        { switch (which)
//        { case DialogInterface.BUTTON_POSITIVE: // Yes button clicked
//            Toast.makeText(getActivity(), "Yes Clicked", Toast.LENGTH_LONG).show();
//            new DeleteLobAsyncTask().execute();
//
//            break;
//            case DialogInterface.BUTTON_NEGATIVE: // No button clicked //
//                // do nothing
//                Toast.makeText(getActivity(), "No Clicked", Toast.LENGTH_LONG).show();
//                break;
//        }
//        }
//        };
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setMessage("Are you sure to delete record ?")
//                .setPositiveButton("Yes", dialogClickListener)
//                .setNegativeButton("No", dialogClickListener).show();
//    }
//
//
//    public class DeleteLobAsyncTask extends AsyncTask<Void, Void, Void>
//    {
//
//        ProgressDialog dialog;
//
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//
//
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//
//            Toast.makeText(getActivity(),"Deleted Succesfully",Toast.LENGTH_SHORT).show();
//            new myLobAsyncTask().execute();
//
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            String url ="http://ampinventory.ampinityit.com/lob.asmx";
//            String soapactionaddlob ="http://tempuri.org/Delete_New";
//            String methodname="Delete_New";
//            String namespacesaalob="http://tempuri.org/";
//            try {
//
//                //for linear parameterPOST /lob.asmx HTTP/1.1
////                Host: ampinventory.ampinityit.com
////                Content-Type: text/xml; charset=utf-8
////                Content-Length: length
////                SOAPAction: "http://tempuri.org/Insert_LobDetails"
//                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
//
//
//                int lobid= Integer.parseInt(sno);
//
//
//
////            PropertyInfo LobIdp=new PropertyInfo();
////            LobIdp.setName("LobId");
////            LobIdp.setValue(lobint);
////
////            requestAddlob.addProperty(LobIdp);
////
//
//                requestAddlob.addProperty("LobId", sno);
//
//
//
//                SoapSerializationEnvelope envelopeAddlob = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//
//
//                envelopeAddlob.encodingStyle = "utf-8";
//                envelopeAddlob.implicitTypes = true;
//                envelopeAddlob.setOutputSoapObject(requestAddlob);
//                envelopeAddlob.dotNet = true;
//                Object result1=null;
//
//                try {
//                    HttpTransportSE httpTransportAddlob = new HttpTransportSE(url);
//                    httpTransportAddlob.debug = true;
//                    httpTransportAddlob.call(soapactionaddlob, envelopeAddlob);
//                    result1 = (Object) envelopeAddlob.getResponse();
//                    Log.i("RESPONSE after delete", String.valueOf(result1));
//
//                } catch (Exception e) {
//
//                    //   Log.e("IOLOG", e.getMessage());
//                    e.printStackTrace();
////                dialog.dismiss();
//                }
//
//
//
//            } catch (Exception e) {
//
//                //   Log.e("IOLOG", e.getMessage());
//                e.printStackTrace();
////                dialog.dismiss();
//            }
//
//
//
//
//            return null;
//        }
//
//
//
//    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        new myLobAsyncTask().execute();
//    }
}



