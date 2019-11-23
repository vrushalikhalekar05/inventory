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

import com.ampinity.inv.Activity.AddJournalVoucherActivity;
import com.ampinity.inv.R;

/**
 * Created by peritis1 on 2/3/2018.
 */







public class AccountJournalVoucherfragment extends Fragment {
    Button idButtonAddJournalVoucher;


    public AccountJournalVoucherfragment() {
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
        return inflater.inflate(R.layout.fragment_account_journal_voucher, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idButtonAddJournalVoucher=(Button)view.findViewById(R.id.idButtonAddJournalVoucher);

        idButtonAddJournalVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),AddJournalVoucherActivity.class));

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





