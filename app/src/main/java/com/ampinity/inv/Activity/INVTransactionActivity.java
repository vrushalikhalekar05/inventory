package com.ampinity.inv.Activity;

import android.content.Intent;
/*
import android.support.v7.app.AppCompatActivity;
*/
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ampinity.inv.R;
import com.ampinity.inv.fragment.Salesfragment;


public class INVTransactionActivity extends AppCompatActivity {

    int backButtonCount=0;
    LinearLayout idAddTran,idTranCashOut,idTranCashIn,idAddTranbottom,idTranCashOutbottom,idTranCashInbottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invtransaction);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Transaction");

/*        idAddTran=(LinearLayout)findViewById(R.id.idAddTran);
        idTranCashOut=(LinearLayout)findViewById(R.id.idTranCashOut);
        idTranCashIn=(LinearLayout)findViewById(R.id.idTranCashIn);
        idAddTranbottom=(LinearLayout)findViewById(R.id.idAddTranbottom);
        idTranCashOutbottom=(LinearLayout)findViewById(R.id.idTranCashOutbottom);
        idTranCashInbottom=(LinearLayout)findViewById(R.id.idTranCashInbottom);



        idAddTranbottom.setVisibility(View.GONE);
        idTranCashOutbottom.setVisibility(View.GONE);
        idTranCashInbottom.setVisibility(View.VISIBLE);

        idTranCashIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idAddTranbottom.setVisibility(View.GONE);
                idTranCashOutbottom.setVisibility(View.GONE);
                idTranCashInbottom.setVisibility(View.VISIBLE);
//                idVenderType.setBackgroundColor(getResources().getColor(R.color.grey));
//                idVenderDetail.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idnewPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                Salesfragment fragment = new Salesfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        idTranCashOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idAddTranbottom.setVisibility(View.GONE);
                idTranCashOutbottom.setVisibility(View.VISIBLE);
                idTranCashInbottom.setVisibility(View.GONE);
//                idVenderType.setBackgroundColor(getResources().getColor(R.color.grey));
//                idVenderDetail.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idnewPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                Salesfragment fragment = new Salesfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        idAddTran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idAddTranbottom.setVisibility(View.VISIBLE);
                idTranCashOutbottom.setVisibility(View.GONE);
                idTranCashInbottom.setVisibility(View.GONE);
//                idVenderType.setBackgroundColor(getResources().getColor(R.color.grey));
//                idVenderDetail.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idnewPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                Salesfragment fragment = new Salesfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });*/

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
    public void onBackPressed()
    {
        if(backButtonCount >= 1)
        {
            backButtonCount = 0;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press again to exit.", Toast.LENGTH_LONG).show();
            backButtonCount++;
        }
    }
}


