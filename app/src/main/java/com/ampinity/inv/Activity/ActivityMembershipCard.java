package com.ampinity.inv.Activity;

import android.content.Intent;
/*
import android.support.v7.app.AppCompatActivity;
*/
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ampinity.inv.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityMembershipCard extends AppCompatActivity {
    Button buttoncardType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_card);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //   getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Card Details");
        buttoncardType= (Button) findViewById(R.id.ButtonOfferName);

        buttoncardType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityMembershipCard.this,ActivityCardType.class);
                startActivity(intent);

            }
        });
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
