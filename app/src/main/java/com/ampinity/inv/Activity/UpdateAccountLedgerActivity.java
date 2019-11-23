package com.ampinity.inv.Activity;

/*
import android.support.v7.app.AppCompatActivity;
*/
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ampinity.inv.R;

public class UpdateAccountLedgerActivity extends AppCompatActivity {

    Button idButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account_ledger);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Account Ledger Details");
        idButtonCancel=(Button)findViewById(R.id.idButtonCancel);
        idButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
