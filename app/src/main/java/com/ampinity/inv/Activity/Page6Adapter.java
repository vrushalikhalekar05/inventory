package com.ampinity.inv.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ampinity.inv.fragment.AccountBalancesheetfragment;
import com.ampinity.inv.fragment.AccountBankBookfragment;
import com.ampinity.inv.fragment.AccountBankPaymentfragment;
import com.ampinity.inv.fragment.AccountCashPaymentfragment;
import com.ampinity.inv.fragment.AccountCreateLedgerfragment;
import com.ampinity.inv.fragment.AccountDayBookfragment;
import com.ampinity.inv.fragment.AccountJournalVoucherfragment;
import com.ampinity.inv.fragment.AccountLedgerBalancefragment;
import com.ampinity.inv.fragment.AccountProfitLossfragment;
import com.ampinity.inv.fragment.AccountRecieptBankfragment;
import com.ampinity.inv.fragment.AccountRecieptfragment;
import com.ampinity.inv.fragment.AccountTrialBalancefragment;

public class Page6Adapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    public Page6Adapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                AccountCreateLedgerfragment tab0=new AccountCreateLedgerfragment();
                return tab0;
            case 1:
                AccountCashPaymentfragment tab1=new AccountCashPaymentfragment();
                return tab1;
            case 2:
                AccountBankPaymentfragment tab2=new AccountBankPaymentfragment();
                return tab2;
            case 3:
                AccountRecieptfragment tab3=new AccountRecieptfragment();
                return tab3;
            case 4:
                AccountRecieptBankfragment tab4=new AccountRecieptBankfragment();
                return tab4;
            case 5:
                AccountJournalVoucherfragment tab5=new AccountJournalVoucherfragment();
                return tab5;
            case 6:
                AccountLedgerBalancefragment tab6=new AccountLedgerBalancefragment();
                return tab6;
            case 7:
                AccountDayBookfragment tab7=new AccountDayBookfragment();
                return tab7;
            case 8:
                AccountTrialBalancefragment tab8=new AccountTrialBalancefragment();
                return tab8;
            case 9:
                AccountBalancesheetfragment tab9=new AccountBalancesheetfragment();
                return tab9;
            case 10:
                AccountBankBookfragment tab10=new AccountBankBookfragment();
                return tab10;
            case 11:
                AccountProfitLossfragment tab11=new AccountProfitLossfragment();
                return tab11;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
