package com.ampinity.inv.Activity;

/*
import android.support.v7.app.AppCompatActivity;
*/
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.ampinity.inv.R;

public class UpdateAccountGroupActivity extends AppCompatActivity {

    Spinner spinnergroupname, spinneraccountgroupname;
    Button idButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account_group);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Account Group Details");
        spinnergroupname = (Spinner) findViewById(R.id.spinnergroupname);
        spinneraccountgroupname = (Spinner) findViewById(R.id.spinneraccountgroupname);
        idButtonCancel = (Button) findViewById(R.id.idButtonCancel);
        idButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
}
