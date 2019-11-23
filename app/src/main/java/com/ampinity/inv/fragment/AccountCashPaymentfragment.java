package com.ampinity.inv.fragment;

import android.app.DatePickerDialog;
//import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
/*
import android.support.v7.app.AlertDialog;
*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.ampinity.inv.Activity.AddAccountGroupActivity;
import com.ampinity.inv.Activity.AddCashPaymentActivity;
import com.ampinity.inv.R;

import java.util.Calendar;

/**
 * Created by peritis1 on 2/3/2018.
 */

public class AccountCashPaymentfragment extends Fragment {
    Button idButtonAddCashPayment;



    public AccountCashPaymentfragment() {
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
        return inflater.inflate(R.layout.fragment_account_cash_payment, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idButtonAddCashPayment=(Button)view.findViewById(R.id.idButtonAddCashPayment);

        idButtonAddCashPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),AddCashPaymentActivity.class));

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


