package com.ampinity.inv.fragment;

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

import com.ampinity.inv.Activity.AddLobActivity;
import com.ampinity.inv.Model.Model;
import com.ampinity.inv.Model.TableDataPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.Config;
import com.ampinity.inv.adapter.CustomizedSpinnerAdapter;
import com.ampinity.inv.adapter.TableDataAdapter;
import com.ampinity.inv.adapter.listviewAdapter;
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
import java.util.HashMap;
import java.io.FileOutputStream;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import android.os.Environment;

import org.apache.poi.hssf.usermodel.HSSFCell;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
/*import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;*/
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MasterLobfragment extends Fragment {

    Button /*idButtonAddLob,*/idButtonExportLob;
    String sno;
    Button idButtonAddLob;
    int length;
    static TableDataAdapter CompanyAdapte;
    private static final String SOAP_ACTION = "http://tempuri.org/GetDataLObDetails";
    private static final String METHOD_NAME = "GetDataLObDetails";
    private static final String NAMESPACE = "http://tempuri.org/";
    private ArrayList<Model> productList;
    String[] LobId,Name,TotalAttribute;
    ArrayList<TableDataPojo> items = new ArrayList<>();
    ListView lview;
    TableDataPojo tableDataPojo;
    int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    public MasterLobfragment() {
        // Required empty public constructor
    }
    // ArrayList<Model> items = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_master_dom, container, false);
        }
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idButtonAddLob=(Button) view.findViewById(R.id.idButtonAddLob);
        //idButtonExportLob=(Button)view.findViewById(R.id.idButtonExportLob);
        productList = new ArrayList<Model>();
        lview = (ListView) view.findViewById(R.id.listview);
        listviewAdapter adapter = new listviewAdapter(getActivity(), productList);
        lview.setAdapter(adapter);
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
        idButtonAddLob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddLobActivity.class));
                }
        });
        /*idButtonExportLob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new myExportLobAsyncTask().execute();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                        + "/Documents/");
                intent.setDataAndType(uri, "text/csv");
                startActivity(Intent.createChooser(intent, "Open folder"));
                }
        });*/
        populateList();
        adapter.notifyDataSetChanged();
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                sno = ((TextView)view.findViewById(R.id.sNo)).getText().toString();
                String product = ((TextView)view.findViewById(R.id.product)).getText().toString();
                String category = ((TextView)view.findViewById(R.id.category)).getText().toString();
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
            System.out.println(result);
            items.clear();
            try {
                JSONArray jsonArray = new JSONArray(String.valueOf(result));
                Log.i("RESPONSEjsonArray",String.valueOf(jsonArray));
                length = jsonArray.length();
                Log.e("length", "length" + length);
                LobId= new String[length];
                Name= new String[length];

                TotalAttribute= new String[length];

                for (int i = 0; i < length; i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    LobId[i] = jsonObject.getString("LobId");
                    Name[i] = jsonObject.getString("Name");
                    TotalAttribute[i] = jsonObject.getString("TotalAttribute");
                    Log.e("Name", "Name" + Name[i]);
                    Log.e("LobId", "LobId" + LobId[i]);
                    Log.e("TotalAttribute", "TotalAttribute" + TotalAttribute[i]);
                    tableDataPojo = new TableDataPojo();
                    tableDataPojo.setTableid(LobId[i]);
                    tableDataPojo.setLobName(Name[i]);
                    tableDataPojo.setTotalAttribute(TotalAttribute[i]);
                    items.add(tableDataPojo);
                    CompanyAdapte = new TableDataAdapter(items,getActivity());
                    lview.setAdapter(CompanyAdapte);


                    }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... arg) {

            String URL = Config.Url+"lob.asmx";

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
        new myLobAsyncTask().execute();
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

            new myLobAsyncTask().execute();

            }

            @Override
            protected Void doInBackground(Void... params) {
            String url =Config.Url+"lob.asmx";
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
                requestAddlob.addProperty("LobId", sno);
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


//
//   public void getreport()
//   {
//       JSONObject rec = null;
//       try {
//
//
//
//
////           // check if available and not read only
////           if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
////               Log.e("", "Storage not available or read only");
////               return false;
////           }
//
////           boolean success = false;
//
//           //New Workbook
//           Workbook wb = new HSSFWorkbook();
//
//           Cell c = null;
//
//           //Cell style for header row
//           CellStyle cs = wb.createCellStyle();
//           cs.setFillForegroundColor(HSSFColor.LIME.index);
//           cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//
//           //New Sheet
//           Sheet sheet1 = null;
//           sheet1 = wb.createSheet("myLOB");
//
//           // Generate column headings
//
//
//           // Create a path where we will place our List of objects on external storage
//           //File file = new File(context.getExternalFilesDir(null), fileName);
//
//
//           JSONArray jsonArray = new JSONArray(String.valueOf(result));
//
//
//           Log.i("RESPONSEjsonArray", String.valueOf(jsonArray));
//           length = jsonArray.length();
//           Log.e("length", "length" + length);
//
//           LobId = new String[length];
//           Name = new String[length];
//           TotalAttribute = new String[length];
//           int rowCount = 0;
//
//
//
//
//
//          // Workbook workbook = new XSSFWorkbook();
//
//          // Sheet sheet = workbook.createSheet("Report");
//           for (int i = 0; i < length; i++) {
//
//
//
//
//               JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//               LobId[i] = jsonObject.getString("LobId");
//
//               Name[i] = jsonObject.getString("Name");
//               TotalAttribute[i] = jsonObject.getString("TotalAttribute");
//
//               Log.e("Name", "Name" + Name[i]);
//               Log.e("LobId", "LobId" + LobId[i]);
//               Log.e("TotalAttribute", "TotalAttribute" + TotalAttribute[i]);
//
//               Row row = sheet1.createRow(i+1);
//
//               c = row.createCell(0);
//               c.setCellValue("LobId");
//               c.setCellStyle(cs);
//
//               c = row.createCell(1);
//               c.setCellValue("Name");
//               c.setCellStyle(cs);
//
//               c = row.createCell(2);
//               c.setCellValue("TotalAttribute");
//               c.setCellStyle(cs);
//
//               sheet1.setColumnWidth(0, (15 * 500));
//               sheet1.setColumnWidth(1, (15 * 500));
//               sheet1.setColumnWidth(2, (15 * 500));
//
//           }
//
//
//
//           File file = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "myLOBExcel.xls");
//
//
//
//           FileOutputStream os = null;
//
//           try {
//               os = new FileOutputStream(file);
//               wb.write(os);
//               Log.w("FileUtils", "Writing file" + file);
//              // success = true;
//           } catch (IOException e) {
//               Log.w("FileUtils", "Error writing " + file, e);
//           } catch (Exception e) {
//               Log.w("FileUtils", "Failed to save file", e);
//           } finally {
//               try {
//                   if (null != os)
//                       os.close();
//               } catch (Exception ex) {
//               }
//           }
//
//       } catch (JSONException e) {
//
//       }
//       }

    public class myExportLobAsyncTask extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPostExecute(String  result) {
            super.onPostExecute(result);
            items.clear();

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
                LobId = new String[length];
                Name = new String[length];
                TotalAttribute = new String[length];
                int rowCount = 0;

//                XSSFWorkbook workbook = new XSSFWorkbook();
//                XSSFSheet sheet = workbook.getSheetAt(0);
                //XSSFWorkbook workbook = new XSSFWorkbook();
               // XSSFSheet sheet = workbook.createSheet("Report");
//                Workbook workbook = new XSSFWorkbook();
//
//                Sheet sheet = workbook.createSheet("Report");
                for (int i = 0; i < length; i++) {
                    row=null;
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    LobId[i] = jsonObject.getString("LobId");
                    Name[i] = jsonObject.getString("Name");
                    TotalAttribute[i] = jsonObject.getString("TotalAttribute");

                    Log.e("Name", "Name" + Name[i]);
                    Log.e("LobId", "LobId" + LobId[i]);
                    Log.e("TotalAttribute", "TotalAttribute" + TotalAttribute[i]);

                    row = sheet1.createRow(i+1);

                    c = row.createCell(0);
                    c.setCellValue(LobId[i]);
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
                //DIRECTORY_DOCUMENTS here error occured because min api level was 15.
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

            } catch (JSONException e) {

            }
        }








        @Override
        protected String doInBackground(String... arg) {

            String URL = Config.Url+"lob.asmx";

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

//    public void myexcel()
//    {
//        String Fnamexls="testfile"  + ".xls";
//        File sdCard = Environment.getExternalStorageDirectory();
//        File directory = new File (sdCard.getAbsolutePath() + "/AmpinityInventory");
//        directory.mkdirs();
//        File file = new File(directory, Fnamexls);
//
//        WorkbookSettings wbSettings = new WorkbookSettings();
//
//        wbSettings.setLocale(new Locale("en", "EN"));
//
//        WritableWorkbook workbook;
//        try {
//            int a = 1;
//            workbook = Workbook.createWorkbook(file, wbSettings);
//            //workbook.createSheet("Report", 0);
//            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
//            Label label = new Label(0, 2, "SECOND");
//            Label label1 = new Label(0,1,"first");
//            Label label0 = new Label(0,0,"HEADING");
//            Label label3 = new Label(1,0,"Heading2");
//            Label label4 = new Label(1,1,String.valueOf(a));
//            try {
//                sheet.addCell(label);
//                sheet.addCell(label1);
//                sheet.addCell(label0);
//                sheet.addCell(label4);
//                sheet.addCell(label3);
//            } catch (RowsExceededException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (WriteException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//
//            workbook.write();
//            try {
//                workbook.close();
//            } catch (WriteException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            //createExcel(excelSheet);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }


}
