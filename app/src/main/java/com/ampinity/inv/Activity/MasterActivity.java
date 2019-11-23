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
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ampinity.inv.R;
import com.ampinity.inv.fragment.ComingSoon;
import com.ampinity.inv.fragment.MasterAccountGroupfragment;
import com.ampinity.inv.fragment.MasterAccountLedgerfragment;
import com.ampinity.inv.fragment.MasterAttributefragment;
import com.ampinity.inv.fragment.MasterBankfragment;
import com.ampinity.inv.fragment.MasterEmployeeRegifragment;
import com.ampinity.inv.fragment.MasterGstfragment;
import com.ampinity.inv.fragment.MasterLobfragment;
import com.ampinity.inv.fragment.MasterTaxGroupfragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MasterActivity extends AppCompatActivity {
    LinearLayout idLob,idAttribute,idGst,idBank,idTaxGroup,idAccountGroup,idAccountLedger,idEmployeeRegistration;
    LinearLayout idLobbottom,idAttributebottom,idGstbottom,idBankbottom,idTaxGroupbottom,idAccountGroupbottom,idAccountLedgerbottom,idEmployeeRegBottom;
    HorizontalScrollView horizontalScrollView;
    int mWidth;
    int mHeigh;
    LayoutParams params,params1,taxgroupLL,gstLL,bankLL;
    int viewWidth;
    int viewHeigh;
    ArrayList<LinearLayout> layouts;
    int backButtonCount=0;
    TextView txtlob,txtattridetails,txttaxgroup,txtgst,txtbank,txtaccgroup,txtaccledger,txtempregi;
    String lob,strdetails,strtaxgroup,strgst,strbank,straccgroup,straccledger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Master");


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("LOB"));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#FF000000"));
        tabLayout.addTab(tabLayout.newTab().setText("Attribute Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Tax Group"));
        tabLayout.addTab(tabLayout.newTab().setText("GST"));
        tabLayout.addTab(tabLayout.newTab().setText("Bank"));
        tabLayout.addTab(tabLayout.newTab().setText("Account Group"));
        tabLayout.addTab(tabLayout.newTab().setText("Account Ledger"));
        tabLayout.addTab(tabLayout.newTab().setText("Employee Registration"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final Page1Adapter adapter = new Page1Adapter
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



        /*MasterLobfragment fragment = new MasterLobfragment();
        android.app.FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content_framemaster,fragment);
        transaction.addToBackStack(null);
        transaction.commit();



        txtlob=(TextView) findViewById(R.id.txtlob);
        txtlob.setTextColor(0xFF000000);
        txtattridetails=(TextView) findViewById(R.id.viewdetails);
        txttaxgroup=(TextView) findViewById(R.id.viewtxtgroup);
        txtgst=(TextView) findViewById(R.id.viewgst);
        txtbank=(TextView) findViewById(R.id.viewbank);
        txtaccgroup=(TextView) findViewById(R.id.viewacctgroup);
        txtaccledger=(TextView) findViewById(R.id.viewacctledger);
        txtempregi=(TextView) findViewById(R.id.viewempregi);

        //lob=txtlob.getText().toString();
        idAttribute=(LinearLayout) findViewById(R.id.idAttribute);
        idLob=(LinearLayout) findViewById(R.id.LOB);
        idGst=(LinearLayout) findViewById(R.id.idGst);
        idBank=(LinearLayout) findViewById(R.id.idBank);
        idTaxGroup=(LinearLayout) findViewById(R.id.idTaxGroup);
        idLobbottom=(LinearLayout) findViewById(R.id.LOBbottom);
        idAttributebottom=(LinearLayout) findViewById(R.id.idAttributebottom);
        idGstbottom=(LinearLayout) findViewById(R.id.idGstbottom);
        idBankbottom=(LinearLayout) findViewById(R.id.idBankbottom);
        idTaxGroupbottom=(LinearLayout) findViewById(R.id.idTaxGroupbottom);
        idAccountGroup=(LinearLayout) findViewById(R.id.idAccountGroup);
        idAccountLedger=(LinearLayout) findViewById(R.id.idAccountLedger);
        idAccountGroupbottom=(LinearLayout) findViewById(R.id.idAccountGroupbottom);
        idAccountLedgerbottom=(LinearLayout) findViewById(R.id.idAccountLedgerbottom);
        idEmployeeRegistration=(LinearLayout) findViewById(R.id.idEmployeeRegistration);
        idEmployeeRegBottom=(LinearLayout) findViewById(R.id.idEmployeeRegBottom);
        horizontalScrollView=(HorizontalScrollView) findViewById(R.id.hsv);


        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth(); // deprecated
        viewWidth = mWidth /3;
        mHeigh = display.getHeight(); // deprecated
        viewHeigh = mHeigh / 8;

        layouts = new ArrayList<LinearLayout>();
        params = new LinearLayout.LayoutParams(viewWidth, LayoutParams.MATCH_PARENT);
        params1 = new LinearLayout.LayoutParams(100, LinearLayout.LayoutParams.MATCH_PARENT);
        taxgroupLL=new LinearLayout.LayoutParams(200, LayoutParams.MATCH_PARENT);
        gstLL=new LinearLayout.LayoutParams(100, LayoutParams.MATCH_PARENT);
        bankLL=new LinearLayout.LayoutParams(110, LayoutParams.MATCH_PARENT);


        idAttribute.setLayoutParams(params);
        idLob.setLayoutParams(params1);
        idGst.setLayoutParams(gstLL);
        idBank.setLayoutParams(bankLL);
        idTaxGroup.setLayoutParams(taxgroupLL);
        idAccountGroup.setLayoutParams(params);
        idAccountLedger.setLayoutParams(params);
        idEmployeeRegistration.setLayoutParams(params);
//        idAttribute.setLayoutParams(params1);
//        idLob.setLayoutParams(params1);
//        idGst.setLayoutParams(params1);
//        idBank.setLayoutParams(params1);
//        idTaxGroup.setLayoutParams(params1);
      //  horizontalScrollView.setLayoutParams(params1);




//        idLob.setBackgroundColor(getResources().getColor(R.color.grey));
//        idAttribute.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        idGst.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        idBank.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        idTaxGroup.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        idLobbottom.setVisibility(View.VISIBLE);
        idAttributebottom.setVisibility(View.GONE);
        idGstbottom.setVisibility(View.GONE);
        idBankbottom.setVisibility(View.GONE);
        idTaxGroupbottom.setVisibility(View.GONE);
        idAccountGroupbottom.setVisibility(View.GONE);
        idAccountLedgerbottom.setVisibility(View.GONE);
        idEmployeeRegBottom.setVisibility(View.GONE);

        idAccountGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                idTaxGroup.setBackgroundColor(getResources().getColor(R.color.grey));
//                idLob.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idAttribute.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idGst.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idBank.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                //#BB0404
                txtaccgroup.setTextColor(0xFF000000);//black
                txtattridetails.setTextColor(0xFFFFFFFF);//white
                txttaxgroup.setTextColor(0xFFFFFFFF);
                txtgst.setTextColor(0xFFFFFFFF);
                txtbank.setTextColor(0xFFFFFFFF);
                txtaccledger.setTextColor(0xFFFFFFFF);
                txtempregi.setTextColor(0xFFFFFFFF);
                txtlob.setTextColor(0xFFFFFFFF);

                idLobbottom.setVisibility(View.GONE);
                idAttributebottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);
                idBankbottom.setVisibility(View.GONE);
                idTaxGroupbottom.setVisibility(View.GONE);
                idAccountGroupbottom.setVisibility(View.VISIBLE);
                idAccountLedgerbottom.setVisibility(View.GONE);
                idEmployeeRegBottom.setVisibility(View.GONE);

                MasterAccountGroupfragment fragment = new MasterAccountGroupfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
                idAccountLedger.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                idTaxGroup.setBackgroundColor(getResources().getColor(R.color.grey));
//                idLob.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idAttribute.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idGst.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idBank.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        txtaccgroup.setTextColor(0xFFFFFFFF);//white
                        txtattridetails.setTextColor(0xFFFFFFFF);//
                        txttaxgroup.setTextColor(0xFFFFFFFF);
                        txtgst.setTextColor(0xFFFFFFFF);
                        txtbank.setTextColor(0xFFFFFFFF);
                        txtaccledger.setTextColor(0xFF000000);//black
                        txtempregi.setTextColor(0xFFFFFFFF);
                        txtlob.setTextColor(0xFFFFFFFF);

                       // txtaccledger.setTextColor(0xFF000000);
                        idLobbottom.setVisibility(View.GONE);
                        idAttributebottom.setVisibility(View.GONE);
                        idGstbottom.setVisibility(View.GONE);
                        idBankbottom.setVisibility(View.GONE);
                        idTaxGroupbottom.setVisibility(View.GONE);
                        idAccountGroupbottom.setVisibility(View.GONE);
                        idAccountLedgerbottom.setVisibility(View.VISIBLE);
                        idEmployeeRegBottom.setVisibility(View.GONE);

                        MasterAccountLedgerfragment fragment = new MasterAccountLedgerfragment();
                        android.app.FragmentManager manager = getFragmentManager();
                        android.app.FragmentTransaction transaction = manager.beginTransaction();
                        transaction.add(R.id.content_framemaster,fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });


        idTaxGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                idTaxGroup.setBackgroundColor(getResources().getColor(R.color.grey));
//                idLob.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idAttribute.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idGst.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idBank.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                txtaccgroup.setTextColor(0xFFFFFFFF);//white
                txtattridetails.setTextColor(0xFFFFFFFF);//
                txttaxgroup.setTextColor(0xFF000000);//black
                txtgst.setTextColor(0xFFFFFFFF);
                txtbank.setTextColor(0xFFFFFFFF);
                txtaccledger.setTextColor(0xFFFFFFFF);
                txtempregi.setTextColor(0xFFFFFFFF);
                txtlob.setTextColor(0xFFFFFFFF);


                //txttaxgroup.setTextColor(0xFF000000);
                idLobbottom.setVisibility(View.GONE);
                idAttributebottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);
                idBankbottom.setVisibility(View.GONE);
                idTaxGroupbottom.setVisibility(View.VISIBLE);
                idAccountGroupbottom.setVisibility(View.GONE);
                idAccountLedgerbottom.setVisibility(View.GONE);
                idEmployeeRegBottom.setVisibility(View.GONE);

                MasterGstfragment fragment = new MasterGstfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        idLob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtaccgroup.setTextColor(0xFFFFFFFF);
                txtattridetails.setTextColor(0xFFFFFFFF);//
                txttaxgroup.setTextColor(0xFFFFFFFF);
                txtgst.setTextColor(0xFFFFFFFF);
                txtbank.setTextColor(0xFFFFFFFF);
                txtaccledger.setTextColor(0xFFFFFFFF);
                txtempregi.setTextColor(0xFFFFFFFF);
                txtlob.setTextColor(0xFF000000);

                idLobbottom.setVisibility(View.VISIBLE);
                idAttributebottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);
                idBankbottom.setVisibility(View.GONE);
                idTaxGroupbottom.setVisibility(View.GONE);
                idAccountGroupbottom.setVisibility(View.GONE);
                idAccountLedgerbottom.setVisibility(View.GONE);
                idEmployeeRegBottom.setVisibility(View.GONE);
//
//                idTaxGroup.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idLob.setBackgroundColor(getResources().getColor(R.color.grey));
//                idAttribute.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idGst.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idBank.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                MasterLobfragment fragment = new MasterLobfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        idAttribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtaccgroup.setTextColor(0xFFFFFFFF);

                txttaxgroup.setTextColor(0xFFFFFFFF);
                txtgst.setTextColor(0xFFFFFFFF);
                txtbank.setTextColor(0xFFFFFFFF);
                txtaccledger.setTextColor(0xFFFFFFFF);
                txtempregi.setTextColor(0xFFFFFFFF);
                txtlob.setTextColor(0xFF000000);

                txtattridetails.setTextColor(0xFF000000);
                idLobbottom.setVisibility(View.GONE);
                idAttributebottom.setVisibility(View.VISIBLE);
                idGstbottom.setVisibility(View.GONE);
                idBankbottom.setVisibility(View.GONE);
                idTaxGroupbottom.setVisibility(View.GONE);
                idAccountGroupbottom.setVisibility(View.GONE);
                idAccountLedgerbottom.setVisibility(View.GONE);
                idEmployeeRegBottom.setVisibility(View.GONE);
//                idTaxGroup.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idLob.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idAttribute.setBackgroundColor(getResources().getColor(R.color.grey));
//                idGst.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idBank.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                MasterAttributefragment fragment = new MasterAttributefragment();
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

                txtaccgroup.setTextColor(0xFFFFFFFF);
                txtattridetails.setTextColor(0xFFFFFFFF);//
                txttaxgroup.setTextColor(0xFFFFFFFF);

                txtbank.setTextColor(0xFFFFFFFF);
                txtaccledger.setTextColor(0xFFFFFFFF);
                txtempregi.setTextColor(0xFFFFFFFF);
                txtlob.setTextColor(0xFFFFFFFF);

                txtgst.setTextColor(0xFF000000);
                idLobbottom.setVisibility(View.GONE);
                idAttributebottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.VISIBLE);
                idBankbottom.setVisibility(View.GONE);
                idTaxGroupbottom.setVisibility(View.GONE);
                idAccountGroupbottom.setVisibility(View.GONE);
                idAccountLedgerbottom.setVisibility(View.GONE);
                idEmployeeRegBottom.setVisibility(View.GONE);
//                idTaxGroup.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idLob.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idAttribute.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idGst.setBackgroundColor(getResources().getColor(R.color.grey));
//                idBank.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                MasterTaxGroupfragment fragment = new MasterTaxGroupfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        idBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtaccgroup.setTextColor(0xFFFFFFFF);
                txtattridetails.setTextColor(0xFFFFFFFF);//
                txttaxgroup.setTextColor(0xFFFFFFFF);
                txtgst.setTextColor(0xFFFFFFFF);

                txtaccledger.setTextColor(0xFFFFFFFF);
                txtempregi.setTextColor(0xFFFFFFFF);
                txtlob.setTextColor(0xFFFFFFFF);


                txtbank.setTextColor(0xFF000000);
                idLobbottom.setVisibility(View.GONE);
                idAttributebottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);
                idBankbottom.setVisibility(View.VISIBLE);
                idTaxGroupbottom.setVisibility(View.GONE);
                idAccountGroupbottom.setVisibility(View.GONE);
                idAccountLedgerbottom.setVisibility(View.GONE);
                idEmployeeRegBottom.setVisibility(View.GONE);
//                idTaxGroup.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idLob.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idAttribute.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idGst.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idBank.setBackgroundColor(getResources().getColor(R.color.grey));

                MasterBankfragment fragment = new MasterBankfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        idEmployeeRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtaccgroup.setTextColor(0xFFFFFFFF);
                txtattridetails.setTextColor(0xFFFFFFFF);//
                txttaxgroup.setTextColor(0xFFFFFFFF);
                txtgst.setTextColor(0xFFFFFFFF);
                txtbank.setTextColor(0xFFFFFFFF);
                txtaccledger.setTextColor(0xFFFFFFFF);

                txtlob.setTextColor(0xFFFFFFFF);


                txtempregi.setTextColor(0xFF000000);
                idLobbottom.setVisibility(View.GONE);
                idAttributebottom.setVisibility(View.GONE);
                idGstbottom.setVisibility(View.GONE);
                idBankbottom.setVisibility(View.GONE);
                idTaxGroupbottom.setVisibility(View.GONE);
                idAccountGroupbottom.setVisibility(View.GONE);
                idAccountLedgerbottom.setVisibility(View.GONE);
                idEmployeeRegBottom.setVisibility(View.VISIBLE);
//                idTaxGroup.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idLob.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idAttribute.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idGst.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                idBank.setBackgroundColor(getResources().getColor(R.color.grey));

                MasterEmployeeRegifragment fragment = new MasterEmployeeRegifragment();
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
