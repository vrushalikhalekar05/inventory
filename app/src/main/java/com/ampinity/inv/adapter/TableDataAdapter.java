package com.ampinity.inv.adapter;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
/*import android.support.v7.app.AlertDialog;*/
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ampinity.inv.Model.TableDataPojo;
import com.ampinity.inv.R;
import com.ampinity.inv.Activity.UpdateLobActivity;
import com.ampinity.inv.Utility.ConnectionDetector;
import com.ampinity.inv.fragment.MasterLobfragment;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by peritis1 on 2/2/2018.
 */
public class TableDataAdapter extends BaseAdapter {

    private ArrayList<TableDataPojo> arraylist;
    private Handler handler;

    private static Context context;
    public static AlertDialog alertDialog1;
    ArrayList<TableDataPojo> TableDataPojos;
    ConnectionDetector cd;
    String id;
    String clickedid;

    public TableDataAdapter(ArrayList<TableDataPojo> TableDataPojos, Context context) {

        this.TableDataPojos = TableDataPojos;
        this.arraylist = new ArrayList<TableDataPojo>();
        this.arraylist.addAll(TableDataPojos);
        this.context = context;

        handler = new Handler();


    }

    @Override
    public int getCount() {
        return TableDataPojos.size();
    }

    @Override
    public Object getItem(int position) {
        return TableDataPojos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return TableDataPojos.indexOf(TableDataPojos.get(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        cd = new ConnectionDetector(context);
        final TableDataPojo tableDataPojo = TableDataPojos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new TableDataAdapter.ViewHolder();


            //intitalize
          //  holder.idTextViewCName = (TextView) convertView.findViewById(R.id.textViewCName);
            holder = new ViewHolder();
            holder.mSNo = (TextView) convertView.findViewById(R.id.sNo);
            holder.mProduct = (TextView) convertView.findViewById(R.id.product);
            holder.mCategory = (TextView) convertView
                    .findViewById(R.id.category);
            holder.idimageviewedit=(ImageView) convertView.findViewById(R.id.idimageviewedit);
                    holder.idimageviewdeletered=(ImageView) convertView.findViewById(R.id.idimageviewdeletered);


            convertView.setTag(holder);
        } else {
            holder = (TableDataAdapter.ViewHolder) convertView.getTag();
        }

       // String Name = CompanyPojos.get(position).getComapanyName();
        //use position vise
        id = TableDataPojos.get(position).getTableid();
        String Name = TableDataPojos.get(position).getLobName();
        String attribute = TableDataPojos.get(position).getTotalAttribute();


        holder.mSNo.setText(id);
        holder.mProduct.setText(Name);
        holder.mCategory.setText(attribute);


        final ViewHolder finalHolder = holder;

        // perform onclick
        holder.idimageviewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, UpdateLobActivity.class);
                intent.putExtra("Lobid", TableDataPojos.get(position).getTableid());
                intent.putExtra("LobName",TableDataPojos.get(position).getLobName());
                intent.putExtra("TotalAttribute",TableDataPojos.get(position).getTotalAttribute());
                context.startActivity(intent);

            }
        });

//        holder.idimageviewdeletered.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (cd.isConnectingToInternet()) {
//                    alertMessage();
//                    clickedid=TableDataPojos.get(position).getTableid();
//                    Log.e("clickedid", "clickedid: "+clickedid );
//                }
//                else {
//                    Toast.makeText(context, "please connect to internet", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });


        return convertView;
    }


    public static class ViewHolder {
        TextView mSNo;
        TextView mProduct;
        TextView mCategory;

        ImageView idimageviewedit,idimageviewdeletered;


    }



//    public void OpenEmailDialog(final Context context) {
//
//        alertDialog1 = new AlertDialog.Builder(context).create();
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
//        final View view1 = layoutInflater.inflate(R.layout.dialogdeletelob, null);
//        alertDialog1.setView(view1);
//        alertDialog1.show();
//        alertDialog1.setCancelable(true);
////        textSend = (Button) view1.findViewById(R.id.Send);
////        idEditTextEmail = (EditText) view1.findViewById(R.id.idEditTextEmail);
//
//
//        // Typeface editTextFace = Typeface.createFromAsset(context.getAssets(),
//        //         FONT_TEXT_VIEW);
//
//        // textSend.setTypeface(editTextFace);
//
//
//        textSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Email = idEditTextEmail.getText().toString().trim();
//                if (Email.length() == 0) {
//
//                    Toast.makeText(context, "Please enter valid Email Id", Toast.LENGTH_SHORT).show();
//                } else {
//                    if (cd.isConnectingToInternet()){
//                        sendMail(Email, NameFromPojo, Id);
//                        alertDialog1.dismiss();
//                    }
//                    else {
//                        Toast.makeText(context, "please connect to internet", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//
//            }
//        });
//
//
//    }




}