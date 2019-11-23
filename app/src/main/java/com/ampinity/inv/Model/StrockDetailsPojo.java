package com.ampinity.inv.Model;

import java.io.Serializable;

public class StrockDetailsPojo implements Serializable {
    String id,SuppName,SuppBillDate,SuppBillNo,inwords,paymentBy,paidAmt,remainAmt,paid,unpaid,subTotal,discount,sgstAmt,cgstAmt,igstAmt,
    totalAmt;
    public String getid() {
        return id;
    }
    public void setid(String id) {
        this.id = id;
    }

    public String getSuppName() { return SuppName; }
    public void setSuppName(String SuppName) { this.SuppName = SuppName; }

    public String getSuppBillDate() {
        return SuppBillDate;
    }
    public void setSuppBillDate(String SuppBillDate) {
        this.SuppBillDate = SuppBillDate;
    }

    public String getSuppBillNo() {
        return SuppBillNo;
    }
    public void setSuppBillNo(String SuppBillNo) {
        this.SuppBillNo = SuppBillNo;
    }

    public String getinwords() {
        return inwords;
    }
    public void setinwords(String inwords) {
        this.inwords = inwords;
    }

    public String getpaymentBy() {
        return paymentBy;
    }
    public void setpaymentBy(String paymentBy) {
        this.paymentBy = paymentBy;
    }

    public String getpaidAmt() { return paidAmt; }
    public void setpaidAmt(String paidAmt) { this.paidAmt = paidAmt; }

    public String getremainAmt() { return remainAmt; }
    public void setremainAmt(String remainAmt) { this.remainAmt = remainAmt; }

    public String getpaid() { return paid; }
    public void setpaid(String paid) { this.paid = paid; }

    public String getunpaid() { return unpaid; }
    public void setunpaid(String unpaid) { this.unpaid = unpaid; }

    public String getsubTotal() { return subTotal; }
    public void setsubTotal(String subTotal) { this.subTotal = subTotal; }

    public String getdiscount() { return discount; }
    public void setdiscount(String discount) { this.discount = discount; }

    public String getsgstAmt() { return sgstAmt; }
    public void setsgstAmt(String sgstAmt) { this.sgstAmt = sgstAmt; }

    public String getcgstAmt() { return cgstAmt; }
    public void setcgstAmt(String cgstAmt) { this.cgstAmt = cgstAmt; }

    public String getigstAmt() { return igstAmt; }
    public void setigstAmt(String igstAmt) { this.igstAmt = igstAmt; }

    public String gettotalAmt() { return totalAmt; }
    public void settotalAmt(String totalAmt) { this.totalAmt = totalAmt; }
}
