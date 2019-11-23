package com.ampinity.inv.Model;

import java.io.Serializable;

/**
 * Created by peritis1 on 2/20/2018.
 */
public class VenderTypePojo implements Serializable {

    String VenderTypeid,VenderGroup,Description;

    public String getVenderTypeid() {
        return VenderTypeid;
    }

    public void setVenderTypeid(String venderTypeid) {
        VenderTypeid = venderTypeid;
    }

    public String getVenderGroup() {
        return VenderGroup;
    }

    public void setVenderGroup(String venderGroup) {
        VenderGroup = venderGroup;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
