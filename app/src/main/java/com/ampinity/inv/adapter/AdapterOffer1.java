package com.ampinity.inv.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
/*import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ampinity.inv.Activity.UpdateLobActivity;
import com.ampinity.inv.Activity.UpdateOffer1Activity;
import com.ampinity.inv.Model.DetailsOffer1;
import com.ampinity.inv.Model.Model;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

public class AdapterOffer1 extends BaseAdapter{
    public ArrayList<DetailsOffer1> detailsOffer1ArrayList;
    private Handler handler;
    //   Activity activity;
    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<DetailsOffer1> DetailsOffer1;
    ConnectionDetector cd;
    String id;
    String clickedid;

    public AdapterOffer1( ArrayList<DetailsOffer1> DetailsOffer1 ,Context context) {
        super();
        this.DetailsOffer1 = DetailsOffer1;
        this.detailsOffer1ArrayList = new ArrayList<DetailsOffer1>();
        this.detailsOffer1ArrayList.addAll(DetailsOffer1);
        this.context = context;
        //  this.detailsOffer1ArrayList = detailsOffer1ArrayList;
        handler = new Handler();
    }

    @Override
    public int getCount() {
        return DetailsOffer1.size();
    }
    @Override
    public Object getItem(int position) {
        return DetailsOffer1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  DetailsOffer1.indexOf(DetailsOffer1.get(position));
    }

    public static class ViewHolder {
        TextView OfferName;
        TextView Perecntage;
        TextView id;
        ImageView idimageviewedit,idimageviewdeletered;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        cd = new ConnectionDetector(context);
        final DetailsOffer1 detailsOffer1 = DetailsOffer1.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //context.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapteroffer1, null);
            holder = new ViewHolder();
            holder.OfferName =(TextView) convertView.findViewById(R.id.Offername);
            holder.id=(TextView) convertView.findViewById(R.id.ID);
            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);
            convertView.setTag(holder);
        }

        else
        {
            holder = (AdapterOffer1.ViewHolder) convertView.getTag();
        }

        id = DetailsOffer1.get(position).getId();
        String Name = DetailsOffer1.get(position).getName();
        holder.OfferName.setText(detailsOffer1.getName().toString());
        holder.id.setText(detailsOffer1.getId());
        final ViewHolder finalHolder = holder;

        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateOffer1Activity.class);
                intent.putExtra("Id",DetailsOffer1.get(position).getId());
                intent.putExtra("Name", DetailsOffer1.get(position).getName());
                // intent.putExtra("TotalAttribute",DetailsOffer1.get(position).getTotalAttribute());
                context.startActivity(intent);
            }

        });

        return convertView;

    }
}
