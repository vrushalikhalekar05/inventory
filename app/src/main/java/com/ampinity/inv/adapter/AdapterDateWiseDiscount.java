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

import com.ampinity.inv.Activity.UpdateDateWiseActivity;
import com.ampinity.inv.Activity.UpdateLobActivity;
import com.ampinity.inv.Model.DateWiseDiscont;
import com.ampinity.inv.Model.DetailsOffer1;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

public class AdapterDateWiseDiscount extends BaseAdapter {
    public ArrayList<DateWiseDiscont> dateWiseDiscontArrayList;
    private Handler handler;
    //   Activity activity;
    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<DateWiseDiscont> DateWiseDiscount;
    ConnectionDetector cd;
    String id;
    String clickedid;

    public AdapterDateWiseDiscount(ArrayList<DateWiseDiscont> DateWisediscount, Context context) {
        super();
        this.DateWiseDiscount = DateWisediscount;
        this.dateWiseDiscontArrayList = new ArrayList<DateWiseDiscont>();
        this.dateWiseDiscontArrayList.addAll(DateWisediscount);
        this.context = context;
        //  this.detailsOffer1ArrayList = detailsOffer1ArrayList;
        handler = new Handler();
    }
    @Override
    public int getCount() {
        return DateWiseDiscount.size();
    }
    @Override
    public Object getItem(int position) {
        return DateWiseDiscount.get(position);
    }
    @Override
    public long getItemId(int position) {
        return DateWiseDiscount.indexOf(DateWiseDiscount.get(position));
    }
    public static class ViewHolder {
        TextView OfferName;
        TextView PerecntageOffer;
        TextView id;
        TextView FromDate, ToDate;
        ImageView idimageviewedit, idimageviewdeletered;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        cd = new ConnectionDetector(context);
        final DateWiseDiscont dateWiseDiscont = DateWiseDiscount.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //context.getLayoutInflater();
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.adapterdatewise_discount, null);

            holder = new ViewHolder();
            holder.OfferName = (TextView) convertView.findViewById(R.id.Offername);
            holder.id = (TextView) convertView.findViewById(R.id.ID);
            holder.FromDate= (TextView) convertView.findViewById(R.id.FromDate);
            holder.ToDate= (TextView) convertView.findViewById(R.id.ToDate);
            holder.PerecntageOffer= (TextView) convertView.findViewById(R.id.PercentageOffer);
            holder.idimageviewedit = (ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered = (ImageView) convertView.findViewById(R.id.idimageviewdeletered);
            convertView.setTag(holder);


        }
        else {

            holder = (AdapterDateWiseDiscount.ViewHolder) convertView.getTag();
            }

        id = DateWiseDiscount.get(position).getId();

        String Name = DateWiseDiscount.get(position).getName();

        holder.OfferName.setText(dateWiseDiscont.getName().toString());

        holder.id.setText(dateWiseDiscont.getId());

        holder.PerecntageOffer.setText(dateWiseDiscont.getPertageOffer());

        holder.FromDate.setText(dateWiseDiscont.getFromDate());

        holder.ToDate.setText(dateWiseDiscont.getToDate());

        final ViewHolder finalHolder = holder;

        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, UpdateDateWiseActivity.class);

                intent.putExtra("Name", DateWiseDiscount.get(position).getName());

                intent.putExtra("Id",DateWiseDiscount.get(position).getId());

                intent.putExtra("PerOffer",DateWiseDiscount.get(position).getPertageOffer());

                intent.putExtra("FromDate",DateWiseDiscount.get(position).getFromDate());

                intent.putExtra("ToDate",DateWiseDiscount.get(position).getToDate());

                context.startActivity(intent);
            }

        });

        return convertView;
    }
}
