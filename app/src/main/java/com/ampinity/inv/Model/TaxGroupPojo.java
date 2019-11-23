package com.ampinity.inv.Model;

import java.io.Serializable;

/**
 * Created by peritis1 on 2/15/2018.
 */
public class TaxGroupPojo implements Serializable {
    String Taxgroupid,Taxgroupname,Rate,Cgst,Sgst,igst;

    public String getTaxgroupid() {
        return Taxgroupid;
    }

    public void setTaxgroupid(String taxgroupid) {
        Taxgroupid = taxgroupid;
    }

    public String getTaxgroupname() {
        return Taxgroupname;
    }

    public void setTaxgroupname(String taxgroupname) {
        Taxgroupname = taxgroupname;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getCgst() {
        return Cgst;
    }

    public void setCgst(String cgst) {
        Cgst = cgst;
    }

    public String getSgst() {
        return Sgst;
    }

    public void setSgst(String sgst) {
        Sgst = sgst;
    }

    public String getIgst() {
        return igst;
    }

    public void setIgst(String igst) {
        this.igst = igst;
    }
}
