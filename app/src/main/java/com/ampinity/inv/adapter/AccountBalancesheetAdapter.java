package com.ampinity.inv.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
/*
import android.support.v7.app.AlertDialog;
*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ampinity.inv.Activity.UpdateAccountGroupActivity;
import com.ampinity.inv.Activity.UpdateAccountLedgerActivity;
import com.ampinity.inv.Model.AccountBalancesheetPojo;
import com.ampinity.inv.Model.AccountGroupPojo;
import com.ampinity.inv.Model.AccountLedgerPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

/**
 * Created by peritis1 on 2/16/2018.
 */


public class AccountBalancesheetAdapter extends BaseAdapter {

    private ArrayList<AccountBalancesheetPojo> arraylist;
    private Handler handler;

    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<AccountBalancesheetPojo> AccountBalancesheetPojos;
    ConnectionDetector cd;
    String id;
    String clickedid;

    public AccountBalancesheetAdapter(ArrayList<AccountBalancesheetPojo> AccountBalancesheetPojos, Context context) {

        this.AccountBalancesheetPojos = AccountBalancesheetPojos;
        this.arraylist = new ArrayList<AccountBalancesheetPojo>();
        this.arraylist.addAll(AccountBalancesheetPojos);
        this.context = context;

        handler = new Handler();


    }

    @Override
    public int getCount() {
        return AccountBalancesheetPojos.size();
    }

    @Override
    public Object getItem(int position) {
        return AccountBalancesheetPojos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return AccountBalancesheetPojos.indexOf(AccountBalancesheetPojos.get(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        cd = new ConnectionDetector(context);
        final AccountBalancesheetPojo accountBalancesheetPojoo = AccountBalancesheetPojos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row_account_ledger, null);
            holder = new AccountBalancesheetAdapter.ViewHolder();


            //intitalize
            //  holder.idTextViewCName = (TextView) convertView.findViewById(R.id.textViewCName);
//            holder = new ViewHolder();
//            holder.mSNo = (TextView) convertView.findViewById(R.id.sNo);
//            holder.mProduct = (TextView) convertView.findViewById(R.id.product);
//            holder.mCategory = (TextView) convertView
//                    .findViewById(R.id.category);
            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);


            convertView.setTag(holder);
        } else {
            holder = (AccountBalancesheetAdapter.ViewHolder) convertView.getTag();
        }

        // String Name = CompanyPojos.get(position).getComapanyName();
        //use position vise
//        id = TableDataPojos.get(position).getTableid();
//        String Name = TableDataPojos.get(position).getLobName();
//        String attribute = TableDataPojos.get(position).getTotalAttribute();
//
//
//        holder.mSNo.setText(id);
//        holder.mProduct.setText(Name);
//        holder.mCategory.setText(attribute);


        final ViewHolder finalHolder = holder;

        //  perform onclick
        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, UpdateAccountLedgerActivity.class);
                //intent.putExtra("Lobid", TableDataPojos.get(position).getTableid());
                // intent.putExtra("LobName",TableDataPojos.get(position).getLobName());
                // intent.putExtra("TotalAttribute",TableDataPojos.get(position).getTotalAttribute());
                context.startActivity(intent);

            }
        });

//        holder.idimageviewdeletered.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (cd.isConnectingToInternet()) {
//                    alertMessage();
//                    clickedid=TableDataPojos.get(position).getTableid();
//                    Log.e("clickedid", "clickedid: "+clickedid );
//                }
//                else {
//                    Toast.makeText(context, "please connect to internet", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });


        return convertView;
    }


    public static class ViewHolder {
        TextView mSNo;
        TextView mProduct;
        TextView mCategory;

        ImageView idimageviewedit,idimageviewdeletered;


    }








}

