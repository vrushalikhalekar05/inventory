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

import com.ampinity.inv.Activity.UpdatePurchaseEntryActivity;
import com.ampinity.inv.Activity.UpdateVenderDetailActivity;
import com.ampinity.inv.Model.PurchaseEntryPojo;
import com.ampinity.inv.Model.VenderDetailPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

/**
 * Created by peritis1 on 2/20/2018.
 */



public class PurchaseEntryAdapter extends BaseAdapter {

    private ArrayList<PurchaseEntryPojo> arraylist;
    private Handler handler;

    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList< PurchaseEntryPojo> PurchaseEntryPojos;
    ConnectionDetector cd;

    String strvendername,strPurchaseEntryName,strSupplierBillNo,strDate,strIDPurchaseEntry,strInWord,strPaidAmount,strRemainingAmount,strTotalSub,strDiscount,strTotalAmount,strDatePaid;


    String clickedid;

    public PurchaseEntryAdapter(ArrayList<PurchaseEntryPojo> PurchaseEntryPojos, Context context) {

        this.PurchaseEntryPojos = PurchaseEntryPojos;
        this.arraylist = new ArrayList<PurchaseEntryPojo>();
        this.arraylist.addAll(PurchaseEntryPojos);
        this.context = context;

        handler = new Handler();


    }

    @Override
    public int getCount() {
        return PurchaseEntryPojos.size();
    }

    @Override
    public Object getItem(int position) {
        return PurchaseEntryPojos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return PurchaseEntryPojos.indexOf(PurchaseEntryPojos.get(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        cd = new ConnectionDetector(context);
        final PurchaseEntryPojo purchaseEntryPojo = PurchaseEntryPojos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row_purchaseentry, null);
            holder = new PurchaseEntryAdapter.ViewHolder();


            //intitalize
            //  holder.idTextViewCName = (TextView) convertView.findViewById(R.id.textViewCName);
            holder = new ViewHolder();


            holder.vendername= (TextView) convertView.findViewById(R.id.VenderName);
           // holder.PurchaseEntryName = (TextView) convertView.findViewById(R.id.PurchaseEntryName);
            holder.SupplierBillNo= (TextView) convertView.findViewById(R.id.SupplierBillNo);
            holder.Date= (TextView) convertView.findViewById(R.id.Date);
            holder.IDPurchaseEntry= (TextView) convertView.findViewById(R.id.IDPurchaseEntry);
            holder.InWord= (TextView) convertView.findViewById(R.id.InWord);
            holder.PaidAmount= (TextView) convertView.findViewById(R.id.PaidAmount);
            holder.RemainingAmount= (TextView) convertView.findViewById(R.id.RemainingAmount);
            holder.TotalSub= (TextView) convertView.findViewById(R.id.TotalSub);
            holder.Discount= (TextView) convertView.findViewById(R.id.Discount);
            holder.TotalAmount= (TextView) convertView.findViewById(R.id.TotalAmount);
            holder.DatePaid= (TextView) convertView.findViewById(R.id.DatePaid);


            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);


            convertView.setTag(holder);
        } else {
            holder = (PurchaseEntryAdapter.ViewHolder) convertView.getTag();
        }

        strIDPurchaseEntry=PurchaseEntryPojos.get(position).getIDPurchaseEntry();
        strvendername=PurchaseEntryPojos.get(position).getVendername();
        strSupplierBillNo=PurchaseEntryPojos.get(position).getSupplierBillNo();
        strDate=PurchaseEntryPojos.get(position).getDate();
        strIDPurchaseEntry=PurchaseEntryPojos.get(position).getIDPurchaseEntry();
        strInWord=PurchaseEntryPojos.get(position).getPaidAmount();
        strPaidAmount=PurchaseEntryPojos.get(position).getPaidAmount();
        strRemainingAmount=PurchaseEntryPojos.get(position).getRemainingAmount();
        strTotalSub=PurchaseEntryPojos.get(position).getTotalSub();
        strDiscount=PurchaseEntryPojos.get(position).getDiscount();
        strTotalAmount=PurchaseEntryPojos.get(position).getTotalAmount();
        strDatePaid=PurchaseEntryPojos.get(position).getDatePaid();


        holder.vendername.setText(strvendername);
        // holder.PurchaseEntryName .setText(strId);
        holder.SupplierBillNo.setText(strSupplierBillNo);
        holder.Date.setText(strDate);
        holder.IDPurchaseEntry.setText(strIDPurchaseEntry);
        holder.InWord.setText(strInWord);
        holder.PaidAmount.setText(strPaidAmount);
        holder.RemainingAmount.setText(strRemainingAmount);
        holder.TotalSub.setText(strTotalSub);
        holder.Discount.setText(strDiscount);
        holder.TotalAmount.setText(strTotalAmount);
        holder.DatePaid.setText(strDatePaid);





        final ViewHolder finalHolder = holder;

        // perform onclick
        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, UpdatePurchaseEntryActivity.class);
                intent.putExtra("vendername",PurchaseEntryPojos.get(position).getVendername());
                intent.putExtra("paymentby",PurchaseEntryPojos.get(position).getPaymentBy());
                intent.putExtra("SupplierBillNo",PurchaseEntryPojos.get(position).getSupplierBillNo());
                intent.putExtra("Date",PurchaseEntryPojos.get(position).getDate());
                intent.putExtra("IDPurchaseEntry",PurchaseEntryPojos.get(position).getIDPurchaseEntry());
                intent.putExtra("InWord",PurchaseEntryPojos.get(position).getPaidAmount());
                intent.putExtra("PaidAmount",PurchaseEntryPojos.get(position).getPaidAmount());
                intent.putExtra("RemainingAmount",PurchaseEntryPojos.get(position).getRemainingAmount());
                intent.putExtra("TotalSub",PurchaseEntryPojos.get(position).getTotalSub());
                intent.putExtra("Discount",PurchaseEntryPojos.get(position).getDiscount());
                intent.putExtra("TotalAmount",PurchaseEntryPojos.get(position).getTotalAmount());
                intent.putExtra("DatePaid",PurchaseEntryPojos.get(position).getDatePaid());
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
        TextView mVenderGroup;
        TextView mDescription;
        TextView vendername,PurchaseEntryName,SupplierBillNo,Date,IDPurchaseEntry,InWord,PaidAmount,RemainingAmount,TotalSub,Discount,TotalAmount,DatePaid;

        ImageView idimageviewedit,idimageviewdeletered;


    }








}
