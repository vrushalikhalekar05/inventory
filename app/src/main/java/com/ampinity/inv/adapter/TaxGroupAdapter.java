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
import com.ampinity.inv.Activity.UpdateTaxGroupActivity;
import com.ampinity.inv.Model.AttributeDetailPojo;
import com.ampinity.inv.Model.TaxGroupPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

/**
 * Created by peritis1 on 2/15/2018.
 */



public class TaxGroupAdapter extends BaseAdapter {

    private ArrayList<TaxGroupPojo> arraylist;
    private Handler handler;

    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<TaxGroupPojo> TaxGroupPojos;
    ConnectionDetector cd;
    String id;
    String clickedid,sidtaxgroup,staxgroup,srate,scgst,ssgst,sigst;

        public TaxGroupAdapter(ArrayList<TaxGroupPojo> TaxGroupPojos, Context context) {
        this.TaxGroupPojos = TaxGroupPojos;
        this.arraylist = new ArrayList<TaxGroupPojo>();
        this.arraylist.addAll(TaxGroupPojos);
        this.context = context;
        handler = new Handler();



    }
    @Override
    public int getCount() {

        return TaxGroupPojos.size();
    }
    @Override
    public Object getItem(int position) {

        return TaxGroupPojos.get(position);
    }
    @Override
    public long getItemId(int position) {

        return TaxGroupPojos.indexOf(TaxGroupPojos.get(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        cd = new ConnectionDetector(context);
        final TaxGroupPojo taxGroupPojo = TaxGroupPojos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listviewrowtaxgroup, null);

            holder = new TaxGroupAdapter.ViewHolder();

            // intitalize
           //   holder.idTextViewCName = (TextView) convertView.findViewById(R.id.textViewCName);

            holder = new ViewHolder();
            holder.idtaxgroup = (TextView) convertView.findViewById(R.id.idtaxgroup);
            holder.taxgroup = (TextView) convertView.findViewById(R.id.taxgroup);
            holder.rate = (TextView) convertView.findViewById(R.id.rate);
            holder.cgst = (TextView) convertView.findViewById(R.id.cgst);
            holder.sgst = (TextView) convertView.findViewById(R.id.sgst);
            holder.igst = (TextView) convertView.findViewById(R.id.igst);
            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);
            convertView.setTag(holder);

        } else {
            holder = (TaxGroupAdapter.ViewHolder) convertView.getTag();
        }
        sidtaxgroup=TaxGroupPojos.get(position).getTaxgroupid();
        staxgroup=TaxGroupPojos.get(position).getTaxgroupname();
        srate=TaxGroupPojos.get(position).getRate();
        scgst=TaxGroupPojos.get(position).getCgst();
        ssgst=TaxGroupPojos.get(position).getSgst();
        sigst=TaxGroupPojos.get(position).getIgst();
        //
//
//       holder.mSNo.setText(id);
//        holder.mProduct.setText(Name);
//        holder.mCategory.setText(attribute);

        holder.idtaxgroup.setText(sidtaxgroup);
        holder.taxgroup.setText(staxgroup);
        holder.rate.setText(srate);
        holder.cgst.setText(scgst);
        holder.sgst.setText(ssgst);
        holder.igst.setText(sigst);
        final ViewHolder finalHolder = holder;
        //  perform onclick
        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, UpdateTaxGroupActivity.class);
                 intent.putExtra("ID", TaxGroupPojos.get(position).getTaxgroupid());
                 intent.putExtra("Name",TaxGroupPojos.get(position).getTaxgroupname());
                 intent.putExtra("Rate",TaxGroupPojos.get(position).getRate());
                 intent.putExtra("CGST",TaxGroupPojos.get(position).getCgst());
                 intent.putExtra("SGST",TaxGroupPojos.get(position).getSgst());
                 intent.putExtra("IGST",TaxGroupPojos.get(position).getIgst());
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
        TextView idtaxgroup,taxgroup,rate,cgst,sgst,igst;
        TextView mProduct;
        TextView mCategory;
        ImageView idimageviewedit,idimageviewdeletered;
    }

}
