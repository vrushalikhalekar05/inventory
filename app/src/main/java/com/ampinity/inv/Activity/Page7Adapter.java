package com.ampinity.inv.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
//of setting page
public class Page7Adapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public Page7Adapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            default:return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
