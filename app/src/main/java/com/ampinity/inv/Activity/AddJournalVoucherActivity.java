package com.ampinity.inv.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
/*import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;*/
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import com.ampinity.inv.R;
import com.ampinity.inv.adapter.CustomizedSpinnerAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddJournalVoucherActivity extends AppCompatActivity {

    Button idButtonCancel;
    EditText edittext,idEditTextDatePaid,idEditTextDate;
    AlertDialog alertDialog1;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    Spinner spinnerAccount,spinnerAccountName;
    RadioGroup selectMode;
    RadioButton radio_Paid,radio_UnPaid;
    String [] CompanyName={
            "Select Company Name",
            "ABCA",
            "PQRS",
            "LNMO",
            "XYZA"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal_voucher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Journal Voucher Details");
        spinnerAccountName = (Spinner) findViewById(R.id.spinnerAccountName);

        final ArrayAdapter<String> adapter2 = new CustomizedSpinnerAdapter(
                AddJournalVoucherActivity.this, android.R.layout.simple_spinner_item,
                CompanyName);

        spinnerAccountName.setAdapter(adapter2);

        myCalendar = Calendar.getInstance();

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
        idEditTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddJournalVoucherActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }

        });
        idButtonCancel=(Button)findViewById(R.id.idButtonCancel);
        idButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        idEditTextDate.setText(sdf.format(myCalendar.getTime()));
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
}
