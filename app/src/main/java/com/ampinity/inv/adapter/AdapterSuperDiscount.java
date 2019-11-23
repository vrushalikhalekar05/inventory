package com.ampinity.inv.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ampinity.inv.Activity.AddActivitySuperDiscount;
import com.ampinity.inv.Activity.UpdateActivitySuperDiscount;
import com.ampinity.inv.Model.SuperDiscount;
import com.ampinity.inv.R;

import java.util.ArrayList;

public class AdapterSuperDiscount extends BaseAdapter {

    Context context;
    ArrayList<SuperDiscount> SuperDiscount;
    public AdapterSuperDiscount(Context context, ArrayList<SuperDiscount> SuperDiscount){
        super();
       this.context = context;
       this.SuperDiscount = SuperDiscount;
    }
    @Override
    public int getCount() {
        return SuperDiscount.size();
    }
    @Override
    public Object getItem(int position) {
        return SuperDiscount.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public static class ViewHolder {
        TextView OfferName;
        TextView DiscountPerecntage;
        TextView id;
        ImageView idimageviewedit,idimageviewdeletered;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
      ViewHolder holder;
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      if (convertView == null) {
            convertView = inflater.inflate(R.layout.adaptersuper_discount, null);
            holder = new ViewHolder();
            holder.OfferName =(TextView) convertView.findViewById(R.id.Offername);
            holder.id=(TextView) convertView.findViewById(R.id.ID);
            holder.DiscountPerecntage= (TextView) convertView.findViewById(R.id.DiscountPercentage);
            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);
            convertView.setTag(holder);
        }
        else
        {

            holder = (ViewHolder) convertView.getTag();

        }
        SuperDiscount superDiscount1= SuperDiscount.get(position);
        holder.OfferName.setText(superDiscount1.getName().toString());
        holder.id.setText(superDiscount1.getId().toString());
        holder.DiscountPerecntage.setText(superDiscount1.getDiscountPercentage().toString());
       //holder.DiscountPerecntage.setText(superDiscount1.getDiscountPercentage().toString());
        final ViewHolder finalHolder = holder;
        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivitySuperDiscount.class);
                intent.putExtra("Id",SuperDiscount.get(position).getId());
                intent.putExtra("Name", SuperDiscount.get(position).getName());
                intent.putExtra("DiscountPercentage",SuperDiscount.get(position).getDiscountPercentage());
                // intent.putExtra("TotalAttribute",DetailsOffer1.get(position).getTotalAttribute());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
