package com.ampinity.inv.fragment;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.app.Fragment;
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

import com.ampinity.inv.Activity.EmployeeRegistration;
import com.ampinity.inv.Model.BankPojo;
import com.ampinity.inv.Model.EmployeeRegiPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.adapter.BankAdapter;
import com.ampinity.inv.adapter.EmployeeRegiAdapter;

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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MasterEmployeeRegifragment.OnFragmentInteractionListener} interface
 * to handle interaction events.

 *
 */
 /*Use the {@link MasterEmployeeRegifragment#newInstance} factory method to create an instance of this fragment.*/
public class MasterEmployeeRegifragment extends Fragment {
    Button idButtonAddemployee;
    ListView lview;
    ArrayList<EmployeeRegiPojo> items= new ArrayList<>();

    EmployeeRegiPojo empRegPojo;
    static EmployeeRegiAdapter CompanyAdapte;
    String [] empId,empName,empAddress,empState,empCity,empPhone,empEmail,empSalary,empTarget;
    int length;
    private static final String SOAP_ACTION = "http://tempuri.org/GetDataDetails";
    private static final String METHOD_NAME = "GetDataDetails";
    private static final String NAMESPACE = "http://tempuri.org/";
    String sno;
    int lobid;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;*/

   /* private OnFragmentInteractionListener mListener;*/

    public MasterEmployeeRegifragment() {
        // Required empty public constructor
    }
    /*@param param1 Parameter 1.
            * @param param2 Parameter 2.*/
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment MasterEmployeeRegifragment.
     */
    // TODO: Rename and change types and number of parameters
   /* public static MasterEmployeeRegifragment newInstance(String param1, String param2) {
        //MasterEmployeeRegifragment fragment = new MasterEmployeeRegifragment();
       *//* Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*//*
        //return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    public void onViewCreated(View view,Bundle saveInstanceState){
        super.onViewCreated(view,saveInstanceState);
        lview = (ListView) view.findViewById(R.id.listview);
        //new myEmpRegiAsyncTask().execute();
        idButtonAddemployee=(Button) view.findViewById(R.id.idButtonAddemployee);
        idButtonAddemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), EmployeeRegistration.class);
                startActivity(i);
            }
        });
        populateList();

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                sno = ((TextView)view.findViewById(R.id.empid)).getText().toString();

                ((ImageView)view.findViewById(R.id.idimageviewdeletered)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertMessage(sno);
                    }
                });
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_master_employee_regifragment, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        /*if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must  OnFragmentInteractionListener");
        }
    }*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onDetach() {
        super.onDetach();
       /* mListener = null;*/
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
       /* void onFragmentInteraction(Uri uri);*/
    }
    public void onResume() {
        super.onResume();
        new myEmpRegiAsyncTask().execute();
    }
    public  void populateList() {

        new myEmpRegiAsyncTask().execute();
    }
    public class myEmpRegiAsyncTask extends AsyncTask<String, Integer, String>{
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String  result) {
            super.onPostExecute(result);
            items.clear();
            try {
                JSONArray jsonArray = new JSONArray(String.valueOf(result));
                System.out.println("jsonArray in myEmpRegiAsyncTask "+jsonArray);
                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                length = jsonArray.length();
                Log.e("length", "length" + length);
                empId= new String[length];
                empName= new String[length];
                empAddress= new String[length];
                empState= new String[length];
                empCity= new String[length];
                empPhone= new String[length];
                empEmail= new String[length];
                empSalary= new String[length];
                empTarget= new String[length];
                for (int i = 0; i < length; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    empId[i] = jsonObject.getString("id");
                    empName[i] = jsonObject.getString("EmployeeName");
                    empAddress[i] = jsonObject.getString("Address");
                    empState[i] = jsonObject.getString("State");
                    empCity[i] = jsonObject.getString("City");

                    empPhone[i] = jsonObject.getString("PhoneNo");
                    empEmail[i] = jsonObject.getString("Email");
                    empSalary[i] = jsonObject.getString("Salary");
                    empTarget[i] = jsonObject.getString("BasicTarget");
                   // Log.e("Bankname", "Bankname" + Bankname[i]);

                    empRegPojo = new EmployeeRegiPojo();
                    empRegPojo.setempId(empId[i]);
                    empRegPojo.setempName( empName[i]);
                    empRegPojo.setempAddr(empAddress[i]);
                    empRegPojo.setempState(empState[i]);
                    empRegPojo.setempCity(empCity[i]);
                    empRegPojo.setempPhone(empPhone[i]);
                    empRegPojo.setempEmail(empEmail[i]);
                    empRegPojo.setempSalary(empSalary[i]);
                    empRegPojo.setempTarget(empTarget[i]);


                    items.add(empRegPojo);
                    CompanyAdapte = new EmployeeRegiAdapter(items,getActivity());
                    lview.setAdapter(CompanyAdapte);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        protected String doInBackground(String... strings) {
            String URL = Config.Url+"empregi.asmx";
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
        { public void onClick(DialogInterface dialog, int which)
        { switch (which)
        { case DialogInterface.BUTTON_POSITIVE: // Yes button clicked
            Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_LONG).show();
            new DeleteLobAsyncTask().execute();
            break;
            case DialogInterface.BUTTON_NEGATIVE: // No button clicked //
                // do nothing
                Toast.makeText(getActivity(), "Cannot be deleted", Toast.LENGTH_LONG).show();
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

            new myEmpRegiAsyncTask().execute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            String url =Config.Url+"empregi.asmx";
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

                lobid= Integer.parseInt(sno);

                //            PropertyInfo LobIdp=new PropertyInfo();
//            LobIdp.setName("LobId");
//            LobIdp.setValue(lobint);
//
//            requestAddlob.addProperty(LobIdp);
//
                requestAddlob.addProperty("ID", lobid);
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
