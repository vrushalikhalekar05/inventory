package com.ampinity.inv.Activity;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ampinity.inv.R;
import com.ampinity.inv.fragment.AccountBalancesheetfragment;
import com.ampinity.inv.fragment.AccountBankBookfragment;
import com.ampinity.inv.fragment.AccountBankPaymentfragment;
import com.ampinity.inv.fragment.AccountCashPaymentfragment;
import com.ampinity.inv.fragment.AccountCreateLedgerfragment;
import com.ampinity.inv.fragment.AccountDayBookfragment;
import com.ampinity.inv.fragment.AccountJournalVoucherfragment;
import com.ampinity.inv.fragment.AccountLedgerBalancefragment;
import com.ampinity.inv.fragment.AccountProfitLossfragment;
import com.ampinity.inv.fragment.AccountRecieptBankfragment;
import com.ampinity.inv.fragment.AccountRecieptfragment;
import com.ampinity.inv.fragment.AccountTrialBalancefragment;
import com.ampinity.inv.fragment.MasterAccountGroupfragment;
import com.ampinity.inv.fragment.MasterBankfragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    HorizontalScrollView horizontalScrollView;
    int mWidth;
    int mHeigh;
    LinearLayout.LayoutParams params,params1;
    int viewWidth;
    int viewHeigh;
    ArrayList<LinearLayout> layouts;
    int backButtonCount=0;
    LinearLayout idCreateLedger ,idPayment,idBankPayment,idReceipt,idBankReceipt,idJournalVoucher,idLedgerBalance,idDayBook,idTrialBalance,idBalanceSheet,idBankBook,idProfitLoss;
    LinearLayout idCreateLedgerbottom,idPaymentbottom,idBankPaymentbottom,idReceiptbottom,idBankReceiptbottom,idJournalVoucherbottom,idLedgerBalancebottom,idDayBookbottom,idTrialBalancebottom,idBalanceSheetbottom,idBankBookbottom,idProfitLossbottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Account");

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#FF000000"));
        tabLayout.addTab(tabLayout.newTab().setText("Create Ledger"));
        tabLayout.addTab(tabLayout.newTab().setText("Cash Payment"));
        tabLayout.addTab(tabLayout.newTab().setText("Bank Payment"));
        tabLayout.addTab(tabLayout.newTab().setText("Cash Receipt"));
        tabLayout.addTab(tabLayout.newTab().setText("Bank Receipt"));
        tabLayout.addTab(tabLayout.newTab().setText("Journal Voucher"));
        tabLayout.addTab(tabLayout.newTab().setText("Ledger Balance"));
        tabLayout.addTab(tabLayout.newTab().setText("Day Book"));
        tabLayout.addTab(tabLayout.newTab().setText("Trial Balance"));
        tabLayout.addTab(tabLayout.newTab().setText("Balancesheet"));
        tabLayout.addTab(tabLayout.newTab().setText("BankBook"));
        tabLayout.addTab(tabLayout.newTab().setText("ProfitLoss"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final Page6Adapter adapter = new Page6Adapter
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

        /*idCreateLedger=(LinearLayout) findViewById(R.id.idCreateLedger);
        idPayment=(LinearLayout) findViewById(R.id.idPayment);
        idBankPayment=(LinearLayout) findViewById(R.id.idBankPayment);
        idReceipt=(LinearLayout) findViewById(R.id.idReceipt);
        idBankReceipt=(LinearLayout) findViewById(R.id.idBankReceipt);
        idJournalVoucher=(LinearLayout) findViewById(R.id.idJournalVoucher);
        idLedgerBalance=(LinearLayout) findViewById(R.id.idLedgerBalance);
        idDayBook=(LinearLayout) findViewById(R.id.idDayBook);
        idTrialBalance=(LinearLayout) findViewById(R.id.idTrialBalance);
        idBalanceSheet=(LinearLayout) findViewById(R.id.idBalanceSheet);
        idBankBook=(LinearLayout) findViewById(R.id.idBankBook);
        idProfitLoss=(LinearLayout) findViewById(R.id.idProfitLoss);

        idCreateLedgerbottom=(LinearLayout) findViewById(R.id.idCreateLedgerbottom);
        idPaymentbottom=(LinearLayout) findViewById(R.id.idPaymentbottom);
        idBankPaymentbottom=(LinearLayout) findViewById(R.id.idBankPaymentbottom);
        idReceiptbottom=(LinearLayout) findViewById(R.id.idReceiptbottom);
        idBankReceiptbottom=(LinearLayout) findViewById(R.id.idBankReceiptbottom);
        idJournalVoucherbottom=(LinearLayout) findViewById(R.id.idJournalVoucherbottom);
        idLedgerBalancebottom=(LinearLayout) findViewById(R.id.idLedgerBalancebottom);
        idDayBookbottom=(LinearLayout) findViewById(R.id.idDayBookbottom);
        idTrialBalancebottom=(LinearLayout) findViewById(R.id.idTrialBalancebottom);
        idBalanceSheetbottom=(LinearLayout) findViewById(R.id.idBalanceSheetbottom);
        idBankBookbottom=(LinearLayout) findViewById(R.id.idBankBookbottom);
        idProfitLossbottom=(LinearLayout) findViewById(R.id.idProfitLossbottom);
        horizontalScrollView=(HorizontalScrollView) findViewById(R.id.hsv);

        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth(); // deprecated
        viewWidth = mWidth / 3;
        mHeigh = display.getHeight(); // deprecated
        viewHeigh = mHeigh / 8;

        layouts = new ArrayList<LinearLayout>();
        params = new LinearLayout.LayoutParams(270, LinearLayout.LayoutParams.MATCH_PARENT);
        params1 = new LinearLayout.LayoutParams(viewHeigh, LinearLayout.LayoutParams.WRAP_CONTENT);

        idCreateLedger.setLayoutParams(params);
        idPayment.setLayoutParams(params);
        idBankPayment.setLayoutParams(params);

        idReceipt.setLayoutParams(params);
        idBankReceipt.setLayoutParams(params);
        idJournalVoucher.setLayoutParams(params);
        idLedgerBalance.setLayoutParams(params);
        idDayBook.setLayoutParams(params);
        idTrialBalance.setLayoutParams(params);
        idBalanceSheet.setLayoutParams(params);
        idBankBook.setLayoutParams(params);
        idProfitLoss.setLayoutParams(params);



        idCreateLedgerbottom .setVisibility(View.VISIBLE);
        idPaymentbottom.setVisibility(View.GONE);
        idBankPaymentbottom.setVisibility(View.GONE);
        idReceiptbottom.setVisibility(View.GONE);
        idBankReceiptbottom.setVisibility(View.GONE);
        idJournalVoucherbottom.setVisibility(View.GONE);
        idLedgerBalancebottom.setVisibility(View.GONE);
        idDayBookbottom.setVisibility(View.GONE);
        idTrialBalancebottom.setVisibility(View.GONE);
        idBalanceSheetbottom.setVisibility(View.GONE);
        idBankBookbottom.setVisibility(View.GONE);
        idProfitLossbottom.setVisibility(View.GONE);



        AccountCreateLedgerfragment fragment = new AccountCreateLedgerfragment();
        android.app.FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content_framemaster,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        idCreateLedger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idCreateLedgerbottom.setVisibility(View.VISIBLE);
                idPaymentbottom.setVisibility(View.GONE);
                idBankPaymentbottom.setVisibility(View.GONE);
                idReceiptbottom.setVisibility(View.GONE);
                idBankReceiptbottom.setVisibility(View.GONE);
                idJournalVoucherbottom.setVisibility(View.GONE);
                idLedgerBalancebottom.setVisibility(View.GONE);
                idDayBookbottom.setVisibility(View.GONE);
                idTrialBalancebottom.setVisibility(View.GONE);
                idBalanceSheetbottom.setVisibility(View.GONE);
                idBankBookbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);

                AccountCreateLedgerfragment fragment = new AccountCreateLedgerfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

                }


        });
        idPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idCreateLedgerbottom .setVisibility(View.GONE);
                idPaymentbottom.setVisibility(View.VISIBLE);
                idBankPaymentbottom.setVisibility(View.GONE);
                idReceiptbottom.setVisibility(View.GONE);
                idBankReceiptbottom.setVisibility(View.GONE);
                idJournalVoucherbottom.setVisibility(View.GONE);
                idLedgerBalancebottom.setVisibility(View.GONE);
                idDayBookbottom.setVisibility(View.GONE);
                idTrialBalancebottom.setVisibility(View.GONE);
                idBalanceSheetbottom.setVisibility(View.GONE);
                idBankBookbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);

                AccountCashPaymentfragment fragment = new AccountCashPaymentfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

                }
        });
        idBankPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idCreateLedgerbottom .setVisibility(View.GONE);
                idPaymentbottom.setVisibility(View.GONE);
                idBankPaymentbottom.setVisibility(View.VISIBLE);
                idReceiptbottom.setVisibility(View.GONE);
                idBankReceiptbottom.setVisibility(View.GONE);
                idJournalVoucherbottom.setVisibility(View.GONE);
                idLedgerBalancebottom.setVisibility(View.GONE);
                idDayBookbottom.setVisibility(View.GONE);
                idTrialBalancebottom.setVisibility(View.GONE);
                idBalanceSheetbottom.setVisibility(View.GONE);
                idBankBookbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);

                AccountBankPaymentfragment fragment = new AccountBankPaymentfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        idReceipt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                idCreateLedgerbottom .setVisibility(View.GONE);
                idPaymentbottom.setVisibility(View.GONE);
                idBankPaymentbottom.setVisibility(View.GONE);
                idReceiptbottom.setVisibility(View.VISIBLE);
                idBankReceiptbottom.setVisibility(View.GONE);
                idJournalVoucherbottom.setVisibility(View.GONE);
                idLedgerBalancebottom.setVisibility(View.GONE);
                idDayBookbottom.setVisibility(View.GONE);
                idTrialBalancebottom.setVisibility(View.GONE);
                idBalanceSheetbottom.setVisibility(View.GONE);
                idBankBookbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);

                AccountRecieptfragment fragment = new AccountRecieptfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        idBankReceipt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                idCreateLedgerbottom .setVisibility(View.GONE);
                idPaymentbottom.setVisibility(View.GONE);
                idBankPaymentbottom.setVisibility(View.GONE);
                idReceiptbottom.setVisibility(View.GONE);
                idBankReceiptbottom.setVisibility(View.VISIBLE);
                idJournalVoucherbottom.setVisibility(View.GONE);
                idLedgerBalancebottom.setVisibility(View.GONE);
                idDayBookbottom.setVisibility(View.GONE);
                idTrialBalancebottom.setVisibility(View.GONE);
                idBalanceSheetbottom.setVisibility(View.GONE);
                idBankBookbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);

                AccountRecieptBankfragment fragment = new AccountRecieptBankfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }


        });
        idJournalVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idCreateLedgerbottom .setVisibility(View.GONE);
                idPaymentbottom.setVisibility(View.GONE);
                idBankPaymentbottom.setVisibility(View.GONE);
                idReceiptbottom.setVisibility(View.GONE);
                idBankReceiptbottom.setVisibility(View.GONE);
                idJournalVoucherbottom.setVisibility(View.VISIBLE);
                idLedgerBalancebottom.setVisibility(View.GONE);
                idDayBookbottom.setVisibility(View.GONE);
                idTrialBalancebottom.setVisibility(View.GONE);
                idBalanceSheetbottom.setVisibility(View.GONE);
                idBankBookbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);

                AccountJournalVoucherfragment fragment = new AccountJournalVoucherfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

                }
        });
        idLedgerBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idCreateLedgerbottom.setVisibility(View.GONE);
                idPaymentbottom.setVisibility(View.GONE);
                idBankPaymentbottom.setVisibility(View.GONE);
                idReceiptbottom.setVisibility(View.GONE);
                idBankReceiptbottom.setVisibility(View.GONE);
                idJournalVoucherbottom.setVisibility(View.GONE);
                idLedgerBalancebottom.setVisibility(View.VISIBLE);
                idDayBookbottom.setVisibility(View.GONE);
                idTrialBalancebottom.setVisibility(View.GONE);
                idBalanceSheetbottom.setVisibility(View.GONE);
                idBankBookbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);

                AccountLedgerBalancefragment fragment = new AccountLedgerBalancefragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }

            });
        idDayBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idCreateLedgerbottom.setVisibility(View.GONE);
                idPaymentbottom.setVisibility(View.GONE);
                idBankPaymentbottom.setVisibility(View.GONE);
                idReceiptbottom.setVisibility(View.GONE);
                idBankReceiptbottom.setVisibility(View.GONE);
                idJournalVoucherbottom.setVisibility(View.GONE);
                idLedgerBalancebottom.setVisibility(View.GONE);
                idDayBookbottom.setVisibility(View.VISIBLE);
                idTrialBalancebottom.setVisibility(View.GONE);
                idBalanceSheetbottom.setVisibility(View.GONE);
                idBankBookbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);

                AccountDayBookfragment fragment = new AccountDayBookfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }

        });
        idTrialBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idCreateLedgerbottom .setVisibility(View.GONE);
                idPaymentbottom.setVisibility(View.GONE);
                idBankPaymentbottom.setVisibility(View.GONE);
                idReceiptbottom.setVisibility(View.GONE);
                idBankReceiptbottom.setVisibility(View.GONE);
                idJournalVoucherbottom.setVisibility(View.GONE);
                idLedgerBalancebottom.setVisibility(View.GONE);
                idDayBookbottom.setVisibility(View.GONE);
                idTrialBalancebottom.setVisibility(View.VISIBLE);
                idBalanceSheetbottom.setVisibility(View.GONE);
                idBankBookbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);



                AccountTrialBalancefragment fragment = new AccountTrialBalancefragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        idBalanceSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idCreateLedgerbottom .setVisibility(View.GONE);
                idPaymentbottom.setVisibility(View.GONE);
                idBankPaymentbottom.setVisibility(View.GONE);
                idReceiptbottom.setVisibility(View.GONE);
                idBankReceiptbottom.setVisibility(View.GONE);
                idJournalVoucherbottom.setVisibility(View.GONE);
                idLedgerBalancebottom.setVisibility(View.GONE);
                idDayBookbottom.setVisibility(View.GONE);
                idTrialBalancebottom.setVisibility(View.GONE);
                idBalanceSheetbottom.setVisibility(View.VISIBLE);
                idBankBookbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.GONE);


                AccountBalancesheetfragment fragment = new AccountBalancesheetfragment();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.content_framemaster,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

                }

            });

        idBankBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idCreateLedgerbottom .setVisibility(View.GONE);
                idPaymentbottom.setVisibility(View.GONE);
                idBankPaymentbottom.setVisibility(View.GONE);
                idReceiptbottom.setVisibility(View.GONE);
                idBankReceiptbottom.setVisibility(View.GONE);
                idJournalVoucherbottom.setVisibility(View.GONE);
                idLedgerBalancebottom.setVisibility(View.GONE);
                idDayBookbottom.setVisibility(View.GONE);
                idTrialBalancebottom.setVisibility(View.GONE);
                idBalanceSheetbottom.setVisibility(View.GONE);
                idBankBookbottom.setVisibility(View.VISIBLE);
                idProfitLossbottom.setVisibility(View.GONE);

                AccountBankBookfragment fragment = new AccountBankBookfragment();
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

                idCreateLedgerbottom.setVisibility(View.GONE);
                idPaymentbottom.setVisibility(View.GONE);
                idBankPaymentbottom.setVisibility(View.GONE);
                idReceiptbottom.setVisibility(View.GONE);
                idBankReceiptbottom.setVisibility(View.GONE);
                idJournalVoucherbottom.setVisibility(View.GONE);
                idLedgerBalancebottom.setVisibility(View.GONE);
                idDayBookbottom.setVisibility(View.GONE);
                idTrialBalancebottom.setVisibility(View.GONE);
                idBalanceSheetbottom.setVisibility(View.GONE);
                idBankBookbottom.setVisibility(View.GONE);
                idProfitLossbottom.setVisibility(View.VISIBLE);

                AccountProfitLossfragment fragment = new AccountProfitLossfragment();
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
