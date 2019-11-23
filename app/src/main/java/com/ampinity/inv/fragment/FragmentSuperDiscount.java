package com.ampinity.inv.fragment;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
/*import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;*/
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.ampinity.inv.Activity.AddActivitySuperDiscount;
import com.ampinity.inv.Model.SuperDiscount;
import com.ampinity.inv.Model.TableDataPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.adapter.AdapterSuperDiscount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


 /* A simple {@link Fragment} subclass.*/

public class FragmentSuperDiscount extends Fragment {

Button buttonAddOffer;


    String sno;
    String Name1;
    int length;

    static AdapterSuperDiscount CompanyAdapte;
    private static final String SOAP_ACTION = "http://tempuri.org/GetData";
    private static final String METHOD_NAME = "GetData";
    private static final String NAMESPACE = "http://tempuri.org/";
    private ArrayList<SuperDiscount> productList;


    String[] Id;
    String[] Name;
    String[] DiscountPercentage;
    String[] TotalAttribute;

    AdapterSuperDiscount adapterSuperDiscount;
    ArrayList<SuperDiscount> superDiscountArrayList = new ArrayList<>();
    ListView lview;
    TableDataPojo tableDataPojo;
    int MY_PERMISSIONS_REQUEST_READ_CONTACTS;

    public FragmentSuperDiscount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_super_discount, container, false);
        buttonAddOffer= (Button) view.findViewById(R.id.ButtonAddOffer);
        productList = new ArrayList<SuperDiscount>();
        lview = (ListView) view.findViewById(R.id.listview);
        adapterSuperDiscount=new AdapterSuperDiscount(getActivity(),productList);
        lview.setAdapter(adapterSuperDiscount);

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        } else {
            // Permission has already been granted
        }

        buttonAddOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AddActivitySuperDiscount.class);
                startActivity(intent);
            }
        });
        populateList();
        adapterSuperDiscount.notifyDataSetChanged();
        return view;
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
        new myLobAsyncTask().execute();
    }
    public class myLobAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPostExecute(String  result) {
            super.onPostExecute(result);
            superDiscountArrayList.clear();
            try {
                JSONArray jsonArray = new JSONArray(String.valueOf(result));
                Log.i("RESPONSE jsonArray",String.valueOf(jsonArray));
                length = jsonArray.length();
                Log.e("length", "length" + length);
                Id= new String[length];
                Name= new String[length];
                DiscountPercentage=new String[length];
                TotalAttribute= new String[length];
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Id[i] = jsonObject.getString("id");
                    Name[i] = jsonObject.getString("SchemeName");
                  DiscountPercentage[i]=jsonObject.getString("Disper");
                    //  TotalAttribute[i] = jsonObject.getString("TotalAttribute");
                    Log.e("Name", "Name" + Name[i]);
                    Log.e("LobId", "LobId" + Id[i]);
                   Log.e("DiscountPercentage","DiscountPercentage" + DiscountPercentage[i]);
                    //   Log.e("TotalAttribute", "TotalAttribute" + TotalAttribute[i]);
                    // tableDataPojo = new TableDataPojo();
                    SuperDiscount superDiscount=new SuperDiscount();
                    superDiscount.setId(Id[i]);
                    superDiscount.setName(Name[i]);
                    superDiscount.setDiscountPercentage(DiscountPercentage[i]);
                    //  tableDataPojo.setTotalAttribute(TotalAttribute[i]);
                    superDiscountArrayList.add(superDiscount);
                    CompanyAdapte = new AdapterSuperDiscount(getActivity(),superDiscountArrayList);
                    lview.setAdapter(CompanyAdapte);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        @Override
              protected String doInBackground(String... arg) {
              String URL = Config.Url+"SuperDiscount.asmx";
              SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
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
//                Log.e("IOLOG", e.getMessage());
               // e.printStackTrace();
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                Log.e("XMLLOG", e.getMessage());
                e.printStackTrace();
            }
           // SoapPrimitive resultRequestSOAP = null;
           Object  result = null;
            try {
                result =  envelope.getResponse();
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
        new myLobAsyncTask().execute();
    }
    public void alertMessage(String SNO)
    {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                switch (which)
                {
                        case DialogInterface.BUTTON_POSITIVE: // Yes button clicked
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
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            Toast.makeText(getActivity(),"Deleted Succesfully",Toast.LENGTH_SHORT).show();
            new myLobAsyncTask().execute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            String url =Config.Url+"SuperDiscount.asmx";
            String soapactionaddlob = "http://tempuri.org/Delete";
            String methodname= "Delete";
            String namespacesaalob= "http://tempuri.org/";
            try {
                //for linear parameterPOST /lob.asmx HTTP/1.1
//                Host: ampinventory.ampinityit.com
//                Content-Type: text/xml; charset=utf-8
//                Content-Length: length
//                SOAPAction: "http://tempuri.org/Insert_LobDetails"

                SoapObject requestAddlob = new SoapObject(namespacesaalob, methodname);
                int lobid= Integer.parseInt(sno);

                //      PropertyInfo LobIdp=new PropertyInfo();
//            LobIdp.setName("LobId");
//            LobIdp.setValue(lobint);
//
//           requestAddlob.addProperty(LobIdp);
//
                requestAddlob.addProperty("Id", lobid);
                requestAddlob.addProperty("ComapnyID","1");
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
                }catch (Exception e) {
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

    }
    public class myExportLobAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPostExecute(String  result) {
            super.onPostExecute(result);
            superDiscountArrayList.clear();
            JSONObject rec = null;
            try {
                Workbook wb = new HSSFWorkbook();
                Cell c = null;
                //Cell style for header row
                CellStyle cs = wb.createCellStyle();
                cs.setFillForegroundColor(HSSFColor.LIME.index);
                cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                //New Sheet
                Sheet sheet1 = null;
                sheet1 = wb.createSheet("myLOB");
                Row row = sheet1.createRow(0);

                c = row.createCell(0);
                c.setCellValue("LobId");
                c.setCellStyle(cs);
                c = row.createCell(1);

                c.setCellValue("Name");
                c.setCellStyle(cs);
                c = row.createCell(2);
                c.setCellValue("TotalAttribute");
                c.setCellStyle(cs);

                sheet1.setColumnWidth(0, (15 * 500));
                sheet1.setColumnWidth(1, (15 * 500));
                sheet1.setColumnWidth(2, (15 * 500));

                JSONArray jsonArray = new JSONArray(String.valueOf(result));
                Log.i("RESPONSEjsonArray", String.valueOf(jsonArray));
                length = jsonArray.length();
                Log.e("length", "length" + length);
                Id = new String[length];
                Name = new String[length];
                TotalAttribute = new String[length];
                int rowCount = 0;
                //                XSSFWorkbook workbook = new XSSFWorkbook();
//                XSSFSheet sheet = workbook.getSheetAt(0);
                //XSSFWorkbook workbook = new XSSFWorkbook();
                // XSSFSheet sheet = workbook.createSheet("Report");
//                Workbook workbook = new XSSFWorkbook();

//
//               Sheet sheet = workbook.createSheet("Report");

                for (int i = 0; i < length; i++) {
                    row=null;
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Id[i] = jsonObject.getString("LobId");
                    Name[i] = jsonObject.getString("Name");
                    TotalAttribute[i] = jsonObject.getString("TotalAttribute");
                    Log.e("Name", "Name" + Name[i]);
                    Log.e("LobId", "LobId" + Id[i]);
                    Log.e("TotalAttribute", "TotalAttribute" + TotalAttribute[i]);
                    row = sheet1.createRow(i+1);

                    c = row.createCell(0);
                    c.setCellValue(Id[i]);
                    c.setCellStyle(cs);

                    c = row.createCell(1);
                    c.setCellValue(Name[i]);
                    c.setCellStyle(cs);

                    c = row.createCell(2);
                    c.setCellValue(TotalAttribute[i]);
                    c.setCellStyle(cs);

                    sheet1.setColumnWidth(0, (15 * 500));
                    sheet1.setColumnWidth(1, (15 * 500));
                    sheet1.setColumnWidth(2, (15 * 500));
                    }

                File file = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "myLOBExcel.xls");
                FileOutputStream os = null;
                try {
                    os = new FileOutputStream(file);
                    wb.write(os);
                    Log.w("FileUtils", "Writing file" + file);
                    // success = true;
                } catch (IOException e) {
                    Log.w("FileUtils", "Error writing " + file, e);
                } catch (Exception e) {
                    Log.w("FileUtils", "Failed to save file", e);
                } finally {
                    try {
                        if (null != os)
                            os.close();
                    } catch (Exception ex) {
                    }
                }
            }
            catch (JSONException e) {
            }
            }
            @Override
        protected String doInBackground(String... arg) {
            String URL = Config.Url+ "SuperDiscount.asmx";
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
