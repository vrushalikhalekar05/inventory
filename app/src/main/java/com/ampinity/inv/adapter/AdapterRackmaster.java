package com.ampinity.inv.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
/*
import android.support.v7.app.AlertDialog;
*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.ampinity.inv.Activity.ActivityRackDetails;
import com.ampinity.inv.Activity.ActivityUpdateRackdetails;
import com.ampinity.inv.Activity.UpdateOffer1Activity;
import com.ampinity.inv.Model.DetailsOffer1;
import com.ampinity.inv.Model.DetailsOffer2;
import com.ampinity.inv.Model.RackMasterDetails;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

public class AdapterRackmaster extends BaseAdapter {
    public ArrayList<RackMasterDetails> RackMasterDetails;
    Context context;

    public static AlertDialog alertDialog1;
    ArrayList<RackMasterDetails> rackMasterDetailsArrayList;
    ConnectionDetector cd;
    String id;
    String clickedid;

    public AdapterRackmaster(ArrayList<RackMasterDetails>RackMasterDetails, Context context) {
        super();
        this.context = context;
        this.RackMasterDetails = RackMasterDetails;
    }
    @Override
    public int getCount() {
        return RackMasterDetails.size();
    }

    @Override
    public Object getItem(int position) {
       return RackMasterDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class ViewHolder {

        TextView RackName,Id;
        ImageView idimageviewedit,idimageviewdeletered;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        cd = new ConnectionDetector(context);
        final RackMasterDetails rackMasterDetails = RackMasterDetails.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.adapterrackmaster, null);
            holder= new ViewHolder();
            holder.RackName = (TextView) convertView.findViewById(R.id.Rackname);
            holder.Id= (TextView) convertView.findViewById(R.id.ID);
            holder.idimageviewedit= (ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered= (ImageView) convertView.findViewById(R.id.idimageviewdeletered);
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

        }

       String Name = RackMasterDetails.get(position).getRackName();
       String id=RackMasterDetails.get(position).getId();

        holder.RackName.setText(Name);
        holder.Id.setText(id);
        final ViewHolder finalHolder = holder;

        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                Intent intent = new Intent(context, ActivityUpdateRackdetails.class);
                intent.putExtra("Id",RackMasterDetails.get(position).getId());
                intent.putExtra("RackName",RackMasterDetails.get(position).getRackName());
                context.startActivity(intent);
            }

        });

        return convertView;
    }
}
