package com.ampinity.inv.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ampinity.inv.fragment.FragmentDateWiseDiscount;
import com.ampinity.inv.fragment.FragmentOffer1;
import com.ampinity.inv.fragment.FragmentOffer2;
import com.ampinity.inv.fragment.FragmentSuperDiscount;
import com.ampinity.inv.fragment.MasterAccountGroupfragment;
import com.ampinity.inv.fragment.MasterAccountLedgerfragment;
import com.ampinity.inv.fragment.MasterAttributefragment;
import com.ampinity.inv.fragment.MasterBankfragment;
import com.ampinity.inv.fragment.MasterEmployeeRegifragment;
import com.ampinity.inv.fragment.MasterGstfragment;
import com.ampinity.inv.fragment.MasterLobfragment;
import com.ampinity.inv.fragment.MasterTaxGroupfragment;

public class Page3Adapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public Page3Adapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentOffer1 tab1 = new FragmentOffer1();
                return tab1;
            case 1:
                FragmentOffer2 tab2 = new FragmentOffer2();
                return tab2;
            case 2:
                FragmentSuperDiscount tab3 = new FragmentSuperDiscount();
                return tab3;
            case 3:
                FragmentDateWiseDiscount tab5 = new FragmentDateWiseDiscount();
                return tab5;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
