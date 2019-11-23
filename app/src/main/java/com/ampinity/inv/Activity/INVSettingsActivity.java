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
import com.ampinity.inv.fragment.Salesfragment;
import com.google.android.material.tabs.TabLayout;

public class INVSettingsActivity extends AppCompatActivity {

    int backButtonCount=0;
    int mWidth;
    int mHeigh;
    LinearLayout.LayoutParams params,params1;
    int viewWidth;
    int viewHeigh;

    LinearLayout idSettingBusinessDetails,idSettingBarcodeFormat,idSettingPrinterDetails,idSettingTelephoneDirectory, idSettingBusinessDetailsbottom,idSettingBarcodeFormatbottom,idSettingPrinterDetailsbottom,idSettingTelephoneDirectorybottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invsettings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);


        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#FF000000"));
        tabLayout.addTab(tabLayout.newTab().setText("Business Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Barcode Format"));
        tabLayout.addTab(tabLayout.newTab().setText("Printer Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Telephone Directory"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        /*final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final Page7Adapter adapter = new Page7Adapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));*/
        /*tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            *//*public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }*//*
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }*/
        /*});*/

     /*   idSettingBusinessDetails=(LinearLayout)findViewById(R.id.idSettingBusinessDetails);
        idSettingBarcodeFormat=(LinearLayout)findViewById(R.id.idSettingBarcodeFormat);
        idSettingPrinterDetails=(LinearLayout)findViewById(R.id.idSettingPrinterDetails);
        idSettingTelephoneDirectory=(LinearLayout)findViewById(R.id.idSettingTelephoneDirectory);
        idSettingBusinessDetailsbottom=(LinearLayout)findViewById(R.id.idSettingBusinessDetailsbottom);
        idSettingBarcodeFormatbottom=(LinearLayout)findViewById(R.id.idSettingBarcodeFormatbottom);
        idSettingPrinterDetailsbottom=(LinearLayout)findViewById(R.id.idSettingPrinterDetailsbottom);
        idSettingTelephoneDirectorybottom=(LinearLayout)findViewById(R.id.idSettingTelephoneDirectorybottom);

        idSettingBusinessDetailsbottom.setVisibility(View.VISIBLE);
        idSettingBarcodeFormatbottom.setVisibility(View.GONE);
        idSettingPrinterDetailsbottom.setVisibility(View.GONE);
        idSettingTelephoneDirectorybottom.setVisibility(View.GONE);


        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth(); // deprecated
        viewWidth = mWidth / 3;

        params = new LinearLayout.LayoutParams(viewWidth, LinearLayout.LayoutParams.WRAP_CONTENT);


        idSettingBusinessDetails.setLayoutParams(params);
        idSettingBarcodeFormat.setLayoutParams(params);
        idSettingPrinterDetails.setLayoutParams(params);
        idSettingTelephoneDirectory.setLayoutParams(params);

//        Salesfragment fragment = new Salesfragment();
//        android.app.FragmentManager manager = getFragmentManager();
//        android.app.FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.content_framemaster,fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();



        idSettingBusinessDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idSettingBusinessDetailsbottom.setVisibility(View.VISIBLE);
                idSettingBarcodeFormatbottom.setVisibility(View.GONE);
                idSettingPrinterDetailsbottom.setVisibility(View.GONE);
                idSettingTelephoneDirectorybottom.setVisibility(View.GONE);
//                idVenderType.setBackgroundColor(getResources().getColor(R.color.grey));
//                idVenderDetail.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idnewPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

//                Salesfragment fragment = new Salesfragment();
//                android.app.FragmentManager manager = getFragmentManager();
//                android.app.FragmentTransaction transaction = manager.beginTransaction();
//                transaction.add(R.id.content_framemaster,fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();

            }
        });

        idSettingBarcodeFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idSettingBusinessDetailsbottom.setVisibility(View.GONE);
                idSettingBarcodeFormatbottom.setVisibility(View.VISIBLE);
                idSettingPrinterDetailsbottom.setVisibility(View.GONE);
                idSettingTelephoneDirectorybottom.setVisibility(View.GONE);
//                idVenderType.setBackgroundColor(getResources().getColor(R.color.grey));
//                idVenderDetail.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idnewPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


            }
        });


        idSettingPrinterDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idSettingBusinessDetailsbottom.setVisibility(View.GONE);
                idSettingBarcodeFormatbottom.setVisibility(View.GONE);
                idSettingPrinterDetailsbottom.setVisibility(View.VISIBLE);
                idSettingTelephoneDirectorybottom.setVisibility(View.GONE);
//                idVenderType.setBackgroundColor(getResources().getColor(R.color.grey));
//                idVenderDetail.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idnewPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


            }
        });
        idSettingTelephoneDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idSettingBusinessDetailsbottom.setVisibility(View.GONE);
                idSettingBarcodeFormatbottom.setVisibility(View.GONE);
                idSettingPrinterDetailsbottom.setVisibility(View.GONE);
                idSettingTelephoneDirectorybottom.setVisibility(View.VISIBLE);
//                idVenderType.setBackgroundColor(getResources().getColor(R.color.grey));
//                idVenderDetail.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseEntry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idnewPurchaseresturn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


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

