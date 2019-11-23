package com.ampinity.inv.Model;

/**
 * Created by peritis1 on 2/20/2018.
 */
public class PurchaseEntryPojo {

    String vendername,PurchaseEntryName,SupplierBillNo,Date,IDPurchaseEntry,InWord,PaymentBy,PaidAmount,RemainingAmount,TotalSub,Discount,TotalAmount,DatePaid,SGSTAmount,CGSTAmount,IGSTAmount;

    public String getVendername() {
        return vendername;
    }

    public String getPaymentBy() {
        return PaymentBy;
    }

    public String getSGSTAmount() {
        return SGSTAmount;
    }

    public void setSGSTAmount(String SGSTAmount) {
        this.SGSTAmount = SGSTAmount;
    }

    public String getCGSTAmount() {
        return CGSTAmount;
    }

    public void setCGSTAmount(String CGSTAmount) {
        this.CGSTAmount = CGSTAmount;
    }

    public String getIGSTAmount() {
        return IGSTAmount;
    }

    public void setIGSTAmount(String IGSTAmount) {
        this.IGSTAmount = IGSTAmount;
    }

    public void setPaymentBy(String paymentBy) {
        PaymentBy = paymentBy;

    }

    public void setVendername(String vendername) {
        this.vendername = vendername;
    }

    public String getPurchaseEntryName() {
        return PurchaseEntryName;
    }

    public void setPurchaseEntryName(String purchaseEntryName) {
        PurchaseEntryName = purchaseEntryName;
    }

    public String getSupplierBillNo() {
        return SupplierBillNo;
    }

    public void setSupplierBillNo(String supplierBillNo) {
        SupplierBillNo = supplierBillNo;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getIDPurchaseEntry() {
        return IDPurchaseEntry;
    }

    public void setIDPurchaseEntry(String IDPurchaseEntry) {
        this.IDPurchaseEntry = IDPurchaseEntry;
    }

    public String getInWord() {
        return InWord;
    }

    public void setInWord(String inWord) {
        InWord = inWord;
    }

    public String getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        PaidAmount = paidAmount;
    }

    public String getRemainingAmount() {
        return RemainingAmount;
    }

    public void setRemainingAmount(String remainingAmount) {
        RemainingAmount = remainingAmount;
    }

    public String getTotalSub() {
        return TotalSub;
    }

    public void setTotalSub(String totalSub) {
        TotalSub = totalSub;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getDatePaid() {
        return DatePaid;
    }

    public void setDatePaid(String datePaid) {
        DatePaid = datePaid;
    }
}
