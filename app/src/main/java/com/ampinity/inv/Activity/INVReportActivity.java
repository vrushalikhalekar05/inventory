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
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ampinity.inv.R;
import com.ampinity.inv.fragment.MasterAccountGroupfragment;
import com.ampinity.inv.fragment.MasterAccountLedgerfragment;
import com.ampinity.inv.fragment.ReportCashInfragment;
import com.ampinity.inv.fragment.ReportCashOutfragment;
import com.ampinity.inv.fragment.ReportCategoryListfragment;
import com.ampinity.inv.fragment.ReportCustomerListfragment;
import com.ampinity.inv.fragment.ReportCustomerPaymentfragment;
import com.ampinity.inv.fragment.ReportGstfragment;
import com.ampinity.inv.fragment.ReportProfitLossfragment;
import com.ampinity.inv.fragment.ReportPurchasefragment;
import com.ampinity.inv.fragment.ReportSalesfragment;
import com.ampinity.inv.fragment.ReportStockfragment;
import com.ampinity.inv.fragment.ReportSupplierListfragment;
import com.ampinity.inv.fragment.ReportSupplierPaymentfragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class INVReportActivity extends AppCompatActivity {

    int mWidth;
    int mHeigh;
    LayoutParams params,params1;
    int viewWidth;
    int backButtonCount=0;

    int viewHeigh;
    LinearLayout idCustomerList,idCustomerListbottom,idSupplierList,idSupplierListbottom,idCategoryList,idCategoryListbottom,idPurchase,idPurchasebottom,idSales,idSalesbottom,idStock,idStockbottom,idCashIn,idCashInbottom,idCashOut,idCashOutbottom,idSupplierPayment,idSupplierPaymentbottom,idCustomerPayment,idCustomerPaymentbottom,idProfitLoss,idProfitLossbottom,idGst,idGstbottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invreport);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Report");

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);


        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#FF000000"));
        tabLayout.addTab(tabLayout.newTab().setText("Customer List"));
        tabLayout.addTab(tabLayout.newTab().setText("Supplier List"));
        tabLayout.addTab(tabLayout.newTab().setText("Category List"));
        tabLayout.addTab(tabLayout.newTab().setText("Purchase"));
        tabLayout.addTab(tabLayout.newTab().setText("Sales"));
        tabLayout.addTab(tabLayout.newTab().setText("Stock"));
        tabLayout.addTab(tabLayout.newTab().setText("CashIn"));
        tabLayout.addTab(tabLayout.newTab().setText("CashOut"));
        tabLayout.addTab(tabLayout.newTab().setText("Supplier Payment"));
        tabLayout.addTab(tabLayout.newTab().setText("Customer Payment"));
        tabLayout.addTab(tabLayout.newTab().setText("Profit Loss"));
        tabLayout.addTab(tabLayout.newTab().setText("Gst"));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final Page8Adapter adapter = new Page8Adapter
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

