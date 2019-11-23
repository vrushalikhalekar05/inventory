package com.ampinity.inv.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ampinity.inv.fragment.DatewiseStockdetailsfragment;
import com.ampinity.inv.fragment.MasterAccountGroupfragment;
import com.ampinity.inv.fragment.MasterAccountLedgerfragment;
import com.ampinity.inv.fragment.MasterAttributefragment;
import com.ampinity.inv.fragment.MasterBankfragment;
import com.ampinity.inv.fragment.MasterEmployeeRegifragment;
import com.ampinity.inv.fragment.MasterGstfragment;
import com.ampinity.inv.fragment.MasterLobfragment;
import com.ampinity.inv.fragment.MasterTaxGroupfragment;
import com.ampinity.inv.fragment.Stockdetailsfragment;
import com.ampinity.inv.fragment.SupplierwiseStockdetailsfragment;

public class Page1Adapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public Page1Adapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MasterLobfragment tab1 = new MasterLobfragment();
                return tab1;
            case 1:
                MasterAttributefragment tab2 = new MasterAttributefragment();
                return tab2;
            case 2:
                MasterTaxGroupfragment tab3 = new MasterTaxGroupfragment();
                return tab3;
            case 3:
                MasterGstfragment tab5 = new MasterGstfragment();
                return tab5;
            case 4:
                MasterBankfragment tab6 = new MasterBankfragment();
                return tab6;
            case 5:
                MasterAccountGroupfragment tab7 = new MasterAccountGroupfragment();
                return tab7;
            case 6:
                MasterAccountLedgerfragment tab8 = new MasterAccountLedgerfragment();
                return tab8;
            case 7:
                MasterEmployeeRegifragment tab9 = new MasterEmployeeRegifragment();
                return tab9;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
