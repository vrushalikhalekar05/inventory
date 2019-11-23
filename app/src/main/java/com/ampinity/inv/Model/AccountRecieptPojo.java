package com.ampinity.inv.Model;

/**
 * Created by peritis1 on 2/16/2018.
 */

import java.io.Serializable;

 public class AccountRecieptPojo implements Serializable {

    String Accountgroupid,GroupName,AccountgroupName,OpeningBalance,ClosingBalance,Description,Date,Amount;
    public String getAccountgroupid() {
        return Accountgroupid;
    }

    public void setAccountgroupid(String accountgroupid) {
        Accountgroupid = accountgroupid;
    }

     public String getDate() {
         return Date;
     }

     public String getAmount() {
         return Amount;
     }

     public void setAmount(String amount) {
         Amount = amount;
     }

     public void setDate(String date) {

         Date = date;
     }

     public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getAccountgroupName() {
        return AccountgroupName;
    }

    public void setAccountgroupName(String accountgroupName) {
        AccountgroupName = accountgroupName;
    }

    public String getOpeningBalance() {
        return OpeningBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        OpeningBalance = openingBalance;
    }

    public String getClosingBalance() {
        return ClosingBalance;
    }

    public void setClosingBalance(String closingBalance) {
        ClosingBalance = closingBalance;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


}
