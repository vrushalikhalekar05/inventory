package com.ampinity.inv.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ampinity.inv.Activity.UpdateOffer1Activity;
import com.ampinity.inv.Activity.UpdateOffer2Activity;
import com.ampinity.inv.Model.DetailsOffer1;
import com.ampinity.inv.Model.DetailsOffer2;
import com.ampinity.inv.R;

import java.util.ArrayList;

public class AdapterOffer2 extends BaseAdapter {
    public ArrayList<DetailsOffer2> DetailsOffer2;
    Context context;



    public AdapterOffer2(Context context, ArrayList<DetailsOffer2> DetailsOffer2) {
        super();
        this.context = context;
        this.DetailsOffer2 = DetailsOffer2;
    }
    @Override
    public int getCount() {
        return DetailsOffer2.size();
    }
    @Override
    public Object getItem(int position) {
        return DetailsOffer2.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    class ViewHolder {

        TextView OfferName,OfferPercentage,Id;
        ImageView idimageviewedit,idimageviewdeletered;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //context.getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapteroffer2, null);
          holder= new ViewHolder();
          holder.OfferName = (TextView) convertView.findViewById(R.id.Offername);
          holder.Id= (TextView) convertView.findViewById(R.id.ID);
          holder.idimageviewedit= (ImageView) convertView.findViewById(R.id.idimageviewedit);
          holder.OfferPercentage= (TextView) convertView.findViewById(R.id.OfferPercentage);

        convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DetailsOffer2 detailsOffer2= DetailsOffer2.get(position);
        holder.OfferName.setText(detailsOffer2.getName().toString());
        holder.Id.setText(detailsOffer2.getId().toString());
        holder.OfferPercentage.setText(detailsOffer2.getPercentage().toString());


        final ViewHolder finalHolder = holder;

        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateOffer2Activity.class);
                intent.putExtra("Id",DetailsOffer2.get(position).getId());
                intent.putExtra("Name", DetailsOffer2.get(position).getName());
                intent.putExtra("Percentage",DetailsOffer2.get(position).getPercentage());
                // intent.putExtra("TotalAttribute",DetailsOffer1.get(position).getTotalAttribute());
                context.startActivity(intent);
            }

        });

        return convertView;

    }
}
