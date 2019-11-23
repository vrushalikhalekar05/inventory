package com.ampinity.inv.Activity;

import android.app.DatePickerDialog;
/*
import android.support.v7.app.AppCompatActivity;
*/
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.content.Intent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ampinity.inv.Model.TableDataPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.adapter.CustomizedSpinnerAdapter;
import com.ampinity.inv.adapter.TableDataAdapter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddSalesCreditNoteActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener{

    static ListView listviewtable;
    ArrayList<TableDataPojo> items = new ArrayList<>();
    public static ArrayList<TableDataPojo> TableDataPojos;
    TableDataPojo TableDataPojoo;
    static TableDataAdapter tableDataAdapter;

    DatePickerDialog.OnDateSetListener date;
    Spinner spinnercategory,PaymentBy;
    EditText idEditTextDatePaid,idEditTextDate;
    Calendar myCalendar;
    RadioGroup selectMode,TypeMode;
    RadioButton radio_Paid,radio_UnPaid,radio_taxinvoice,radio_cashsale;
    String [] CompanyName={
            "Select Payment Mode",
            "Cash",
            "Check",
            "Credit Card",
            "XYZA"
    };
    LinearLayout idlayoutpurchase;
    Button idButtonAddItem,idButtonRemoveItem,idButtonCancel;
    AutoCompleteTextView textView=null;
    private ArrayAdapter<String> adapter;
    EditText idEditTextItemIdFormat,idEditTextItemId;
    String item[]={
            "Janu", "ruary", "rch", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales_credit_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Sales");
        textView = (AutoCompleteTextView) findViewById(R.id.Months);

        //Create adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);

        textView.setThreshold(1);

        //Set adapter to AutoCompleteTextView
        textView.setAdapter(adapter);
        textView.setOnItemSelectedListener(this);
        textView.setOnItemClickListener(this);
        idEditTextItemIdFormat=(EditText)findViewById(R.id.idEditTextItemIdFormat) ;
                idEditTextItemId=(EditText)findViewById(R.id.idEditTextItemId) ;

        listviewtable = (ListView) findViewById(R.id.listviewctable);

        PaymentBy = (Spinner) findViewById(R.id.PaymentBy);
        final ArrayAdapter<String> adapter2 = new CustomizedSpinnerAdapter(
                AddSalesCreditNoteActivity.this, android.R.layout.simple_spinner_item,
                CompanyName);
        PaymentBy.setAdapter(adapter2);

        myCalendar = Calendar.getInstance();
        TypeMode= (RadioGroup) findViewById(R.id.TypeMode);
        selectMode = (RadioGroup) findViewById(R.id.selectMode);
        radio_Paid= (RadioButton) findViewById(R.id.radio_Paid);
        radio_UnPaid= (RadioButton) findViewById(R.id.radio_UnPaid);
        radio_taxinvoice= (RadioButton) findViewById(R.id.radio_taxinvoice);
        radio_cashsale= (RadioButton) findViewById(R.id.radio_cashsale);
        idEditTextDatePaid= (EditText) findViewById(R.id.idEditTextDatePaid);
        idEditTextDate= (EditText) findViewById(R.id.idEditTextDate);

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
                new DatePickerDialog(AddSalesCreditNoteActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        idEditTextDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddSalesCreditNoteActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        idlayoutpurchase=(LinearLayout)findViewById(R.id.idlayoutpurchase);
        idButtonRemoveItem=(Button)findViewById(R.id.idButtonRemoveItem);
        idButtonAddItem=(Button)findViewById(R.id.idButtonAddItem);
        idButtonCancel=(Button)findViewById(R.id.idButtonCancel);
        idButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        idButtonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scanNow(v);

            }
        });
        idButtonRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idlayoutpurchase.setVisibility(View.GONE);

            }
        });
        pojoadd();
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

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
                               long arg3) {
        // TODO Auto-generated method stub
        //Log.d("AutocompleteContacts", "onItemSelected() position " + position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

        InputMethodManager imm = (InputMethodManager) getSystemService(
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub

        // Show Alert
        Toast.makeText(getBaseContext(), "Position:"+arg2+" Month:"+arg0.getItemAtPosition(arg2),
                Toast.LENGTH_LONG).show();

        Log.d("AutocompleteContacts", "Position:"+arg2+" Month:"+arg0.getItemAtPosition(arg2));

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        idEditTextDate.setText(sdf.format(myCalendar.getTime()));
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

    public void  taxinvoice(View view)
    {
        radio_cashsale.setChecked(false);
        radio_taxinvoice.setChecked(true);
    }

    public void  cashsale(View view)
    {
        radio_cashsale.setChecked(true);
        radio_taxinvoice.setChecked(false);
    }



    void pojoadd() {

        for(int i=0;i<20;i++) {
//            TableDataPojo tablepojo = new TableDataPojo();
//            tablepojo.setTableid(i);
//
//            items.add(tablepojo);
        }



        tableDataAdapter = new TableDataAdapter(items, AddSalesCreditNoteActivity.this);
        listviewtable.setAdapter(tableDataAdapter);
        tableDataAdapter.notifyDataSetChanged();


    }

    /**
     * event handler for scan button
     * @param view view of the activity
     */
    public void scanNow(View view){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setResultDisplayDuration(0);
         // Wide scanning rectangle, may work better for 1D barcodes
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.initiateScan();
    }

    /**
     * function handle scan result
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            // display it on screen
            idEditTextItemId.setText("FORMAT: " + scanFormat);
            idEditTextItemIdFormat.setText("CONTENT: " + scanContent);

        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
