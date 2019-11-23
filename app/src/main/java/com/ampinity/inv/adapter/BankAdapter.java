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

import com.ampinity.inv.Activity.UpdateAttributedetailActivity;
import com.ampinity.inv.Activity.UpdateBankActivity;
import com.ampinity.inv.Model.AttributeDetailPojo;
import com.ampinity.inv.Model.BankPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

/**
 * Created by peritis1 on 2/15/2018.
 */






/**
 * Created by peritis1 on 2/13/2018.
 */

public class BankAdapter extends BaseAdapter {

    private ArrayList<BankPojo> arraylist;
    private Handler handler;

    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<BankPojo> BankPojos;
    ConnectionDetector cd;
    String id;
    String clickedid;

    public BankAdapter(ArrayList<BankPojo> BankPojos, Context context) {

        this.BankPojos = BankPojos;
        this.arraylist = new ArrayList<BankPojo>();
        this.arraylist.addAll(BankPojos);
        this.context = context;

        handler = new Handler();


    }

    @Override
    public int getCount() {
        return BankPojos.size();
    }

    @Override
    public Object getItem(int position) {
        return BankPojos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return BankPojos.indexOf(BankPojos.get(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        cd = new ConnectionDetector(context);
        final BankPojo bankPojo = BankPojos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row_bank, null);
            holder = new BankAdapter.ViewHolder();


            //intitalize

            holder = new ViewHolder();
              holder.idBank = (TextView) convertView.findViewById(R.id.idBank);
            holder.idBankName = (TextView) convertView.findViewById(R.id.idBankName);
            holder.idBranchName = (TextView) convertView.findViewById(R.id.idBranchName);
            holder.IFSCCode = (TextView) convertView.findViewById(R.id.IFSCCode);

            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);


            convertView.setTag(holder);
        } else {
            holder = (BankAdapter.ViewHolder) convertView.getTag();
        }

        // String Name = CompanyPojos.get(position).getComapanyName();
       // use position vise
        id = BankPojos.get(position).getBankid();
        String bankname = BankPojos.get(position).getBankName();
        String branchname = BankPojos.get(position).getBranchName();
        String ifsc = BankPojos.get(position).getIFSCCode();


        holder.idBank.setText(id);
        holder.idBankName.setText(bankname);
        holder.idBranchName.setText(branchname);
        holder.IFSCCode.setText(ifsc);

        final ViewHolder finalHolder = holder;

        //  perform onclick
        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, UpdateBankActivity.class);
                intent.putExtra("Bankid",BankPojos.get(position).getBankid());
                 intent.putExtra("BankName", BankPojos.get(position).getBankName());
                 intent.putExtra("BranchName", BankPojos.get(position).getBranchName());
                intent.putExtra("IFSC",BankPojos.get(position).getIFSCCode());
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
        TextView idBank;
        TextView idBankName;
        TextView idBranchName,IFSCCode;

        ImageView idimageviewedit,idimageviewdeletered;


    }








}