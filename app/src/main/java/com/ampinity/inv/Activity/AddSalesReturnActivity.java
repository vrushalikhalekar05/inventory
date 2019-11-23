package com.ampinity.inv.Activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
/*
import android.support.v7.app.AppCompatActivity;
*/
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ampinity.inv.R;
import com.ampinity.inv.adapter.CustomizedSpinnerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddSalesReturnActivity extends AppCompatActivity {
    RadioGroup selectMode;
    DatePickerDialog.OnDateSetListener date;
    Spinner spinnercategory, PaymentBy;
    EditText idEditTextDatePaid, idEditTextDate;
    Calendar myCalendar;
    RadioButton radio_Paid, radio_UnPaid;
    TextView sno, pname;


    TableRow tr;
    TableLayout tl;
    TableRow mTable = null;
    TextView companyTV, valueTV;
    String[] CompanyName = {
            "Select Payment Mode",
            "Cash",
            "Check",
            "Credit Card",
            "XYZA"};
    LinearLayout idlayoutpurchase;
    TableRow row;
    String companies[] = {"Google", "Windows", "iPhone", "Nokia", "Samsung",
            "Google", "Windows", "iPhone", "Nokia", "Samsung", "Google",
            "Windows", "iPhone", "Nokia", "Samsung"};
    String os[] = {"Android", "Mango", "iOS", "Symbian", "Bada", "Android",
            "Mango", "iOS", "Symbian", "Bada", "Android", "Mango", "iOS",
            "Symbian", "Bada"};
    Button idButtonAddItem, idButtonRemoveItem, idButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales_return);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Sales Return");

        PaymentBy = (Spinner) findViewById(R.id.PaymentBy);
        final ArrayAdapter<String> adapter2 = new CustomizedSpinnerAdapter(
                AddSalesReturnActivity.this, android.R.layout.simple_spinner_item,
                CompanyName);
        PaymentBy.setAdapter(adapter2);


        tl = (TableLayout) findViewById(R.id.main_table);

        TableLayout table = (TableLayout) findViewById(R.id.tablereturn);
        sno = (TextView) findViewById(R.id.sno);
        pname = (TextView) findViewById(R.id.pname);
        row = (TableRow) findViewById(R.id.tablerowreturn);//new TableRow(this);
        row.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TableRow t = (TableRow) view;
                TextView firstTextView = (TextView) t.getChildAt(0);
                TextView secondTextView = (TextView) t.getChildAt(1);
                String firstText = firstTextView.getText().toString();
                String secondText = secondTextView.getText().toString();
                Toast.makeText(getApplicationContext(), "value was " + secondText,
                        Toast.LENGTH_LONG).show();
                view.setBackgroundColor(Color.DKGRAY);
            }
        });
        myCalendar = Calendar.getInstance();
        selectMode = (RadioGroup) findViewById(R.id.selectMode);
        radio_Paid = (RadioButton) findViewById(R.id.radio_Paid);
        radio_UnPaid = (RadioButton) findViewById(R.id.radio_UnPaid);
        idEditTextDatePaid = (EditText) findViewById(R.id.idEditTextDatePaid);
        idEditTextDate = (EditText) findViewById(R.id.idEditTextDate);

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
                new DatePickerDialog(AddSalesReturnActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        idEditTextDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddSalesReturnActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        idlayoutpurchase = (LinearLayout) findViewById(R.id.idlayoutpurchase);
        idButtonRemoveItem = (Button) findViewById(R.id.idButtonRemoveItem);
        idButtonAddItem = (Button) findViewById(R.id.idButtonAddItem);
        idButtonCancel = (Button) findViewById(R.id.idButtonCancel);
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
       // addData();
        addDataa();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        idEditTextDate.setText(sdf.format(myCalendar.getTime()));
    }

    public void addData() {

        for (int i = 0; i < companies.length; i++) {
            /** Create a TableRow dynamically **/
            row = new TableRow(this);


            // ImageView im = new ImageView(this);
            // im.setBackgroundResource(R.drawable.sample_image);
            // im.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
            // LayoutParams.WRAP_CONTENT));
            // tr.addView(im);
            /** Creating a TextView to add to the row **/
            TextView firstTextView = (TextView) row.getChildAt(0);
            TextView secondTextView = (TextView) row.getChildAt(1);
            sno = new TextView(this);
            row.addView(sno);
            sno.setText(i);

            pname = new TextView(this);
            row.addView(pname);
            pname.setText(companies[i]);


        }
    }

    public void paidradio(View view) {
        radio_UnPaid.setChecked(false);
        radio_Paid.setChecked(true);
    }

    public void Unpaidradio(View view) {
        radio_UnPaid.setChecked(true);
        radio_Paid.setChecked(false);
    }


    public void addDataa() {

        for (int i = 0; i < companies.length; i++) {
            /** Create a TableRow dynamically **/
            tr = new TableRow(this);
            tr.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            // ImageView im = new ImageView(this);
            // im.setBackgroundResource(R.drawable.sample_image);
            // im.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
            // LayoutParams.WRAP_CONTENT));
            // tr.addView(im);
            /** Creating a TextView to add to the row **/
            companyTV = new TextView(this);
            companyTV.setText(companies[i]);
            companyTV.setTextColor(Color.RED);
            companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
           // companyTV.setLayoutParams(new LinearLayout.LayoutParams(
           //         LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            companyTV.setPadding(5, 5, 5, 5);
            tr.addView(companyTV); // Adding textView to tablerow.

            /** Creating another textview **/
            valueTV = new TextView(this);
            valueTV.setText(os[i]);
            valueTV.setTextColor(Color.GREEN);
           // valueTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
             //       LinearLayout.LayoutParams.WRAP_CONTENT));
            valueTV.setPadding(5, 5, 5, 5);
            valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(valueTV); // Adding textView to tablerow.

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

            // tr.setOnClickListener(new View.OnClickListener() {
            // public void onClick(View view) {
            // view.setBackgroundColor(Color.DKGRAY);
            // }
            // });
            //
            // tr.setOnLongClickListener(new View.OnLongClickListener() {
            // public boolean onLongClick(View v) {
            // mTable = (TableRow) v; // assign selected TableRow gobally
            // openContextMenu(v);
            // return true;
            // }
            // });

        }
    }
}
