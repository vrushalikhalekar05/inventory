package com.ampinity.inv.Activity;

import android.content.Intent;
/*
import android.support.v7.app.AppCompatActivity;
*/
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ampinity.inv.R;
import com.google.android.material.tabs.TabLayout;

public class StockActivity extends AppCompatActivity {
    int backButtonCount=0;
    LinearLayout idSuppierwiseStockdetails,idStockdetails,idDatewiseStockdetails,idSuppierwiseStockdetailsbottom,idStockdetailsbottom,idDatewiseStockdetailsbottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stock);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Stock");


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Stock Details"));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#FF000000"));
        tabLayout.addTab(tabLayout.newTab().setText("Datewise Stock"));
        tabLayout.addTab(tabLayout.newTab().setText("Supplierwise"));
        /*tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 5"));*/
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PageAdapter adapter = new PageAdapter
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




        /*idSuppierwiseStockdetails=(LinearLayout)findViewById(R.id.idSuppierwiseStockdetails);
        idStockdetails=(LinearLayout)findViewById(R.id.idStockdetails);
        idDatewiseStockdetails=(LinearLayout)findViewById(R.id.idDatewiseStockdetails);
        idSuppierwiseStockdetailsbottom=(LinearLayout)findViewById(R.id.idSuppierwiseStockdetailsbottom);
        idStockdetailsbottom=(LinearLayout)findViewById(R.id.idStockdetailsbottom);
        idDatewiseStockdetailsbottom=(LinearLayout)findViewById(R.id.idDatewiseStockdetailsbottom);
        idStockdetailsbottom.setVisibility(View.VISIBLE);
        idDatewiseStockdetailsbottom.setVisibility(View.GONE);
        idSuppierwiseStockdetailsbottom.setVisibility(View.GONE);

        Stockdetailsfragment fragment = new Stockdetailsfragment();
        android.app.FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content_framemaster,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

                  idStockdetails.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                idStockdetailsbottom.setVisibility(View.VISIBLE);
                idDatewiseStockdetailsbottom.setVisibility(View.GONE);
                idSuppierwiseStockdetailsbottom.setVisibility(View.GONE);
                Stockdetailsfragment fragment = new Stockdetailsfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }

        });
                  idDatewiseStockdetails.setOnClickListener(new View.OnClickListener() {
                  @Override
                 public void onClick(View view) {
                      idStockdetailsbottom.setVisibility(View.GONE);
                 idDatewiseStockdetailsbottom.setVisibility(View.VISIBLE);
                 idSuppierwiseStockdetailsbottom.setVisibility(View.GONE);
                 DatewiseStockdetailsfragment fragment = new DatewiseStockdetailsfragment();
                 android.app.FragmentManager manager = getFragmentManager();
                 android.app.FragmentTransaction transaction = manager.beginTransaction();
                 transaction.add(R.id.content_framemaster,fragment);
                 transaction.addToBackStack(null);
                 transaction.commit();
                  }
                  });
                  idSuppierwiseStockdetails.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                  idStockdetailsbottom.setVisibility(View.GONE);
                 idDatewiseStockdetailsbottom.setVisibility(View.GONE);
                 idSuppierwiseStockdetailsbottom.setVisibility(View.VISIBLE);
                 SupplierwiseStockdetailsfragment fragment = new SupplierwiseStockdetailsfragment();
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

