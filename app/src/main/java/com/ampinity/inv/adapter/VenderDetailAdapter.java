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

import com.ampinity.inv.Activity.UpdateVenderDetailActivity;
import com.ampinity.inv.Activity.UpdateVenderTypeActivity;
import com.ampinity.inv.Model.VenderDetailPojo;
import com.ampinity.inv.Model.VenderTypePojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

/**
 * Created by peritis1 on 2/20/2018.
 */




public class VenderDetailAdapter extends BaseAdapter {

    private ArrayList<VenderDetailPojo> arraylist;
    private Handler handler;

    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<VenderDetailPojo> VenderDetailPojos;
    ConnectionDetector cd;
    String strId,strGroupName,strName,strAddress,strCity,strState,strEmail,strPhoneNo,strGSTINNumber;

    String clickedid;

    public VenderDetailAdapter(ArrayList<VenderDetailPojo> VenderDetailPojos, Context context) {

        this.VenderDetailPojos = VenderDetailPojos;
        this.arraylist = new ArrayList<VenderDetailPojo>();
        this.arraylist.addAll(VenderDetailPojos);
        this.context = context;

        handler = new Handler();


    }

    @Override
    public int getCount() {
        return VenderDetailPojos.size();
    }

    @Override
    public Object getItem(int position) {
        return VenderDetailPojos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return VenderDetailPojos.indexOf(VenderDetailPojos.get(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        cd = new ConnectionDetector(context);
        final VenderDetailPojo venderTypePojo = VenderDetailPojos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row_venderdetail, null);
            holder = new VenderDetailAdapter.ViewHolder();


            //intitalize
            //  holder.idTextViewCName = (TextView) convertView.findViewById(R.id.textViewCName);
            holder = new ViewHolder();

            holder.Id= (TextView) convertView.findViewById(R.id.ID);
            holder.GroupName= (TextView) convertView.findViewById(R.id.GroupName);
            holder.Name= (TextView) convertView.findViewById(R.id.Name);
            holder.Address= (TextView) convertView.findViewById(R.id.Address);
            holder.City= (TextView) convertView.findViewById(R.id.City);
            holder.State= (TextView) convertView.findViewById(R.id.State);
            holder.Email= (TextView) convertView.findViewById(R.id.Email);
            holder.PhoneNo= (TextView) convertView.findViewById(R.id.PhoneNo);
            holder.GSTINNumber= (TextView) convertView.findViewById(R.id.GSTINNumber);
            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);


            convertView.setTag(holder);
        } else {
            holder = (VenderDetailAdapter.ViewHolder) convertView.getTag();
        }

        // String Name = CompanyPojos.get(position).getComapanyName();
        //use position vise
        strId= VenderDetailPojos.get(position).getId();
        strGroupName= VenderDetailPojos.get(position).getGroupName();
        strName= VenderDetailPojos.get(position).getName();
        strAddress= VenderDetailPojos.get(position).getAddress();
        strCity= VenderDetailPojos.get(position).getCity();
        strState= VenderDetailPojos.get(position).getState();
        strEmail= VenderDetailPojos.get(position).getEmail();
        strPhoneNo= VenderDetailPojos.get(position).getPhoneNo();
        strGSTINNumber= VenderDetailPojos.get(position).getGSTINNumber();

        holder.Id.setText(strId);
        holder.GroupName.setText(strGroupName);
        holder.Name.setText(strName);
        holder.Address.setText(strAddress);
        holder.City.setText(strCity);
        holder.State.setText(strState);
        holder.Email.setText(strEmail);
        holder.PhoneNo.setText(strPhoneNo);
        holder.GSTINNumber.setText(strGSTINNumber);





        final ViewHolder finalHolder = holder;

        // perform onclick
        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, UpdateVenderDetailActivity.class);
                intent.putExtra("Id", VenderDetailPojos.get(position).getId());
                intent.putExtra("GroupName", VenderDetailPojos.get(position).getGroupName());
                intent.putExtra("Name", VenderDetailPojos.get(position).getName());
                intent.putExtra("Address", VenderDetailPojos.get(position).getAddress());
                intent.putExtra("City", VenderDetailPojos.get(position).getCity());
                intent.putExtra("State", VenderDetailPojos.get(position).getState());
                intent.putExtra("Email", VenderDetailPojos.get(position).getEmail());
                intent.putExtra("PhoneNo", VenderDetailPojos.get(position).getPhoneNo());
                intent.putExtra("GSTINNumber", VenderDetailPojos.get(position).getGSTINNumber());


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
        TextView Id,GroupName,Name,Address,City,State,Email,PhoneNo,GSTINNumber;

        ImageView idimageviewedit,idimageviewdeletered;


    }








}
