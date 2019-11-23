package com.ampinity.inv.Model;

import java.io.Serializable;

/**
 * Created by peritis1 on 2/16/2018.
 */
public class AccountGroupPojo implements Serializable {
    String Accountgroupid,GroupName,AccountgroupName,BalanceSheetposition,Description;

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getAccountgroupid() {
        return Accountgroupid;
    }

    public void setAccountgroupid(String accountgroupid) {
        Accountgroupid = accountgroupid;
    }

    public String getAccountgroupName() {
        return AccountgroupName;
    }

    public void setAccountgroupName(String accountgroupName) {
        AccountgroupName = accountgroupName;
    }

    public String getBalanceSheetposition() {
        return BalanceSheetposition;
    }

    public void setBalanceSheetposition(String balanceSheetposition) {
        BalanceSheetposition = balanceSheetposition;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
