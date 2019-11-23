package com.ampinity.inv.Model;

import java.io.Serializable;

/**
 * Created by peritis1 on 2/15/2018.
 */
public class BankPojo implements Serializable {
    String Bankid,BankName,BranchName,IFSCCode;

    public String getBankid() {
        return Bankid;
    }

    public void setBankid(String bankid) {
        Bankid = bankid;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getIFSCCode() {
        return IFSCCode;
    }

    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }
}
