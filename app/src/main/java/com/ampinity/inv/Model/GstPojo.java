package com.ampinity.inv.Model;

import java.io.Serializable;

/**
 * Created by peritis1 on 2/15/2018.
 */
public class GstPojo implements Serializable {
    String Gstid,Gst,Rate;

    public String getGstid() {
        return Gstid;
    }

    public void setGstid(String gstid) {
        Gstid = gstid;
    }

    public String getGst() {
        return Gst;
    }

    public void setGst(String gst) {
        Gst = gst;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }
}
