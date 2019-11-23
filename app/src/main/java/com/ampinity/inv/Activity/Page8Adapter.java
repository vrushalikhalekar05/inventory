package com.ampinity.inv.Activity;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ampinity.inv.fragment.ReportCashInfragment;
import com.ampinity.inv.fragment.ReportCashOutfragment;
import com.ampinity.inv.fragment.ReportCategoryListfragment;
import com.ampinity.inv.fragment.ReportCustomerListfragment;
import com.ampinity.inv.fragment.ReportCustomerPaymentfragment;
import com.ampinity.inv.fragment.ReportGstfragment;
import com.ampinity.inv.fragment.ReportProfitLossfragment;
import com.ampinity.inv.fragment.ReportPurchasefragment;
import com.ampinity.inv.fragment.ReportSalesfragment;
import com.ampinity.inv.fragment.ReportStockfragment;
import com.ampinity.inv.fragment.ReportSupplierListfragment;
import com.ampinity.inv.fragment.ReportSupplierPaymentfragment;

public class Page8Adapter extends FragmentStatePagerAdapter {
    int numOfTabs;
    public Page8Adapter(FragmentManager fm,int numOfTabs){
        super(fm);
        this.numOfTabs=numOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                ReportCustomerListfragment tab0=new ReportCustomerListfragment();
                return tab0;
            case 1:
                ReportSupplierListfragment tab1=new ReportSupplierListfragment();
                return tab1;
            case 2:
                ReportCategoryListfragment tab2=new ReportCategoryListfragment();
                return tab2;
            case 3:
                ReportPurchasefragment tab3=new ReportPurchasefragment();
                return tab3;
            case 4:
                ReportSalesfragment tab4=new ReportSalesfragment();
                return tab4;
            case 5:
                ReportStockfragment tab5=new ReportStockfragment();
                return tab5;
            case 6:
                ReportCashInfragment tab6=new ReportCashInfragment();
                return tab6;
            case 7:
                ReportCashOutfragment tab7=new ReportCashOutfragment();
                return tab7;
            case 8:
                ReportSupplierPaymentfragment tab8=new ReportSupplierPaymentfragment();
                return tab8;
            case 9:
                ReportCustomerPaymentfragment tab9=new ReportCustomerPaymentfragment();
                return tab9;
            case 10:
                ReportProfitLossfragment tab10=new ReportProfitLossfragment();
                return tab10;
            case 11:
                ReportGstfragment tab11=new ReportGstfragment();
                return tab11;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
