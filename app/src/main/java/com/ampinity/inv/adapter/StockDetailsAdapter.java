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

import com.ampinity.inv.Activity.UpdateOffer1Activity;
import com.ampinity.inv.Model.EmployeeRegiPojo;
import com.ampinity.inv.Model.StrockDetailsPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Utility.ConnectionDetector;

import java.util.ArrayList;

public class StockDetailsAdapter extends BaseAdapter {
    public ArrayList<StrockDetailsPojo> arraylist;
    private Handler handler;

    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<StrockDetailsPojo> StrockDetailsPojo;
    ConnectionDetector cd;
    String id,name,billdate,billno,inwords,paymentby,paidamt,remainamt,paid,unpaid,subtotal,discount,sgst,cgst,igst,totalamt;
    String clickedid;

    public StockDetailsAdapter(ArrayList<StrockDetailsPojo> StrockDetailsPojo , Context context) {
        super();
        this.StrockDetailsPojo = StrockDetailsPojo;
        this.arraylist = new ArrayList<StrockDetailsPojo>();
        this.arraylist.addAll(StrockDetailsPojo);
        this.context = context;
        //  this.detailsOffer1ArrayList = detailsOffer1ArrayList;
        handler = new Handler();
    }

    @Override
    public int getCount() {
        return StrockDetailsPojo.size();
    }
    @Override
    public Object getItem(int position) {
        return StrockDetailsPojo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  StrockDetailsPojo.indexOf(StrockDetailsPojo.get(position));
    }

    public static class ViewHolder {

        TextView id,name,billdate,billno,inwords,paymentby,paidamt,remainamt,paid,unpaid,subtotal,discount,sgst,cgst,igst,totalamt;
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
            convertView = inflater.inflate(R.layout.listview_stockdetails, null);
            holder = new StockDetailsAdapter.ViewHolder();
            holder.id =(TextView) convertView.findViewById(R.id.id);
            holder.name=(TextView) convertView.findViewById(R.id.name);
            holder.billdate=(TextView) convertView.findViewById(R.id.billdate);
            holder.billno=(TextView) convertView.findViewById(R.id.billno);
            holder.inwords=(TextView) convertView.findViewById(R.id.inwords);
            holder.paymentby=(TextView) convertView.findViewById(R.id.paymentby);
            /*holder.paidamt=(TextView) convertView.findViewById(R.id.paidamt);*/
            /*holder.remainamt=(TextView) convertView.findViewById(R.id.remainamt);
            holder.paid=(TextView) convertView.findViewById(R.id.paid);
            holder.unpaid=(TextView) convertView.findViewById(R.id.unpaid);
            holder.subtotal=(TextView) convertView.findViewById(R.id.subtotal);
            holder.discount=(TextView) convertView.findViewById(R.id.discount);
            holder.sgst=(TextView) convertView.findViewById(R.id.sgst);
            holder.cgst=(TextView) convertView.findViewById(R.id.cgst);
            holder.igst=(TextView) convertView.findViewById(R.id.igst);
            holder.totalamt=(TextView) convertView.findViewById(R.id.totalamt);
            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
            holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);*/
            convertView.setTag(holder);
        }

        else
        {
            holder = (StockDetailsAdapter.ViewHolder) convertView.getTag();
        }

        id = StrockDetailsPojo.get(position).getid();
        name=StrockDetailsPojo.get(position).getSuppName();
        billdate=StrockDetailsPojo.get(position).getSuppBillDate();
        billno=StrockDetailsPojo.get(position).getSuppBillNo();
        inwords=StrockDetailsPojo.get(position).getinwords();
        paymentby=StrockDetailsPojo.get(position).getpaymentBy();
        /*paidamt=StrockDetailsPojo.get(position).getpaidAmt();
        remainamt=StrockDetailsPojo.get(position).getremainAmt();
        paid=StrockDetailsPojo.get(position).getpaid();
        unpaid=StrockDetailsPojo.get(position).getunpaid();
        subtotal=StrockDetailsPojo.get(position).getsubTotal();
        discount=StrockDetailsPojo.get(position).getdiscount();
        sgst=StrockDetailsPojo.get(position).getsgstAmt();
        cgst=StrockDetailsPojo.get(position).getcgstAmt();
        igst=StrockDetailsPojo.get(position).getigstAmt();
        totalamt=StrockDetailsPojo.get(position).gettotalAmt();*/


        holder.id.setText(id);
        holder.name.setText(name);
        holder.billdate.setText(billdate);
        holder.billno.setText(billno);
        holder.inwords.setText(inwords);
        holder.paymentby.setText(paymentby);
       /* holder.paidamt.setText(paidamt);
        holder.remainamt.setText(remainamt);
        holder.paid.setText(paid);
        holder.unpaid.setText(unpaid);
        holder.subtotal.setText(subtotal);
        holder.discount.setText(discount);
        holder.sgst.setText(sgst);
        holder.cgst.setText(cgst);
        holder.igst.setText(igst);
        holder.totalamt.setText(totalamt);*/

        /*holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateOffer1Activity.class);
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

        });*/

        return convertView;

    }
}
