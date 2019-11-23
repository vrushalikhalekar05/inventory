package com.ampinity.inv.fragment;
import androidx.fragment.app.Fragment;

//import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.ampinity.inv.Activity.AddSalesCustomerActivity;
import com.ampinity.inv.Activity.AddSalesReturnActivity;
import com.ampinity.inv.R;
import com.ampinity.inv.adapter.CustomizedSpinnerAdapter;

/**
 * Created by peritis1 on 2/2/2018.
 */





public class SupplierwiseStockdetailsfragment extends Fragment {
    Button idButtonAddSalesReturn;
    String [] CompanyName={
            "Select Supplier",
            "ABCD",
            "PQRS",
            "ASC",
            "ACN",
            "RAM"};
    String selcetedspinnerSearchBy;
Spinner spinnerSearchBy;

    public SupplierwiseStockdetailsfragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stock_details_supplierwise, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idButtonAddSalesReturn=(Button)view.findViewById(R.id.idButtonAddSalesReturn);
        spinnerSearchBy = (Spinner)view. findViewById(R.id.spinnerSearchBy);



        final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item,
                CompanyName);
        spinnerSearchBy.setAdapter(adapter1);


        spinnerSearchBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selcetedspinnerSearchBy=spinnerSearchBy.getSelectedItem().toString();
                if(selcetedspinnerSearchBy.equals("Select Supplier"))
                {

//                    Lobname=new String[1];
//                    Lobname[0]=
//
//                    final ArrayAdapter<String> adapter1 = new CustomizedSpinnerAdapter(
//                            AddAttributeActivity.this, android.R.layout.simple_spinner_item,
//                            Lobname);
//                    spinnerlobname.setAdapter(adapter1);

                }
                else
                {


                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        idButtonAddSalesReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // startActivity(new Intent(getActivity(),AddSalesCustomerActivity.class));

            }
        });

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}




