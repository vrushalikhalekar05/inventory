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
import com.ampinity.inv.Activity.UpdateGstActivity;
import com.ampinity.inv.Model.AttributeDetailPojo;
import com.ampinity.inv.Model.GstPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

/**
 * Created by peritis1 on 2/15/2018.
 */



public class GstAdapter extends BaseAdapter {

    private ArrayList<GstPojo> arraylist;
    private Handler handler;

    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<GstPojo> GstPojos;
    ConnectionDetector cd;
    String id;
    String clickedid;

    public GstAdapter(ArrayList<GstPojo> GstPojos, Context context) {

        this.GstPojos = GstPojos;
        this.arraylist = new ArrayList<GstPojo>();
        this.arraylist.addAll(GstPojos);
        this.context = context;

        handler = new Handler();


    }

    @Override
    public int getCount() {
        return GstPojos.size();
    }

    @Override
    public Object getItem(int position) {
        return GstPojos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return GstPojos.indexOf(GstPojos.get(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        cd = new ConnectionDetector(context);
        final GstPojo gstPojo = GstPojos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row_gst, null);
            holder = new GstAdapter.ViewHolder();


            //intitalize
            //  holder.idTextViewCName = (TextView) convertView.findViewById(R.id.textViewCName);
            holder = new ViewHolder();
            holder.idgst = (TextView) convertView.findViewById(R.id.idgst);
            holder.gst = (TextView) convertView.findViewById(R.id.gst);
            holder.rate = (TextView) convertView
                    .findViewById(R.id.rate);
            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);


            convertView.setTag(holder);
        } else {
            holder = (GstAdapter.ViewHolder) convertView.getTag();
        }

        // String Name = CompanyPojos.get(position).getComapanyName();
        //use position vise
        id = GstPojos.get(position).getGstid();
        String gsts = GstPojos.get(position).getGst();
        String rates = GstPojos.get(position).getRate();


        holder.idgst.setText(id);
        holder.gst.setText(gsts);
        holder.rate.setText(rates);


        final ViewHolder finalHolder = holder;

        //  perform onclick
        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, UpdateGstActivity.class);
                intent.putExtra("id",  GstPojos.get(position).getGstid());
                 intent.putExtra("gsts", GstPojos.get(position).getGst());
                 intent.putExtra("rate",GstPojos.get(position).getRate());
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
        TextView idgst;
        TextView rate;
        TextView gst;

        ImageView idimageviewedit,idimageviewdeletered;


    }








}