/*        idCustomerList=(LinearLayout) findViewById(R.id.idCustomerList);
        idCustomerListbottom=(LinearLayout) findViewById(R.id.idCustomerListbottom);
        idSupplierList=(LinearLayout) findViewById(R.id.idSupplierList);
        idSupplierListbottom=(LinearLayout) findViewById(R.id.idSupplierListbottom);
        idCategoryList=(LinearLayout) findViewById(R.id.idCategoryList);
        idCategoryListbottom=(LinearLayout) findViewById(R.id.idCategoryListbottom);
        idPurchase=(LinearLayout) findViewById(R.id.idPurchase);
        idPurchasebottom=(LinearLayout) findViewById(R.id.idPurchasebottom);
        idSales=(LinearLayout) findViewById(R.id.idSales);
        idSalesbottom=(LinearLayout) findViewById(R.id.idSalesbottom);
        idStock=(LinearLayout) findViewById(R.id.idStock);
        idStockbottom=(LinearLayout) findViewById(R.id.idStockbottom);
        idCashIn=(LinearLayout) findViewById(R.id.idCashIn);
        idCashInbottom=(LinearLayout) findViewById(R.id.idCashInbottom);
        idCashOut=(LinearLayout) findViewById(R.id.idCashOut);
        idCashOutbottom=(LinearLayout) findViewById(R.id.idCashOutbottom);
        idSupplierPayment=(LinearLayout) findViewById(R.id.idSupplierPayment);
        idSupplierPaymentbottom=(LinearLayout) findViewById(R.id.idSupplierPaymentbottom);
        idCustomerPayment=(LinearLayout) findViewById(R.id.idCustomerPayment);
        idCustomerPaymentbottom=(LinearLayout) findViewById(R.id.idCustomerPaymentbottom);
        idProfitLoss=(LinearLayout) findViewById(R.id.idProfitLoss);
        idProfitLossbottom=(LinearLayout) findViewById(R.id.idProfitLossbottom);
        idGst=(LinearLayout) findViewById(R.id.idGst);
        idGstbottom=(LinearLayout) findViewById(R.id.idGstbottom);
        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth(); // deprecated
        viewWidth = mWidth / 3;
        mHeigh = display.getHeight(); // deprecated
        viewHeigh = mHeigh / 8;

        params = new LinearLayout.LayoutParams(viewWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        params1 = new LinearLayout.LayoutParams(viewHeigh, LinearLayout.LayoutParams.WRAP_CONTENT);




        idCustomerList.setLayoutParams(params);
        idSupplierList.setLayoutParams(params);
        idCategoryList.setLayoutParams(params);
        idPurchase.setLayoutParams(params);
        idSales.setLayoutParams(params);
        idStock.setLayoutParams(params);
        idCashIn.setLayoutParams(params);
        idCashOut.setLayoutParams(params);
        idSupplierPayment.setLayoutParams(params);
        idCustomerPayment.setLayoutParams(params);
        idProfitLoss.setLayoutParams(params);
        idGst.setLayoutParams(params);


        idCustomerListbottom.setVisibility(View.VISIBLE);
        idSupplierListbottom.setVisibility(View.GONE);
        idCategoryListbottom.setVisibility(View.GONE);
        idPurchasebottom.setVisibility(View.GONE);
        idSalesbottom.setVisibility(View.GONE);
        idStockbottom.setVisibility(View.GONE);
        idCashInbottom.setVisibility(View.GONE);
        idCashOutbottom.setVisibility(View.GONE);
        idSupplierPaymentbottom.setVisibility(View.GONE);
        idCustomerPaymentbottom.setVisibility(View.GONE);
        idProfitLossbottom.setVisibility(View.GONE);
        idGstbottom.setVisibility(View.GONE);

        ReportCustomerListfragment fragment = new ReportCustomerListfragment();
        android.app.FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content_framemaster,fragment);
        transaction.addToBackStack(null);
        transaction.commit();



        idCustomerList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                idCustomerListbottom.setVisibility(View.VISIBLE);
                idSupplierListbottom.setVisibility(View.GONE);
                idCategoryListbottom.setVisibility(View.GONE);
                idPurchasebottom.setVisibility(View.GONE);
                idSalesbottom.setVisibility(View.GONE);
                idStockbottom.setVisibility(View.GONE);
                idCashInbottom.setVisibility(View.GONE);
                idCashOutbottom.setVisibility(View.GONE);
                idSupplierPaymentbottom.setVisibility(View.GONE);
                idCustomerPaymentbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);


                ReportCustomerListfragment fragment = new ReportCustomerListfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        idSupplierList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                idCustomerListbottom.setVisibility(View.GONE);
                idSupplierListbottom.setVisibility(View.VISIBLE);
                idCategoryListbottom.setVisibility(View.GONE);
                idPurchasebottom.setVisibility(View.GONE);
                idSalesbottom.setVisibility(View.GONE);
                idStockbottom.setVisibility(View.GONE);
                idCashInbottom.setVisibility(View.GONE);
                idCashOutbottom.setVisibility(View.GONE);
                idSupplierPaymentbottom.setVisibility(View.GONE);
                idCustomerPaymentbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);

                ReportSupplierListfragment fragment = new ReportSupplierListfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        idCategoryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                idCustomerListbottom.setVisibility(View.GONE);
                idSupplierListbottom.setVisibility(View.GONE);
                idCategoryListbottom.setVisibility(View.VISIBLE);
                idPurchasebottom.setVisibility(View.GONE);
                idSalesbottom.setVisibility(View.GONE);
                idStockbottom.setVisibility(View.GONE);
                idCashInbottom.setVisibility(View.GONE);
                idCashOutbottom.setVisibility(View.GONE);
                idSupplierPaymentbottom.setVisibility(View.GONE);
                idCustomerPaymentbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);

                ReportCategoryListfragment fragment = new ReportCategoryListfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        idPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                idCustomerListbottom.setVisibility(View.GONE);
                idSupplierListbottom.setVisibility(View.GONE);
                idCategoryListbottom.setVisibility(View.GONE);
                idPurchasebottom.setVisibility(View.VISIBLE);
                idSalesbottom.setVisibility(View.GONE);
                idStockbottom.setVisibility(View.GONE);
                idCashInbottom.setVisibility(View.GONE);
                idCashOutbottom.setVisibility(View.GONE);
                idSupplierPaymentbottom.setVisibility(View.GONE);
                idCustomerPaymentbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);


                ReportPurchasefragment fragment = new ReportPurchasefragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        idSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                idCustomerListbottom.setVisibility(View.GONE);
                idSupplierListbottom.setVisibility(View.GONE);
                idCategoryListbottom.setVisibility(View.GONE);
                idPurchasebottom.setVisibility(View.GONE);
                idSalesbottom.setVisibility(View.VISIBLE);
                idStockbottom.setVisibility(View.GONE);
                idCashInbottom.setVisibility(View.GONE);
                idCashOutbottom.setVisibility(View.GONE);
                idSupplierPaymentbottom.setVisibility(View.GONE);
                idCustomerPaymentbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);


                ReportSalesfragment fragment = new ReportSalesfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        idStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                idCustomerListbottom.setVisibility(View.GONE);
                idSupplierListbottom.setVisibility(View.GONE);
                idCategoryListbottom.setVisibility(View.GONE);
                idPurchasebottom.setVisibility(View.GONE);
                idSalesbottom.setVisibility(View.GONE);
                idStockbottom.setVisibility(View.VISIBLE);
                idCashInbottom.setVisibility(View.GONE);
                idCashOutbottom.setVisibility(View.GONE);
                idSupplierPaymentbottom.setVisibility(View.GONE);
                idCustomerPaymentbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);

                ReportStockfragment fragment = new ReportStockfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        idCashIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                idCustomerListbottom.setVisibility(View.GONE);
                idSupplierListbottom.setVisibility(View.GONE);
                idCategoryListbottom.setVisibility(View.GONE);
                idPurchasebottom.setVisibility(View.GONE);
                idSalesbottom.setVisibility(View.GONE);
                idStockbottom.setVisibility(View.GONE);
                idCashInbottom.setVisibility(View.VISIBLE);
                idCashOutbottom.setVisibility(View.GONE);
                idSupplierPaymentbottom.setVisibility(View.GONE);
                idCustomerPaymentbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);


                ReportCashInfragment fragment = new ReportCashInfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        idCashOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                idCustomerListbottom.setVisibility(View.GONE);
                idSupplierListbottom.setVisibility(View.GONE);
                idCategoryListbottom.setVisibility(View.GONE);
                idPurchasebottom.setVisibility(View.GONE);
                idSalesbottom.setVisibility(View.GONE);
                idStockbottom.setVisibility(View.GONE);
                idCashInbottom.setVisibility(View.GONE);
                idCashOutbottom.setVisibility(View.VISIBLE);
                idSupplierPaymentbottom.setVisibility(View.GONE);
                idCustomerPaymentbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);

                ReportCashOutfragment fragment = new ReportCashOutfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        idSupplierPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                idCustomerListbottom.setVisibility(View.GONE);
                idSupplierListbottom.setVisibility(View.GONE);
                idCategoryListbottom.setVisibility(View.GONE);
                idPurchasebottom.setVisibility(View.GONE);
                idSalesbottom.setVisibility(View.GONE);
                idStockbottom.setVisibility(View.GONE);
                idCashInbottom.setVisibility(View.GONE);
                idCashOutbottom.setVisibility(View.GONE);
                idSupplierPaymentbottom.setVisibility(View.VISIBLE);
                idCustomerPaymentbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);

                ReportSupplierPaymentfragment fragment = new ReportSupplierPaymentfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        idCustomerPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                idCustomerListbottom.setVisibility(View.GONE);
                idSupplierListbottom.setVisibility(View.GONE);
                idCategoryListbottom.setVisibility(View.GONE);
                idPurchasebottom.setVisibility(View.GONE);
                idSalesbottom.setVisibility(View.GONE);
                idStockbottom.setVisibility(View.GONE);
                idCashInbottom.setVisibility(View.GONE);
                idCashOutbottom.setVisibility(View.GONE);
                idSupplierPaymentbottom.setVisibility(View.GONE);
                idCustomerPaymentbottom.setVisibility(View.VISIBLE);
                idProfitLossbottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);


                ReportCustomerPaymentfragment fragment = new ReportCustomerPaymentfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        idProfitLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                idCustomerListbottom.setVisibility(View.GONE);
                idSupplierListbottom.setVisibility(View.GONE);
                idCategoryListbottom.setVisibility(View.GONE);
                idPurchasebottom.setVisibility(View.GONE);
                idSalesbottom.setVisibility(View.GONE);
                idStockbottom.setVisibility(View.GONE);
                idCashInbottom.setVisibility(View.GONE);
                idCashOutbottom.setVisibility(View.GONE);
                idSupplierPaymentbottom.setVisibility(View.GONE);
                idCustomerPaymentbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.VISIBLE);
                idGstbottom.setVisibility(View.GONE);


                ReportProfitLossfragment fragment = new ReportProfitLossfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        idGst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                idCustomerListbottom.setVisibility(View.GONE);
                idSupplierListbottom.setVisibility(View.GONE);
                idCategoryListbottom.setVisibility(View.GONE);
                idPurchasebottom.setVisibility(View.GONE);
                idSalesbottom.setVisibility(View.GONE);
                idStockbottom.setVisibility(View.GONE);
                idCashInbottom.setVisibility(View.GONE);
                idCashOutbottom.setVisibility(View.GONE);
                idSupplierPaymentbottom.setVisibility(View.GONE);
                idCustomerPaymentbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.VISIBLE);


                ReportGstfragment fragment = new ReportGstfragment();
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


