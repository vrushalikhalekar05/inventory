package com.ampinity.inv.Activity;

//import androidx.fragment.app.Fragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ampinity.inv.fragment.DatewiseStockdetailsfragment;
import com.ampinity.inv.fragment.Stockdetailsfragment;
import com.ampinity.inv.fragment.SupplierwiseStockdetailsfragment;
//import android.app.Fragment;

public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Stockdetailsfragment tab1 = new Stockdetailsfragment();
                return tab1;
            case 1:
                DatewiseStockdetailsfragment tab2 = new DatewiseStockdetailsfragment();
                return tab2;
            case 2:
                SupplierwiseStockdetailsfragment tab3 = new SupplierwiseStockdetailsfragment();
                return tab3;
            /*case 3:
                Stockdetailsfragment tab4 = new Stockdetailsfragment();
                return tab4;
            case 4:
                Stockdetailsfragment tab5 = new Stockdetailsfragment();
                return tab5;*/
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
