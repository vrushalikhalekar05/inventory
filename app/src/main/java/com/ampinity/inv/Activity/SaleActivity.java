package com.ampinity.inv.Activity;

import android.content.Intent;
/*
import android.support.v7.app.AppCompatActivity;
*/
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ampinity.inv.R;
import com.ampinity.inv.fragment.PurchaseVenderTypefragment;
import com.ampinity.inv.fragment.SalesCreditNotefragment;
import com.ampinity.inv.fragment.SalesReturnfragment;
import com.ampinity.inv.fragment.Salesfragment;
import com.ampinity.inv.fragment.SalesCustomerfragment;
import com.google.android.material.tabs.TabLayout;

public class SaleActivity extends AppCompatActivity {
    int backButtonCount=0;
    int mWidth;
    int mHeigh;
    LinearLayout.LayoutParams params,params1;
    int viewWidth;
    int viewHeigh;
    LinearLayout idSales,idSalesReturn,idSalesCustomerdetails,idSalesCreditNote,idSalesbottom,idSalesReturnbottom,idSalesCustomerdetailsbottom,idSalesCreditNotebottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sale");

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#FF000000"));
        tabLayout.addTab(tabLayout.newTab().setText("Customer Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Sales"));
        tabLayout.addTab(tabLayout.newTab().setText("Sales Return"));
        tabLayout.addTab(tabLayout.newTab().setText("Credit Note"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final Page5Adapter adapter = new Page5Adapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        /*idSalesCustomerdetails=(LinearLayout)findViewById(R.id.idSalesCustomerdetails);
        idSales=(LinearLayout)findViewById(R.id.idSales);
        idSalesReturn=(LinearLayout)findViewById(R.id.idSalesReturn);
        idSalesCreditNote=(LinearLayout)findViewById(R.id.idSalesCreditNote);
        idSalesCustomerdetailsbottom=(LinearLayout)findViewById(R.id.idSalesCustomerdetailsbottom);
        idSalesbottom=(LinearLayout)findViewById(R.id.idSalesbottom);
        idSalesReturnbottom=(LinearLayout)findViewById(R.id.idSalesReturnbottom);
        idSalesCreditNotebottom=(LinearLayout)findViewById(R.id.idSalesCreditNotebottom);

        idSalesbottom.setVisibility(View.VISIBLE);
        idSalesReturnbottom.setVisibility(View.GONE);
        idSalesCreditNotebottom.setVisibility(View.GONE);
        idSalesCustomerdetailsbottom.setVisibility(View.GONE);


        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth(); // deprecated
        viewWidth = mWidth / 3;

        params = new LinearLayout.LayoutParams(270, LinearLayout.LayoutParams.MATCH_PARENT);


        idSalesCustomerdetails.setLayoutParams(params);
        idSales.setLayoutParams(params);
        idSalesCreditNote.setLayoutParams(params);
        idSalesReturn.setLayoutParams(params);

        Salesfragment fragment = new Salesfragment();
        android.app.FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content_framemaster,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        idSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idSalesbottom.setVisibility(View.VISIBLE);
                idSalesReturnbottom.setVisibility(View.GONE);
                idSalesCustomerdetailsbottom.setVisibility(View.GONE);
                idSalesCreditNotebottom.setVisibility(View.GONE);

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
                idSalesReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idSalesbottom.setVisibility(View.GONE);
                idSalesReturnbottom.setVisibility(View.VISIBLE);
                idSalesCustomerdetailsbottom.setVisibility(View.GONE);
                idSalesCreditNotebottom.setVisibility(View.GONE);

//                idVenderType.setBackgroundColor(getResources().getColor(R.color.grey));
//                idVenderDetail.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idnewPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                SalesReturnfragment fragment = new SalesReturnfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

                idSalesCustomerdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idSalesbottom.setVisibility(View.GONE);
                idSalesReturnbottom.setVisibility(View.GONE);
                idSalesCustomerdetailsbottom.setVisibility(View.VISIBLE);
                idSalesCreditNotebottom.setVisibility(View.GONE);
//                idVenderType.setBackgroundColor(getResources().getColor(R.color.grey));
//                idVenderDetail.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idnewPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                SalesCustomerfragment fragment = new SalesCustomerfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

                idSalesCreditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idSalesbottom.setVisibility(View.GONE);
                idSalesReturnbottom.setVisibility(View.GONE);
                idSalesCustomerdetailsbottom.setVisibility(View.GONE);
                idSalesCreditNotebottom.setVisibility(View.VISIBLE);
//                idVenderType.setBackgroundColor(getResources().getColor(R.color.grey));
//                idVenderDetail.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idnewPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                SalesCreditNotefragment fragment = new SalesCreditNotefragment();
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

