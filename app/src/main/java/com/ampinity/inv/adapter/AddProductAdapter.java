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

import com.ampinity.inv.Model.AddProductPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Activity.UpdateProductItemActivity;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

/**
 * Created by peritis1 on 2/22/2018.
 */

public class AddProductAdapter extends BaseAdapter {

    private ArrayList<AddProductPojo> arraylist;
    private Handler handler;

    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<AddProductPojo> AddProductPojos;
    ConnectionDetector cd;
    String id;
    String clickedid;
    String ID,PID,Name,ItemCode,HSNSACCode,lable1,lable2,lable3,lable4,lable5,lable6,lable7,lable8,PurchasePrice,per,SalePrice,SaleDesPer,Quantity,Gst,LobName,SGSTPERItem,SGSTAMTItem,IGSTPERItem,IGSTAMTItem,CGSTPERItem,CGSTAMTItem,SubPriceItem;


    public AddProductAdapter(ArrayList<AddProductPojo> AddProductPojos, Context context) {

        this.AddProductPojos = AddProductPojos;
        this.arraylist = new ArrayList<AddProductPojo>();
        this.arraylist.addAll(AddProductPojos);
        this.context = context;

        handler = new Handler();


    }

    @Override
    public int getCount() {
        return AddProductPojos.size();
    }

    @Override
    public Object getItem(int position) {
        return AddProductPojos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return AddProductPojos.indexOf(AddProductPojos.get(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        cd = new ConnectionDetector(context);
        final AddProductPojo addProductPojo = AddProductPojos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row_add_product, null);
            holder = new AddProductAdapter.ViewHolder();


            //intitalize

            holder. txtItemID= (TextView) convertView.findViewById(R.id.ItemID);
            holder.  txtItemName= (TextView) convertView.findViewById(R.id.ItemName);
            holder.txtItemCode= (TextView) convertView.findViewById(R.id.ItemCode);
            holder.  txtHSNCode= (TextView) convertView.findViewById(R.id.HSNCode);
            holder. txtPrice= (TextView) convertView.findViewById(R.id.Price);
            holder.  txtPer= (TextView) convertView.findViewById(R.id.Per);
            holder. txtSalesPrice= (TextView) convertView.findViewById(R.id.SalesPrice);
            holder. txtSaleDesc= (TextView) convertView.findViewById(R.id.SaleDesc);
            holder. txtQty= (TextView) convertView.findViewById(R.id.Qty);
            holder. txtGST= (TextView) convertView.findViewById(R.id.GST);
            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);


            convertView.setTag(holder);
        } else {
            holder = (AddProductAdapter.ViewHolder) convertView.getTag();
        }

        // String Name = CompanyPojos.get(position).getComapanyName();
        //use position vise

