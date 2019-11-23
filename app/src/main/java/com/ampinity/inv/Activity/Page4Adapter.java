package com.ampinity.inv.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ampinity.inv.fragment.FragmentDateWiseDiscount;
import com.ampinity.inv.fragment.FragmentOffer1;
import com.ampinity.inv.fragment.FragmentOffer2;
import com.ampinity.inv.fragment.FragmentSuperDiscount;
import com.ampinity.inv.fragment.PurchaseEntryfragment;
import com.ampinity.inv.fragment.PurchaseReturnfragment;
import com.ampinity.inv.fragment.PurchaseVenderDetailfragment;
import com.ampinity.inv.fragment.PurchaseVenderTypefragment;

public class Page4Adapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public Page4Adapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PurchaseVenderTypefragment tab1 = new PurchaseVenderTypefragment();
                return tab1;
            case 1:
                PurchaseVenderDetailfragment tab2 = new PurchaseVenderDetailfragment();
                return tab2;
            case 2:
                PurchaseEntryfragment tab3 = new PurchaseEntryfragment();
                return tab3;
            case 3:
                PurchaseReturnfragment tab5 = new PurchaseReturnfragment();
                return tab5;
            case 4:
                PurchaseReturnfragment tab6 = new PurchaseReturnfragment();
                return tab6;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
