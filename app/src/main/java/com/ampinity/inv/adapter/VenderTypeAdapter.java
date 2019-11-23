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

import com.ampinity.inv.Model.VenderTypePojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Activity.UpdateVenderTypeActivity;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

/**
 * Created by peritis1 on 2/20/2018.
 */

public class VenderTypeAdapter extends BaseAdapter {

    private ArrayList<VenderTypePojo> arraylist;
    private Handler handler;

    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<VenderTypePojo> VenderTypePojos;
    ConnectionDetector cd;
    String id;
    String clickedid;

    public VenderTypeAdapter(ArrayList<VenderTypePojo> VenderTypePojos, Context context) {

        this.VenderTypePojos = VenderTypePojos;
        this.arraylist = new ArrayList<VenderTypePojo>();
        this.arraylist.addAll(VenderTypePojos);
        this.context = context;

        handler = new Handler();


    }

    @Override
    public int getCount() {
        return VenderTypePojos.size();
    }

    @Override
    public Object getItem(int position) {
        return VenderTypePojos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return VenderTypePojos.indexOf(VenderTypePojos.get(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        cd = new ConnectionDetector(context);
        final VenderTypePojo venderTypePojo = VenderTypePojos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row_vendertype, null);
            holder = new VenderTypeAdapter.ViewHolder();


            //intitalize
            //  holder.idTextViewCName = (TextView) convertView.findViewById(R.id.textViewCName);
            holder = new ViewHolder();
            holder.mSNo = (TextView) convertView.findViewById(R.id.ID);
            holder.mVenderGroup = (TextView) convertView.findViewById(R.id.VenderGroup);
            holder.mDescription = (TextView) convertView
                    .findViewById(R.id.Description);
            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);


            convertView.setTag(holder);
        } else {
            holder = (VenderTypeAdapter.ViewHolder) convertView.getTag();
        }

        // String Name = CompanyPojos.get(position).getComapanyName();
        //use position vise
        id = VenderTypePojos.get(position).getVenderTypeid();
        String vg = VenderTypePojos.get(position).getVenderGroup();
        String desc = VenderTypePojos.get(position).getDescription();


        holder.mSNo.setText(id);
        holder.mVenderGroup.setText(vg);
        holder.mDescription.setText(desc);


        final ViewHolder finalHolder = holder;

        // perform onclick
        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, UpdateVenderTypeActivity.class);
                intent.putExtra("id", VenderTypePojos.get(position).getVenderTypeid());
                intent.putExtra("VenderGroup",VenderTypePojos.get(position).getVenderGroup());
                intent.putExtra("Description",VenderTypePojos.get(position).getDescription());
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

        ImageView idimageviewedit,idimageviewdeletered;


    }








}