        ID=AddProductPojos.get(position).getID();
        PID=AddProductPojos.get(position).getPID();
        Name=AddProductPojos.get(position).getName();
        ItemCode=AddProductPojos.get(position).getItemCode();
        HSNSACCode=AddProductPojos.get(position).getHSNSACCode();
        lable1=AddProductPojos.get(position).getLable1();
        lable2=AddProductPojos.get(position).getLable2();
        lable3=AddProductPojos.get(position).getLable3();
        lable4=AddProductPojos.get(position).getLable4();
        lable5=AddProductPojos.get(position).getLable5();
        lable6=AddProductPojos.get(position).getLable6();
        lable7=AddProductPojos.get(position).getLable7();
        lable8=AddProductPojos.get(position).getLable8();
        PurchasePrice=AddProductPojos.get(position).getPurchasePrice();
        per=AddProductPojos.get(position).getPer();
        SalePrice=AddProductPojos.get(position).getSalePrice();
        SaleDesPer=AddProductPojos.get(position).getSaleDesPer();
        Quantity=AddProductPojos.get(position).getQuantity();
        Gst=AddProductPojos.get(position).getGst();
      //  LobName=AddProductPojos.get(position).getLobName();
        SGSTPERItem=AddProductPojos.get(position).getSGSTPERItem();
        SGSTAMTItem=AddProductPojos.get(position).getSGSTAMTItem();
        IGSTPERItem=AddProductPojos.get(position).getIGSTPERItem();
        IGSTAMTItem=AddProductPojos.get(position).getIGSTAMTItem();
        CGSTPERItem=AddProductPojos.get(position).getCGSTPERItem();
        CGSTAMTItem=AddProductPojos.get(position).getCGSTAMTItem();
        SubPriceItem=AddProductPojos.get(position).getSubPriceItem();


//        id = TableDataPojos.get(position).getTableid();
//        String Name = TableDataPojos.get(position).getLobName();
//        String attribute = TableDataPojos.get(position).getTotalAttribute();
//
//

//        holder.mSNo.setText(id);
//        holder.mProduct.setText(Name);
//        holder.mCategory.setText(attribute);
        holder.txtItemID.setText(ID);
        holder.txtItemName.setText(Name);
        holder.txtItemCode.setText(ItemCode);
        holder.txtHSNCode.setText(HSNSACCode);
        holder.txtPrice.setText(PurchasePrice);
        holder.txtPer.setText(per);
        holder.txtSalesPrice.setText(SalePrice);
        holder.txtSaleDesc.setText(SaleDesPer);
        holder.txtQty.setText(Quantity);
        holder.txtGST.setText(Gst);
        holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
        holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);

        final ViewHolder finalHolder = holder;

        //  perform onclick
        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, UpdateProductItemActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("ID",AddProductPojos.get(position).getID());
                intent.putExtra("PID",AddProductPojos.get(position).getPID());
                intent.putExtra("Name",AddProductPojos.get(position).getName());
                intent.putExtra("ItemCode",AddProductPojos.get(position).getItemCode());
                intent.putExtra("HSNSACCode",AddProductPojos.get(position).getHSNSACCode());
                intent.putExtra("lable1",AddProductPojos.get(position).getLable1());
                intent.putExtra("lable2",AddProductPojos.get(position).getLable2());
                intent.putExtra("lable3",AddProductPojos.get(position).getLable3());
                intent.putExtra("lable4",AddProductPojos.get(position).getLable4());
                intent.putExtra("lable5",AddProductPojos.get(position).getLable5());
                intent.putExtra("lable6",AddProductPojos.get(position).getLable6());
                intent.putExtra("lable7",AddProductPojos.get(position).getLable7());
                intent.putExtra("lable8",AddProductPojos.get(position).getLable8());
                intent.putExtra("PurchasePrice",AddProductPojos.get(position).getPurchasePrice());
                intent.putExtra("per",AddProductPojos.get(position).getPer());
                intent.putExtra("SalePrice",AddProductPojos.get(position).getSalePrice());
                intent.putExtra("SaleDesPer",AddProductPojos.get(position).getSaleDesPer());
                intent.putExtra("Quantity",AddProductPojos.get(position).getQuantity());
                intent.putExtra("Gst",AddProductPojos.get(position).getGst());
                //  LobName=AddProductPojos.get(position).getLobName();
                intent.putExtra("SGSTPERItem",AddProductPojos.get(position).getSGSTPERItem());
                intent.putExtra("SGSTAMTItem",AddProductPojos.get(position).getSGSTAMTItem());
                intent.putExtra("IGSTPERItem",AddProductPojos.get(position).getIGSTPERItem());
                intent.putExtra("IGSTAMTItem",AddProductPojos.get(position).getIGSTAMTItem());
                intent.putExtra("CGSTPERItem",AddProductPojos.get(position).getCGSTPERItem());
                intent.putExtra("CGSTAMTItem",AddProductPojos.get(position).getCGSTAMTItem());
                intent.putExtra("SubPriceItem",AddProductPojos.get(position).getSubPriceItem());


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
        TextView txtItemID,txtItemName,txtItemCode,txtHSNCode,txtPrice,txtPer,txtSalesPrice,txtSaleDesc,txtQty,txtGST;

        ImageView idimageviewedit,idimageviewdeletered;


    }








}
