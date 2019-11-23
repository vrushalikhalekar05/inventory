package com.ampinity.inv.Model;

import java.io.Serializable;

/**
 * Created by peritis1 on 2/2/2018.
 */
public class TableDataPojo implements Serializable {



    String Tableid,LobName,TotalAttribute;

    public String getTableid() {
        return Tableid;
    }

    public void setTableid(String tableid) {
        Tableid = tableid;
    }

    public String getTotalAttribute() {
        return TotalAttribute;
    }

    public void setTotalAttribute(String totalAttribute) {
        TotalAttribute = totalAttribute;
    }

    public String getLobName() {
        return LobName;
    }

    public void setLobName(String lobName) {
        LobName = lobName;
    }
}
