package com.ampinity.inv.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
/*
import android.support.v7.app.AlertDialog;
*/
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ampinity.inv.Activity.UpdateEmployeeAttendance;
import com.ampinity.inv.Activity.UpdateOffer1Activity;
import com.ampinity.inv.Model.DetailsOffer1;
import com.ampinity.inv.Model.DetailsOffer2;
import com.ampinity.inv.Model.EmployeeAttendanceDetails;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

public class AdapterEmployeeAttendance extends BaseAdapter {

    public ArrayList<EmployeeAttendanceDetails> EmployeeAttendanceDetails;
    Context context;

    public AdapterEmployeeAttendance(ArrayList<EmployeeAttendanceDetails> EmployeeAttendanceDetails  , Context context) {
        super();

        this.context = context;
        this.EmployeeAttendanceDetails = EmployeeAttendanceDetails;
        }
        @Override
        public int getCount() {
        return EmployeeAttendanceDetails.size();

    }
    @Override
    public Object getItem(int position) {

        return EmployeeAttendanceDetails.get(position);

    }
    @Override
    public long getItemId(int position) {
        return  EmployeeAttendanceDetails.indexOf(EmployeeAttendanceDetails.get(position));

    }
        class ViewHolder {
        TextView OfferName;
        TextView Attendance;
        TextView Date;
        TextView id;
        ImageView idimageviewedit,idimageviewdeletered;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       ViewHolder holder=null;

        final EmployeeAttendanceDetails employeeAttendanceDetails = EmployeeAttendanceDetails.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapteremployeeattendancedate, null);
            holder = new ViewHolder();
            holder.OfferName =(TextView) convertView.findViewById(R.id.Offername);
            holder.id=(TextView) convertView.findViewById(R.id.ID);
            holder.Attendance= (TextView) convertView.findViewById(R.id.Attendance);
            holder.Date= (TextView) convertView.findViewById(R.id.Date);
            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        String OfferName=EmployeeAttendanceDetails.get(position).getName();
        String id=EmployeeAttendanceDetails.get(position).getId();
        String Attendance=EmployeeAttendanceDetails.get(position).getAttendance();
        String Date=EmployeeAttendanceDetails.get(position).getDate();



        holder.OfferName.setText(OfferName);
        holder.id.setText(id);
        holder.Attendance.setText(Attendance);
        holder.Date.setText(Date);


        final ViewHolder finalHolder = holder;

        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateEmployeeAttendance.class);


                intent.putExtra("Id",EmployeeAttendanceDetails.get(position).getId());
                intent.putExtra("Name", EmployeeAttendanceDetails.get(position).getName());
                intent.putExtra("Attendance",EmployeeAttendanceDetails.get(position).getAttendance());
                intent.putExtra("Date",EmployeeAttendanceDetails.get(position).getDate());
                // intent.putExtra("TotalAttribute",DetailsOffer1.get(position).getTotalAttribute());
                context.startActivity(intent);

        }


            });


        return convertView;
    }
}
