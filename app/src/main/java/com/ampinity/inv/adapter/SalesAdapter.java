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
import com.ampinity.inv.Activity.UpdateSalesActivity;
import com.ampinity.inv.Model.AttributeDetailPojo;
import com.ampinity.inv.Model.BankPojo;
import com.ampinity.inv.Model.SalesPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

/**
 * Created by peritis1 on 2/15/2018.
 */






/**
 * Created by peritis1 on 2/13/2018.
 */

public class SalesAdapter extends BaseAdapter {

    private ArrayList<SalesPojo> arraylist;
    private Handler handler;

    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<SalesPojo> SalesPojos;
    ConnectionDetector cd;
    String id;
    String clickedid;
    String strSNO,strProductName,strRate,strQty,strDiscount,strCGST,strSGST,strIGST;

    public SalesAdapter(ArrayList<SalesPojo> SalesPojos, Context context) {

        this.SalesPojos = SalesPojos;
        this.arraylist = new ArrayList<SalesPojo>();
        this.arraylist.addAll(SalesPojos);
        this.context = context;

        handler = new Handler();


    }

    @Override
    public int getCount() {
        return SalesPojos.size();
    }

    @Override
    public Object getItem(int position) {
        return SalesPojos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return SalesPojos.indexOf(SalesPojos.get(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        cd = new ConnectionDetector(context);
        final SalesPojo salesPojo = SalesPojos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row_sales, null);
            holder = new SalesAdapter.ViewHolder();


            //intitalize

            holder = new ViewHolder();
            holder.txtSNO= (TextView) convertView.findViewById(R.id.SNO);
            holder.txtProductName= (TextView) convertView.findViewById(R.id.ProductName);
            holder.txtRate= (TextView) convertView.findViewById(R.id.Rate);
            holder.txtQty= (TextView) convertView.findViewById(R.id.Qty);
            holder.txtDiscount= (TextView) convertView.findViewById(R.id.Discount);
            holder.txtCGST= (TextView) convertView.findViewById(R.id.CGST);
            holder.txtSGST= (TextView) convertView.findViewById(R.id.SGST);
            holder.txtIGST= (TextView) convertView.findViewById(R.id.IGST);

//
            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);


            convertView.setTag(holder);
        } else {
            holder = (SalesAdapter.ViewHolder) convertView.getTag();
        }




        strSNO= SalesPojos.get(position).getNO();
        strProductName= SalesPojos.get(position).getProductName();
        strRate= SalesPojos.get(position).getRate();
        strQty= SalesPojos.get(position).getQty();
        strDiscount= SalesPojos.get(position).getDiscount();
        strCGST= SalesPojos.get(position).getCGST();
        strSGST= SalesPojos.get(position).getSGST();
        strIGST= SalesPojos.get(position).getIGST();




        holder.txtSNO.setText(strSNO);
        holder.txtProductName.setText(strProductName);
        holder.txtRate.setText(strRate);
        holder.txtQty.setText(strQty);
        holder.txtDiscount.setText(strDiscount);
        holder.txtCGST.setText(strCGST);
        holder.txtSGST.setText(strSGST);
        holder.txtIGST.setText(strIGST);

        final ViewHolder finalHolder = holder;

        //  perform onclick
        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, UpdateSalesActivity.class);
//                intent.putExtra("Bankid",BankPojos.get(position).getBankid());
//                 intent.putExtra("BankName", BankPojos.get(position).getBankName());
//                 intent.putExtra("BranchName", BankPojos.get(position).getBranchName());
//                intent.putExtra("IFSC",BankPojos.get(position).getIFSCCode());
                context.startActivity(intent);

            }
        });




        return convertView;
    }


    public static class ViewHolder {

        TextView txtSNO,txtProductName,txtRate,txtQty,txtDiscount,txtCGST,txtSGST,txtIGST;

        ImageView idimageviewedit,idimageviewdeletered;


    }








}