package com.ampinity.inv.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ampinity.inv.fragment.MasterAccountGroupfragment;
import com.ampinity.inv.fragment.MasterAccountLedgerfragment;
import com.ampinity.inv.fragment.MasterAttributefragment;
import com.ampinity.inv.fragment.MasterBankfragment;
import com.ampinity.inv.fragment.MasterEmployeeRegifragment;
import com.ampinity.inv.fragment.MasterGstfragment;
import com.ampinity.inv.fragment.MasterLobfragment;
import com.ampinity.inv.fragment.MasterTaxGroupfragment;
import com.ampinity.inv.fragment.SalesCreditNotefragment;
import com.ampinity.inv.fragment.SalesCustomerfragment;
import com.ampinity.inv.fragment.SalesReturnfragment;
import com.ampinity.inv.fragment.Salesfragment;

public class Page5Adapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public Page5Adapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SalesCustomerfragment tab1 = new SalesCustomerfragment();
                return tab1;
            case 1:
                Salesfragment tab2 = new Salesfragment();
                return tab2;
            case 2:
                SalesReturnfragment tab3 = new SalesReturnfragment();
                return tab3;
            case 3:
                SalesCreditNotefragment tab5 = new SalesCreditNotefragment();
                return tab5;
            /*case 4:
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
                return tab9;*/
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
