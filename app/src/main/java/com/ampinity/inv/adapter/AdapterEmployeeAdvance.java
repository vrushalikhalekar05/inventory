package com.ampinity.inv.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
//import android.support.v7.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ampinity.inv.Activity.UpdateEmployeeAdvance;
import com.ampinity.inv.Activity.UpdateOffer2Activity;
import com.ampinity.inv.Model.DetailsOffer2;
import com.ampinity.inv.Model.EmployeeAdvanceDetails;
import com.ampinity.inv.Model.EmployeeAttendanceDetails;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

public class AdapterEmployeeAdvance extends BaseAdapter {
    public ArrayList<EmployeeAdvanceDetails> EmployeeAdvanceDetails;
    private static Context context;

    public AdapterEmployeeAdvance(ArrayList<EmployeeAdvanceDetails> EmployeeAdvanceDetails ,Context context) {

        super();
        this.context = context;
        this.EmployeeAdvanceDetails = EmployeeAdvanceDetails;

    }
    @Override
    public int getCount() {

        return  EmployeeAdvanceDetails.size();

        }
    @Override
    public Object getItem(int position) {

        return EmployeeAdvanceDetails.get(position);
    }
    @Override
    public long getItemId(int position) {
        return  EmployeeAdvanceDetails.indexOf(EmployeeAdvanceDetails.get(position));
        }

        class ViewHolder {
        TextView Name;
        TextView Salary;
        TextView TotalAdvancement;
        TextView RemainingSalry;
        TextView id;
        ImageView idimageviewedit,idimageviewdeletered;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        final EmployeeAdvanceDetails employeeAdvanceDetails = EmployeeAdvanceDetails.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.adapter_employee_advancement, null);

            holder = new ViewHolder();

            holder.Name =(TextView) convertView.findViewById(R.id.Rackname);
            holder.id=(TextView) convertView.findViewById(R.id.ID);
            holder.Salary= (TextView) convertView.findViewById(R.id.Salary);
            holder.TotalAdvancement= (TextView) convertView.findViewById(R.id.TotalAdvancement);
            holder.RemainingSalry= (TextView) convertView.findViewById(R.id.RemainingSal);

            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);
            convertView.setTag(holder);

        }
        else
            {
                holder = (ViewHolder) convertView.getTag();
        }
        EmployeeAdvanceDetails employeeAdvanceDetails1= EmployeeAdvanceDetails.get(position);
        holder.Name.setText(employeeAdvanceDetails.getName().toString());
        holder.id.setText(employeeAdvanceDetails.getId().toString());
        holder.TotalAdvancement.setText(employeeAdvanceDetails.getTotalAdvancement().toString());
        holder.RemainingSalry.setText(employeeAdvanceDetails.getRemainingSalary().toString());
        holder.Salary.setText(employeeAdvanceDetails.getSalary().toString());

        final ViewHolder finalHolder = holder;

        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,UpdateEmployeeAdvance.class);
                intent.putExtra("Id",EmployeeAdvanceDetails.get(position).getId());
                intent.putExtra("Name", EmployeeAdvanceDetails.get(position).getName());
                intent.putExtra("TotalAdvancement",EmployeeAdvanceDetails.get(position).getTotalAdvancement());
                intent.putExtra("RemSalary",EmployeeAdvanceDetails.get(position).getRemainingSalary());
                intent.putExtra("Salary",EmployeeAdvanceDetails.get(position).getSalary());
                context.startActivity(intent);
            }

        });

        return convertView;
    }
}
