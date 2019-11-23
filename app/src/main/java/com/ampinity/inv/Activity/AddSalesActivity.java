package com.ampinity.inv.Activity;

import android.app.DatePickerDialog;
/*
import android.support.v7.app.AppCompatActivity;
*/
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddSalesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

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
            "XYZA"};
    LinearLayout idlayoutpurchase;
    Button idButtonAddItem,idButtonRemoveItem,idButtonCancel;
    AutoCompleteTextView textView=null;
    private ArrayAdapter<String> adapter;
    String item[]={
            "Janu", "ruary", "rch", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add CreditNote");
        textView = (AutoCompleteTextView) findViewById(R.id.Months);

        //Create adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);

        textView.setThreshold(1);

        //Set adapter to AutoCompleteTextView
        textView.setAdapter(adapter);
        textView.setOnItemSelectedListener(this);
        textView.setOnItemClickListener(this);

        listviewtable = (ListView) findViewById(R.id.listviewctable);

        PaymentBy = (Spinner) findViewById(R.id.PaymentBy);
        final ArrayAdapter<String> adapter2 = new CustomizedSpinnerAdapter(
                AddSalesActivity.this, android.R.layout.simple_spinner_item,
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
                new DatePickerDialog(AddSalesActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        idEditTextDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddSalesActivity.this, date, myCalendar
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
//        idButtonAddItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        });
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



        tableDataAdapter = new TableDataAdapter(items, AddSalesActivity.this);
        listviewtable.setAdapter(tableDataAdapter);
        tableDataAdapter.notifyDataSetChanged();


    }
}
