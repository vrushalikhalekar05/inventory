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
import android.widget.Filter;
import android.widget.Filterable;


import com.ampinity.inv.Activity.UpdateAttributedetailActivity;
import com.ampinity.inv.Activity.UpdateGstActivity;
import com.ampinity.inv.Model.AttributeDetailPojo;
import com.ampinity.inv.Model.GstPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;
import com.ampinity.inv.fragment.ReportGstfragment;

import java.util.ArrayList;

import java.util.List;

/**
 * Created by peritis1 on 2/15/2018.
 */



public class GstAdapterWithFilter extends BaseAdapter implements Filterable{

    private ArrayList<GstPojo> arraylist;
    private Handler handler;

    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<GstPojo> GstPojos;
    ConnectionDetector cd;
    String id;
    String clickedid;
    private CountryFilter filter;


    private ArrayList<GstPojo> originalList;
    private ArrayList<GstPojo> countryList;


    public GstAdapterWithFilter(ArrayList<GstPojo> GstPojos, Context context) {

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
            holder = new GstAdapterWithFilter.ViewHolder();


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
            holder = (GstAdapterWithFilter.ViewHolder) convertView.getTag();
        }

        // String Name = CompanyPojos.get(position).getComapanyName();
        //use position vise
        id = GstPojos.get(position).getGstid();
        String gsts = GstPojos.get(position).getGst();
        String rates = GstPojos.get(position).getRate();

        holder.idimageviewedit.setVisibility(View.GONE);
        holder.idimageviewdeletered.setVisibility(View.GONE);

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



        return convertView;
    }




    public static class ViewHolder {
        TextView idgst;
        TextView rate;
        TextView gst;

        ImageView idimageviewedit,idimageviewdeletered;


    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter  = new CountryFilter();
        }
        return filter;
    }



    private class CountryFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if(constraint != null && constraint.toString().length() > 0)
            {
                ArrayList<GstPojo> filteredItems = new ArrayList<GstPojo>();

                for(int i = 0, l = arraylist.size(); i < l; i++)
                {

                   if(ReportGstfragment.selcetedspinnerSearchBy.equals("Name"))
                   {
                       GstPojo country = arraylist.get(i);
                       if(country.getGst().toString().toLowerCase().contains(constraint))
                           filteredItems.add(country);
                   }
                    else if(ReportGstfragment.selcetedspinnerSearchBy.equals("ID"))
                   {
                       GstPojo country = arraylist.get(i);
                       if(country.getGstid().toString().toLowerCase().contains(constraint))
                           filteredItems.add(country);
                   }
                   else if(ReportGstfragment.selcetedspinnerSearchBy.equals("Rate"))
                   {
                       GstPojo country = arraylist.get(i);
                       if(country.getRate().toString().toLowerCase().contains(constraint))
                           filteredItems.add(country);
                   }
                    else
                   {
                       GstPojo country = arraylist.get(i);
                       if(country.toString().toLowerCase().contains(constraint))
                           filteredItems.add(country);
                   }




                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }
            else
            {
                synchronized(this)
                {
                    result.values = arraylist;
                    result.count = arraylist.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            countryList = (ArrayList<GstPojo>)results.values;
            notifyDataSetChanged();
            GstPojos.clear();
            for(int i = 0, l = countryList.size(); i < l; i++)
                GstPojos.add(countryList.get(i));
            notifyDataSetInvalidated();
        }
    }
}









