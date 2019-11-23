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
import com.ampinity.inv.Activity.UpdateLobActivity;
import com.ampinity.inv.Model.AttributeDetailPojo;
import com.ampinity.inv.Model.TableDataPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

/**
 * Created by peritis1 on 2/13/2018.
 */

public class AttributeDetailAdapter extends BaseAdapter {

    private ArrayList<AttributeDetailPojo> AttributeDetailPojo;
    private Handler handler;
    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<AttributeDetailPojo> AttributeDetailPojos;
    ConnectionDetector cd;
    String id;
    String clickedid;

    public AttributeDetailAdapter(ArrayList<AttributeDetailPojo> AttributeDetailPojos, Context context) {

        this.AttributeDetailPojos = AttributeDetailPojos;
        this.AttributeDetailPojo = new ArrayList<AttributeDetailPojo>();
        this.AttributeDetailPojo.addAll(AttributeDetailPojos);
        this.context = context;
        handler = new Handler();

    }
    @Override
    public int getCount() {
        return AttributeDetailPojos.size();
    }
    @Override
    public Object getItem(int position) {
        return AttributeDetailPojos.get(position);
    }
    @Override
    public long getItemId(int position) {
        return AttributeDetailPojos.indexOf(AttributeDetailPojos.get(position));
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        cd = new ConnectionDetector(context);
        final AttributeDetailPojo attributeDetailaPojo = AttributeDetailPojos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row_attribute, null);
            holder = new AttributeDetailAdapter.ViewHolder();


            //intitalize
            //  holder.idTextViewCName = (TextView) convertView.findViewById(R.id.textViewCName);
//            holder = new ViewHolder();
//            holder.mSNo = (TextView) convertView.findViewById(R.id.sNo);
//            holder.mProduct = (TextView) convertView.findViewById(R.id.product);
//            holder.mCategory = (TextView) convertView
//                    .findViewById(R.id.category);

            holder.idattributedetail=(TextView) convertView.findViewById(R.id.idattributedetail);
            holder.attributedetail=(TextView) convertView.findViewById(R.id.attributedetail);
            holder.attributedetaillobname=(TextView) convertView.findViewById(R.id.attributedetaillobname);
            holder.Name=(TextView) convertView.findViewById(R.id.Name);
            holder.ShortName=(TextView) convertView.findViewById(R.id.ShortName);
            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);

            convertView.setTag(holder);


        }
        else {
            holder = (AttributeDetailAdapter.ViewHolder) convertView.getTag();
        }



        id = AttributeDetailPojos.get(position).AttrDId();
        String attrName = AttributeDetailPojos.get(position).Name();
        String lobName = AttributeDetailPojos.get(position).Name1();
        String name = AttributeDetailPojos.get(position).Alias();
        String shortName = AttributeDetailPojos.get(position).ShortName();
      //  String alies = AttributeDetailPojos.get(position).getName();

        holder.idattributedetail.setText(id);
        holder.attributedetail.setText(attrName);
        holder.attributedetaillobname.setText(lobName);
        holder.Name.setText(name);
        holder.ShortName.setText(shortName);

        final ViewHolder finalHolder = holder;


        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, UpdateAttributedetailActivity.class);
                intent.putExtra("AttrDId", AttributeDetailPojo.get(position).AttrDId());
               intent.putExtra("LobId",AttributeDetailPojo.get(position).Name());
               intent.putExtra("AttributeID",AttributeDetailPojo.get(position).Name1());
               intent.putExtra("Name",AttributeDetailPojo.get(position).Alias());
               intent.putExtra("ShortName",AttributeDetailPojo.get(position).ShortName());
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
        /*TextView mSNo;
        TextView mProduct;
        TextView mCategory;*/
        TextView idattributedetail,attributedetail,attributedetaillobname,Name,ShortName;

        ImageView idimageviewedit,idimageviewdeletered;


    }








}