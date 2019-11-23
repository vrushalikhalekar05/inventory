package com.ampinity.inv.Model;

import java.io.Serializable;

/**
 * Created by peritis1 on 2/13/2018.
 */
public class AttributeDetailPojo implements Serializable {
    String AttrDId,Name,Name1,Alias,ShortName;

//getter
    public String AttrDId() {
        return AttrDId;
    }
//setter
    public void AttrDId(String lobId) {
        AttrDId = lobId;
    }

    public String Name() {
        return Name;
    }

    public void Name(String attributeID) {
        Name = attributeID;
    }

    public String Name1() {
        return Name1;
    }

    public void Name1(String catName) {
        Name1 = catName;
    }

    public String Alias() {
        return Alias;
    }

    public void Alias(String shortName) {
        Alias = shortName;
    }

    public String ShortName() {
        return ShortName;
    }

    public void ShortName(String companyId) {
        ShortName = companyId;
    }
}

