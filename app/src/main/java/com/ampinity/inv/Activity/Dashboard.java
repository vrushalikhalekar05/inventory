package com.ampinity.inv.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.SubMenu;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
/*import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;*/
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.CircleDisplay;
import com.ampinity.inv.Utility.CustomTypefaceSpan;
import com.ampinity.inv.Utility.SharedPreferencesConstants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;


public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CircleDisplay cd,cd1,cd2,cd4,cd5,cd6,cd7,cd8,cd9;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        cd= (CircleDisplay) findViewById(R.id.circleDisplay1);
        cd1= (CircleDisplay) findViewById(R.id.circleDisplay2);
        cd2= (CircleDisplay) findViewById(R.id.circleDisplay3);
        cd4= (CircleDisplay) findViewById(R.id.circleDisplay4);
        cd5= (CircleDisplay) findViewById(R.id.circleDisplay5);
        cd6= (CircleDisplay) findViewById(R.id.circleDisplay6);
        cd7= (CircleDisplay) findViewById(R.id.circleDisplay7);
        cd8= (CircleDisplay) findViewById(R.id.circleDisplay8);
        cd9= (CircleDisplay) findViewById(R.id.circleDisplay9);

        int myColor1 = getApplicationContext().getResources().getColor(R.color.green);
        int myColor2 = getApplicationContext().getResources().getColor(R.color.yellow);
        int myColor3 = getApplicationContext().getResources().getColor(R.color.violet);

       cd.setColor(myColor1);
        cd1.setColor(myColor2);
       cd2.setColor(myColor3);
        cd4.setColor(myColor1);
        cd5.setColor(myColor2);
        cd6.setColor(myColor3);
        cd7.setColor(myColor1);
        cd8.setColor(myColor2);
        cd9.setColor(myColor3);
       cd1.setDrawText(false);
       cd2.setDrawText(false);
       cd.setDrawText(false);
        cd4.setDrawText(false);
        cd5.setDrawText(false);
        cd6.setDrawText(false);
        cd7.setDrawText(false);
        cd8.setDrawText(false);
        cd9.setDrawText(false);

       cd.setStepSize((float) 0.5);
        cd2.setStepSize((float) 0.8);
       cd1.setStepSize((float) 0.2);
        cd4.setStepSize((float) 0.5);
        cd5.setStepSize((float) 0.8);
        cd6.setStepSize((float) 0.2);
        cd7.setStepSize((float) 0.5);
        cd8.setStepSize((float) 0.8);
        cd9.setStepSize((float) 0.2);

        cd.showValue(98.7f, 100f, true);
       cd1.showValue(0.88f, 100f, true);
        cd2.showValue(0f, 100f, true);
        cd4.showValue(98.7f, 100f, true);
        cd5.showValue(0.88f, 100f, true);
        cd6.showValue(0f, 100f, true);
        cd7.showValue(98.7f, 100f, true);
        cd8.showValue(0.88f, 100f, true);
        cd9.showValue(0f, 100f, true);

        cd2.setTouchEnabled(false);
        cd1.setTouchEnabled(false);
        cd.setTouchEnabled(false);
        cd4.setTouchEnabled(false);
        cd5.setTouchEnabled(false);
        cd6.setTouchEnabled(false);
        cd7.setTouchEnabled(false);
        cd8.setTouchEnabled(false);
        cd9.setTouchEnabled(false);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

     //   navView = (NavigationView) findViewById(R.id.navView);
        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Sales) {
             Intent intent = new Intent(Dashboard.this, SaleActivity.class);
              startActivity(intent);

        } else if (id == R.id.nav_purchase) {
            Intent intent = new Intent(Dashboard.this, PurchaseActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_dashboard) {

           Intent intent = new Intent(Dashboard.this, Dashboard.class);
            startActivity(intent);
            finish();

        }
        else if (id == R.id.nav_master) {

              Intent intent = new Intent(Dashboard.this, MasterActivity.class);
              startActivity(intent);


        }
        else if (id == R.id.nav_membershipcard) {

            Intent intent = new Intent(Dashboard.this,ActivityMembershipCard.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_Incentiverate) {

            Intent intent = new Intent(Dashboard.this,ActivityIncentiverate.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_RackMaster) {

            Intent intent = new Intent(Dashboard.this,ActivityRackmaster.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_employeeDetails) {

            Intent intent = new Intent(Dashboard.this,ActivityEmployeeAttendance.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_employeeAdvance) {

            Intent intent = new Intent(Dashboard.this,ActivityEmployeeAdvance.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_Offer) {

            Intent intent = new Intent(Dashboard.this, ActivityAddOffer1.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_account) {

            Intent intent = new Intent(Dashboard.this, AccountActivity.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_barcode) {

            Intent intent = new Intent(Dashboard.this, BarcodeActivity.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_help) {

            Intent intent = new Intent(Dashboard.this, HelpActivity.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_logout) {
            AlertDialog.Builder builder= new AlertDialog.Builder(Dashboard.this);

            builder.setTitle("Confirm");
            builder.setMessage("Are You Sure ??");
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Dashboard .this,Login.class));
                    pref = getApplicationContext().getSharedPreferences("MyPreference", Context.MODE_PRIVATE); // 0 - for private mode
                    editor = pref.edit();

             SharedPreferencesConstants.UserName="";


                    pref.edit().clear().apply();
                    editor.commit();
                    finish();

                    Toast.makeText(getApplicationContext(), "Logout Successfully", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton(android.R.string.no,null);
            builder.show();


        }
        else if (id == R.id.nav_stock) {

            Intent intent = new Intent(Dashboard.this, StockActivity.class);
            startActivity(intent);



        }
        else if (id == R.id.nav_setting) {

            Intent intent = new Intent(Dashboard.this, INVSettingsActivity.class);
            startActivity(intent);



        }
        else if (id == R.id.nav_report) {

            Intent intent = new Intent(Dashboard.this, INVReportActivity.class);
            startActivity(intent);



        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "OpenSans-Bold.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }
}
