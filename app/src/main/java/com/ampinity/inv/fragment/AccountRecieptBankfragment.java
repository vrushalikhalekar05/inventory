package com.ampinity.inv.fragment;

//import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.ampinity.inv.Activity.AddBankPaymentActivity;
import com.ampinity.inv.Activity.AddBankRecieptActivity;
import com.ampinity.inv.R;

/**
 * Created by peritis1 on 2/6/2018.

 */

public class AccountRecieptBankfragment extends Fragment {
    Button idButtonAddBankReciept;



    public AccountRecieptBankfragment() {
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
        return inflater.inflate(R.layout.fragment_account_bank_reciept, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idButtonAddBankReciept=(Button)view.findViewById(R.id.idButtonAddBankReciept);

        idButtonAddBankReciept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),AddBankRecieptActivity.class));

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




