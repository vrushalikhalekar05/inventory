package com.ampinity.inv.Model;

import java.io.Serializable;

/**
 * Created by peritis1 on 2/20/2018.
 */
public class VenderDetailPojo implements Serializable {
    String Id,GroupName,Name,Address,City,State,Email,PhoneNo,GSTINNumber,Id_C,CompanyID;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getGSTINNumber() {
        return GSTINNumber;
    }

    public void setGSTINNumber(String GSTINNumber) {
        this.GSTINNumber = GSTINNumber;
    }

    public String getId_C() {
        return Id_C;
    }

    public void setId_C(String Id_C) { this.Id_C = Id_C; }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String CompanyID) { this.CompanyID = CompanyID; }
}
