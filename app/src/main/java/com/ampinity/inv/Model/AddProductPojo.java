package com.ampinity.inv.Model;

import java.io.Serializable;

/**
 * Created by peritis1 on 2/22/2018.
 */
public class AddProductPojo implements Serializable {
    String ID,PID,Name,ItemCode,HSNSACCode,lable1,lable2,lable3,lable4,lable5,lable6,lable7,lable8,PurchasePrice,per,SalePrice,SaleDesPer,Quantity,Gst,LobName,SGSTPERItem,SGSTAMTItem,IGSTPERItem,IGSTAMTItem,CGSTPERItem,CGSTAMTItem,SubPriceItem;

    public String getSGSTPERItem() {
        return SGSTPERItem;
    }

    public String getSubPriceItem() {
        return SubPriceItem;
    }

    public void setSubPriceItem(String subPriceItem) {
        SubPriceItem = subPriceItem;
    }

    public void setSGSTPERItem(String SGSTPERItem) {
        this.SGSTPERItem = SGSTPERItem;
    }

    public String getSGSTAMTItem() {
        return SGSTAMTItem;
    }

    public void setSGSTAMTItem(String SGSTAMTItem) {
        this.SGSTAMTItem = SGSTAMTItem;
    }

    public String getIGSTPERItem() {
        return IGSTPERItem;
    }

    public void setIGSTPERItem(String IGSTPERItem) {
        this.IGSTPERItem = IGSTPERItem;
    }

    public String getIGSTAMTItem() {
        return IGSTAMTItem;
    }

    public void setIGSTAMTItem(String IGSTAMTItem) {
        this.IGSTAMTItem = IGSTAMTItem;
    }

    public String getCGSTPERItem() {
        return CGSTPERItem;
    }

    public void setCGSTPERItem(String CGSTPERItem) {
        this.CGSTPERItem = CGSTPERItem;
    }

    public String getCGSTAMTItem() {
        return CGSTAMTItem;
    }

    public void setCGSTAMTItem(String CGSTAMTItem) {
        this.CGSTAMTItem = CGSTAMTItem;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getHSNSACCode() {
        return HSNSACCode;
    }

    public void setHSNSACCode(String HSNSACCode) {
        this.HSNSACCode = HSNSACCode;
    }

    public String getLable1() {
        return lable1;
    }

    public void setLable1(String lable1) {
        this.lable1 = lable1;
    }

    public String getLable2() {
        return lable2;
    }

    public void setLable2(String lable2) {
        this.lable2 = lable2;
    }

    public String getLable6() {
        return lable6;
    }

    public void setLable6(String lable6) {
        this.lable6 = lable6;
    }

    public String getLable7() {
        return lable7;
    }

    public void setLable7(String lable7) {
        this.lable7 = lable7;
    }

    public String getLable3() {
        return lable3;
    }

    public void setLable3(String lable3) {
        this.lable3 = lable3;
    }

    public String getLable4() {
        return lable4;
    }

    public void setLable4(String lable4) {
        this.lable4 = lable4;
    }

    public String getLable5() {
        return lable5;
    }

    public void setLable5(String lable5) {
        this.lable5 = lable5;
    }



    public String getLable8() {
        return lable8;
    }

    public void setLable8(String lable8) {
        this.lable8 = lable8;
    }

    public String getPurchasePrice() {
        return PurchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        PurchasePrice = purchasePrice;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }

    public String getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(String salePrice) {
        SalePrice = salePrice;
    }

    public String getSaleDesPer() {
        return SaleDesPer;
    }

    public void setSaleDesPer(String saleDesPer) {
        SaleDesPer = saleDesPer;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getGst() {
        return Gst;
    }

    public void setGst(String gst) {
        Gst = gst;
    }

    public String getLobName() {
        return LobName;
    }

    public void setLobName(String lobName) {
        LobName = lobName;
    }
}
