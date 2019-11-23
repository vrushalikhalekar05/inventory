package com.ampinity.inv.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ampinity.inv.Activity.MasterUpdateEmpReg;
import com.ampinity.inv.Activity.UpdateOffer1Activity;
import com.ampinity.inv.Model.BankPojo;
import com.ampinity.inv.Model.DetailsOffer1;
import com.ampinity.inv.Model.EmployeeRegiPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

public class EmployeeRegiAdapter extends BaseAdapter {
    public ArrayList<EmployeeRegiPojo> arraylist;
    private Handler handler;

    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<EmployeeRegiPojo> EmployeeRegiPojo;
    ConnectionDetector cd;
    String id,name,addr,state,city,phone,email,salary,target;
    String clickedid;

    public EmployeeRegiAdapter( ArrayList<EmployeeRegiPojo> EmployeeRegiPojo ,Context context) {
        super();
        this.EmployeeRegiPojo = EmployeeRegiPojo;
        this.arraylist = new ArrayList<EmployeeRegiPojo>();
        this.arraylist.addAll(EmployeeRegiPojo);
        this.context = context;
        //  this.detailsOffer1ArrayList = detailsOffer1ArrayList;
        handler = new Handler();
    }

    @Override
    public int getCount() {
        return EmployeeRegiPojo.size();
    }
    @Override
    public Object getItem(int position) {
        return EmployeeRegiPojo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  EmployeeRegiPojo.indexOf(EmployeeRegiPojo.get(position));
    }

    public static class ViewHolder {

        TextView empid,empname,empaddr,empstate,empcity,empphone,empemail,empsalary,emptarget;
        ImageView idimageviewedit,idimageviewdeletered;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        cd = new ConnectionDetector(context);
       // final EmployeeRegiPojo EmployeeRegiPojo = EmployeeRegiPojo.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //context.getLayoutInflater();
        if (convertView == null) {
            //change below line
            convertView = inflater.inflate(R.layout.listview_emp_regi, null);
            holder = new ViewHolder();
            holder.empid =(TextView) convertView.findViewById(R.id.empid);
            holder.empname=(TextView) convertView.findViewById(R.id.empname);
            holder.empaddr=(TextView) convertView.findViewById(R.id.empaddr);
            holder.empstate=(TextView) convertView.findViewById(R.id.empstate);
            holder.empcity=(TextView) convertView.findViewById(R.id.empcity);
            holder.empphone=(TextView) convertView.findViewById(R.id.empphone);
            holder.empemail=(TextView) convertView.findViewById(R.id.empemail);
            holder.empsalary=(TextView) convertView.findViewById(R.id.empsalary);
            holder.emptarget=(TextView) convertView.findViewById(R.id.emptarget);
            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);
            convertView.setTag(holder);
        }

        else
        {
            holder = (EmployeeRegiAdapter.ViewHolder) convertView.getTag();
        }

        id = EmployeeRegiPojo.get(position).getempId();
        name=EmployeeRegiPojo.get(position).getempName();
        addr=EmployeeRegiPojo.get(position).getempAddr();
        state=EmployeeRegiPojo.get(position).getempState();
        city=EmployeeRegiPojo.get(position).getempCity();
        phone=EmployeeRegiPojo.get(position).getempPhone();
        email=EmployeeRegiPojo.get(position).getempEmail();
        salary=EmployeeRegiPojo.get(position).getempSalary();
        target=EmployeeRegiPojo.get(position).getempTarget();


        holder.empid.setText(id);
        holder.empname.setText(name);
        holder.empaddr.setText(addr);
        holder.empstate.setText(state);
        holder.empcity.setText(city);
        holder.empphone.setText(phone);
        holder.empemail.setText(email);
        holder.empsalary.setText(salary);
        holder.emptarget.setText(target);

        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MasterUpdateEmpReg.class);
                intent.putExtra("Id",EmployeeRegiPojo.get(position).getempId());
                intent.putExtra("Name", EmployeeRegiPojo.get(position).getempName());
                intent.putExtra("Address", EmployeeRegiPojo.get(position).getempAddr());
                intent.putExtra("State", EmployeeRegiPojo.get(position).getempState());
                intent.putExtra("City", EmployeeRegiPojo.get(position).getempCity());
                intent.putExtra("Phone", EmployeeRegiPojo.get(position).getempPhone());
                intent.putExtra("Email", EmployeeRegiPojo.get(position).getempEmail());
                intent.putExtra("Salary", EmployeeRegiPojo.get(position).getempSalary());
                intent.putExtra("Target", EmployeeRegiPojo.get(position).getempTarget());
                // intent.putExtra("TotalAttribute",DetailsOffer1.get(position).getTotalAttribute());
                context.startActivity(intent);
            }

        });

        return convertView;

    }
}
