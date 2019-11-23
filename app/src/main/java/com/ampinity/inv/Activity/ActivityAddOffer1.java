package com.ampinity.inv.Activity;

import android.content.Intent;
import android.graphics.Color;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ampinity.inv.R;
import com.ampinity.inv.fragment.FragmentDateWiseDiscount;
import com.ampinity.inv.fragment.FragmentOffer1;
import com.ampinity.inv.fragment.FragmentOffer2;
import com.ampinity.inv.fragment.FragmentSuperDiscount;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ActivityAddOffer1 extends AppCompatActivity /*implements View.OnClickListener*/{
    LinearLayout idOffer1,idOffer2,idSuperDiscount,iddateWiseDiscount;
    LinearLayout idOffer1bottom,idOffer2bottom,idSuperDiscounttbottom,idDateWiseDiscountbottom;
    LinearLayout idVenderTypebottom,idVenderDetailbottom;
    Button offer1,offer2,superDiscount,datewiseDiscount;
    HorizontalScrollView horizontalScrollView;
    int mWidth;
    int mHeigh;
    LinearLayout.LayoutParams params,params1;
    int viewWidth;
    int viewHeigh;
    ArrayList<LinearLayout> layouts;
    int backButtonCount=0;
    private Button[] btn = new Button[4];
    private Button btn_unfocus;
    //private int[] btn_id = {R.id.offer1, R.id.offer2, R.id.superDiscount, R.id.datewiseDiscount};
    TextView txtOff1,txtOff2,txtOff3,txtOff4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Offer");

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);


        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#FF000000"));
        tabLayout.addTab(tabLayout.newTab().setText("Offer1"));
        tabLayout.addTab(tabLayout.newTab().setText("Offer2"));
        tabLayout.addTab(tabLayout.newTab().setText("Super Discount"));
        tabLayout.addTab(tabLayout.newTab().setText("Datewise Discount"));
        /*tabLayout.addTab(tabLayout.newTab().setText("Bank"));
        tabLayout.addTab(tabLayout.newTab().setText("Account Group"));
        tabLayout.addTab(tabLayout.newTab().setText("Account Ledger"));
        tabLayout.addTab(tabLayout.newTab().setText("Employee Registration"));*/
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final Page3Adapter adapter = new Page3Adapter
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



        /*txtOff1=(TextView) findViewById(R.id.txtOff1);
        txtOff2=(TextView) findViewById(R.id.txtOff2);
        txtOff3=(TextView) findViewById(R.id.txtOff3);
        txtOff4=(TextView) findViewById(R.id.txtOff4);

        txtOff1.setTextColor(0xFF000000);

        FragmentOffer1 fragment = new FragmentOffer1();
        android.app.FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
       transaction.add(R.id.content_frameOffer,fragment);
        transaction.addToBackStack(null);
        transaction.commit();



        *//*for(int i = 0; i < btn.length; i++){
            btn[i] = (Button) findViewById(btn_id[i]);
            btn[i].setBackgroundColor(Color.rgb(207, 207, 207));
            btn[i].setOnClickListener(this);
        }

        btn_unfocus = btn[0];*//*


       *//* offer1=(Button) findViewById(R.id.offer1);
        offer2=(Button) findViewById(R.id.offer2);
        superDiscount=(Button) findViewById(R.id.superDiscount);
        datewiseDiscount=(Button) findViewById(R.id.datewiseDiscount);
        idVenderTypebottom= (LinearLayout)findViewById(R.id.idVenderTypebottom);
        idVenderDetailbottom= (LinearLayout)findViewById(R.id.idVenderDetailbottom);
        idVenderTypebottom.setVisibility(View.VISIBLE);
       idVenderDetailbottom.setVisibility(View.GONE);

        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth(); // deprecated
        viewWidth = mWidth / 3;
        mHeigh = display.getHeight(); // deprecated
        viewHeigh = mHeigh / 8;*//*


        idOffer1= (LinearLayout) findViewById(R.id.Offer1);
        idOffer1bottom= (LinearLayout) findViewById(R.id.Offer1bottom);
        idOffer2= (LinearLayout) findViewById(R.id.Offer2);
        idOffer2bottom= (LinearLayout) findViewById(R.id.idOffer2bottom);
        idSuperDiscount= (LinearLayout) findViewById(R.id.idSuperDiscount);
        idSuperDiscounttbottom= (LinearLayout) findViewById(R.id.idSuperDiscountBottom);
        iddateWiseDiscount= (LinearLayout) findViewById(R.id.idDatewiseDiscount);
        idDateWiseDiscountbottom= (LinearLayout) findViewById(R.id.idDateWiseDiscountbottom);
       horizontalScrollView=(HorizontalScrollView) findViewById(R.id.hsv);


        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth();//deprecated
        System.out.println("mWidth"+mWidth);
        viewWidth = mWidth /2 ;
        System.out.println("viewWidth"+viewWidth);
        mHeigh = display.getHeight(); // deprecated
        viewHeigh = mHeigh / 5;

        layouts = new ArrayList<LinearLayout>();
        params = new LinearLayout.LayoutParams(250, LinearLayout.LayoutParams.MATCH_PARENT);
        params1 = new LinearLayout.LayoutParams(150, LinearLayout.LayoutParams.MATCH_PARENT);
        idOffer1.setLayoutParams(params1);
        idOffer2.setLayoutParams(params1);
        idSuperDiscount.setLayoutParams(params);
        iddateWiseDiscount.setLayoutParams(params);


        idOffer1bottom.setVisibility(View.VISIBLE);
        idOffer2bottom.setVisibility(View.GONE);
        idSuperDiscounttbottom.setVisibility(View.GONE);
        idDateWiseDiscountbottom.setVisibility(View.GONE);

        idOffer1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                txtOff1.setTextColor(0xFF000000);  //black
                txtOff2.setTextColor(0xFFFFFFFF);  //white
                txtOff3.setTextColor(0xFFFFFFFF);
                txtOff4.setTextColor(0xFFFFFFFF);

                idOffer1bottom.setVisibility(View.VISIBLE);
                idOffer2bottom.setVisibility(View.GONE);
                idSuperDiscounttbottom.setVisibility(View.GONE);
                idDateWiseDiscountbottom.setVisibility(View.GONE);

                  *//*idVenderTypebottom.setVisibility(View.VISIBLE);
                  idVenderDetailbottom.setVisibility(View.GONE);*//*
                FragmentOffer1 fragment = new FragmentOffer1();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_frameOffer,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
              }

        });
        idOffer2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  txtOff1.setTextColor(0xFFFFFFFF);
                  txtOff2.setTextColor(0xFF000000);
                  txtOff3.setTextColor(0xFFFFFFFF);
                  txtOff4.setTextColor(0xFFFFFFFF);

                  idOffer1bottom.setVisibility(View.GONE);
                idOffer2bottom.setVisibility(View.VISIBLE);
                idSuperDiscounttbottom.setVisibility(View.GONE);
                idDateWiseDiscountbottom.setVisibility(View.GONE);

                  *//*idVenderTypebottom.setVisibility(View.GONE);
                  idVenderDetailbottom.setVisibility(View.VISIBLE);*//*
                FragmentOffer2 fragment = new FragmentOffer2();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_frameOffer,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

              }
        });
        idSuperDiscount.setOnClickListener(new View.OnClickListener() {
             @Override
              public void onClick(View v) {

                 txtOff1.setTextColor(0xFFFFFFFF);
                 txtOff2.setTextColor(0xFFFFFFFF);
                 txtOff3.setTextColor(0xFF000000);
                 txtOff4.setTextColor(0xFFFFFFFF);

                idOffer1bottom.setVisibility(View.GONE);
                idOffer2bottom.setVisibility(View.GONE);
                idSuperDiscounttbottom.setVisibility(View.VISIBLE);
                idDateWiseDiscountbottom.setVisibility(View.GONE);

                FragmentSuperDiscount fragment = new FragmentSuperDiscount();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_frameOffer,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

             }

        });
        iddateWiseDiscount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    txtOff1.setTextColor(0xFFFFFFFF);
                    txtOff2.setTextColor(0xFFFFFFFF);
                    txtOff3.setTextColor(0xFFFFFFFF);
                    txtOff4.setTextColor(0xFF000000);

                idOffer1bottom.setVisibility(View.GONE);
                idOffer2bottom.setVisibility(View.GONE);
                idSuperDiscounttbottom.setVisibility(View.GONE);
                idDateWiseDiscountbottom.setVisibility(View.VISIBLE);

                FragmentDateWiseDiscount fragment = new FragmentDateWiseDiscount();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_frameOffer,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                }




                });*/


    }

  /*  @Override
    public void onClick(View v) {
        //setForcus(btn_unfocus, (Button) findViewById(v.getId()));
        //Or use switch
        switch (v.getId()){
            case R.id.offer1 :
                setFocus(btn_unfocus, btn[0]);

                FragmentOffer1 fragment = new FragmentOffer1();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_frameOffer,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.offer2 :
                setFocus(btn_unfocus, btn[1]);
                FragmentOffer2 fragment2 = new FragmentOffer2();
                android.app.FragmentManager manager2 = getFragmentManager();
                android.app.FragmentTransaction transaction2 = manager2.beginTransaction();
                transaction2.add(R.id.content_frameOffer,fragment2);
                transaction2.addToBackStack(null);
                transaction2.commit();
                break;

            case R.id.superDiscount :
                setFocus(btn_unfocus, btn[2]);
                FragmentSuperDiscount fragment3 = new FragmentSuperDiscount();
                android.app.FragmentManager manager3 = getFragmentManager();
                android.app.FragmentTransaction transaction3 = manager3.beginTransaction();
                transaction3.add(R.id.content_frameOffer,fragment3);
                transaction3.addToBackStack(null);
                transaction3.commit();
                break;

            case R.id.datewiseDiscount :
                setFocus(btn_unfocus, btn[3]);
                FragmentDateWiseDiscount fragment4 = new FragmentDateWiseDiscount();
                android.app.FragmentManager manager4 = getFragmentManager();
                android.app.FragmentTransaction transaction4 = manager4.beginTransaction();
                transaction4.add(R.id.content_frameOffer,fragment4);
                transaction4.addToBackStack(null);
                transaction4.commit();
                break;
        }

        }*/









    private void setFocus(Button btn_unfocus, Button btn_focus) {
        btn_unfocus.setTextColor(Color.rgb(49, 50, 51));
        btn_unfocus.setBackgroundColor(Color.rgb(207, 207, 207));
        btn_focus.setTextColor(Color.rgb(255, 255, 255));
        btn_focus.setBackgroundColor(Color.rgb(3, 106, 150));
        this.btn_unfocus = btn_focus;
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
