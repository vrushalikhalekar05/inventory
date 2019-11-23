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
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ampinity.inv.R;
import com.ampinity.inv.fragment.PurchaseReturnfragment;
import com.ampinity.inv.fragment.PurchaseEntryfragment;
import com.ampinity.inv.fragment.PurchaseVenderDetailfragment;
import com.ampinity.inv.fragment.PurchaseVenderTypefragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PurchaseActivity extends AppCompatActivity {
    LinearLayout idVenderType,idVenderDetail,idPurchaseEntry,idPurchaseresturn,idnewPurchaseresturn;
    LinearLayout idVenderTypebottom,idVenderDetailbottom,idPurchaseEntrybottom,idPurchaseresturnbottom,idnewPurchaseresturnbottom;
    HorizontalScrollView horizontalScrollView;
    int mWidth;
    int mHeigh;
    LinearLayout.LayoutParams params,params1,params2,params3;
    int viewWidth;
    int viewHeigh;
    ArrayList<LinearLayout> layouts;
    int backButtonCount=0;

    TextView v1,v2,v3,v4,v5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_purchase);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Purchase");


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);


        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));

        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#FF000000"));
        tabLayout.addTab(tabLayout.newTab().setText("Vender Type"));
        tabLayout.addTab(tabLayout.newTab().setText("Vender Detail"));
        tabLayout.addTab(tabLayout.newTab().setText("Purchase Entry"));
        tabLayout.addTab(tabLayout.newTab().setText("Purchase Return"));
        tabLayout.addTab(tabLayout.newTab().setText("New Puchase Entry"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final Page4Adapter adapter = new Page4Adapter
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


        /*v1=(TextView) findViewById(R.id.v1);
        v2=(TextView) findViewById(R.id.v2);
        v3=(TextView) findViewById(R.id.v3);
        v4=(TextView) findViewById(R.id.v4);
        v5=(TextView) findViewById(R.id.v5);

        v1.setTextColor(0xFF000000);

        idVenderType= (LinearLayout)findViewById(R.id.idVenderType);
        idVenderDetail= (LinearLayout)findViewById(R.id.idVenderDetail);
        idPurchaseEntry= (LinearLayout)findViewById(R.id.idPurchaseEntry);
        idPurchaseresturn= (LinearLayout)findViewById(R.id.idPurchaseReturn);
        idnewPurchaseresturn= (LinearLayout)findViewById(R.id.idNewPurchaseEntry);
        idVenderTypebottom= (LinearLayout)findViewById(R.id.idVenderTypebottom);
        idVenderDetailbottom= (LinearLayout)findViewById(R.id.idVenderDetailbottom);
        idPurchaseEntrybottom= (LinearLayout)findViewById(R.id.idPurchaseEntrybottom);
        idPurchaseresturnbottom= (LinearLayout)findViewById(R.id.idPurchaseReturnbottom);
        idnewPurchaseresturnbottom= (LinearLayout)findViewById(R.id.idNewPurchaseEntrybottom);

        PurchaseVenderTypefragment fragment = new PurchaseVenderTypefragment();
        android.app.FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content_framemaster,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        idVenderTypebottom.setVisibility(View.VISIBLE);
        idVenderDetailbottom.setVisibility(View.GONE);
        idPurchaseEntrybottom.setVisibility(View.GONE);
        idPurchaseresturnbottom.setVisibility(View.GONE);
        idnewPurchaseresturnbottom.setVisibility(View.GONE);

        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth(); // deprecated
        viewWidth = mWidth / 3;
        mHeigh = display.getHeight(); // deprecated
        viewHeigh = mHeigh / 8;


        layouts = new ArrayList<LinearLayout>();

        params1 = new LinearLayout.LayoutParams(200, LinearLayout.LayoutParams.MATCH_PARENT);


        params2= new LinearLayout.LayoutParams(230, LinearLayout.LayoutParams.MATCH_PARENT);
        params3= new LinearLayout.LayoutParams(250, LinearLayout.LayoutParams.MATCH_PARENT);
        params = new LinearLayout.LayoutParams(280, LinearLayout.LayoutParams.MATCH_PARENT);

        idVenderType.setLayoutParams(params1);
        idVenderDetail.setLayoutParams(params2);
        idPurchaseEntry.setLayoutParams(params3);
        idPurchaseresturn.setLayoutParams(params3);
        idnewPurchaseresturn.setLayoutParams(params);


        idVenderType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v1.setTextColor(0xFF000000);
                v2.setTextColor(0xFFFFFFFF);
                v3.setTextColor(0xFFFFFFFF);
                v4.setTextColor(0xFFFFFFFF);
                v5.setTextColor(0xFFFFFFFF);

                idVenderTypebottom.setVisibility(View.VISIBLE);
                idVenderDetailbottom.setVisibility(View.GONE);
                idPurchaseEntrybottom.setVisibility(View.GONE);
                idPurchaseresturnbottom.setVisibility(View.GONE);
                idnewPurchaseresturnbottom.setVisibility(View.GONE);

                PurchaseVenderTypefragment fragment = new PurchaseVenderTypefragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }

        });

        idVenderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                v1.setTextColor(0xFFFFFFFF);
                v2.setTextColor(0xFF000000);
                v3.setTextColor(0xFFFFFFFF);
                v4.setTextColor(0xFFFFFFFF);
                v5.setTextColor(0xFFFFFFFF);

                idVenderTypebottom.setVisibility(View.GONE);
                idVenderDetailbottom.setVisibility(View.VISIBLE);
                idPurchaseEntrybottom.setVisibility(View.GONE);
                idPurchaseresturnbottom.setVisibility(View.GONE);
                idnewPurchaseresturnbottom.setVisibility(View.GONE);



                PurchaseVenderDetailfragment fragment = new PurchaseVenderDetailfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        idPurchaseEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v1.setTextColor(0xFFFFFFFF);
                v2.setTextColor(0xFFFFFFFF);
                v3.setTextColor(0xFF000000);
                v4.setTextColor(0xFFFFFFFF);
                v5.setTextColor(0xFFFFFFFF);

                idVenderTypebottom.setVisibility(View.GONE);
                idVenderDetailbottom.setVisibility(View.GONE);
                idPurchaseEntrybottom.setVisibility(View.VISIBLE);
                idPurchaseresturnbottom.setVisibility(View.GONE);
                idnewPurchaseresturnbottom.setVisibility(View.GONE);

                PurchaseEntryfragment fragment = new PurchaseEntryfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        idPurchaseresturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v1.setTextColor(0xFFFFFFFF);
                v2.setTextColor(0xFFFFFFFF);
                v3.setTextColor(0xFFFFFFFF);
                v4.setTextColor(0xFF000000);
                v5.setTextColor(0xFFFFFFFF);

                idVenderTypebottom.setVisibility(View.GONE);
                idVenderDetailbottom.setVisibility(View.GONE);
                idPurchaseEntrybottom.setVisibility(View.GONE);
                idPurchaseresturnbottom.setVisibility(View.VISIBLE);
                idnewPurchaseresturnbottom.setVisibility(View.GONE);

                PurchaseReturnfragment fragment = new PurchaseReturnfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        idnewPurchaseresturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v1.setTextColor(0xFFFFFFFF);
                v2.setTextColor(0xFFFFFFFF);
                v3.setTextColor(0xFFFFFFFF);
                v4.setTextColor(0xFFFFFFFF);
                v5.setTextColor(0xFF000000);

                idVenderTypebottom.setVisibility(View.GONE);
                idVenderDetailbottom.setVisibility(View.GONE);
                idPurchaseEntrybottom.setVisibility(View.GONE);
                idPurchaseresturnbottom.setVisibility(View.GONE);
                idnewPurchaseresturnbottom.setVisibility(View.VISIBLE);

//                MasterTaxGroupfragment fragment = new MasterTaxGroupfragment();
//                android.app.FragmentManager manager = getFragmentManager();
//                android.app.FragmentTransaction transaction = manager.beginTransaction();
//                transaction.add(R.id.content_framemaster,fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
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

