package com.ampinity.inv.Model;

import java.io.Serializable;

public class EmployeeRegiPojo implements Serializable {
    String empId,empName,empAddr,empState,empCity,empPhone,empEmail,empSalary,empTarget;
    public String getempId(){
        return empId;
    }
    public void setempId(String empId){
        this.empId=empId;
    }
    public String getempName(){
        return empName;
    }
    public void setempName(String empName){
        this.empName=empName;
    }
    public String getempAddr(){
        return empAddr;
    }
    public void setempAddr(String empAddr){
        this.empAddr=empAddr;
    }
    public String getempState(){
        return empState;
    }
    public void setempState(String empState){
        this.empState=empState;
    }
    public String getempCity(){
        return empCity;
    }
    public void setempCity(String empCity){
        this.empCity=empCity;
    }
    public String getempPhone(){
        return empPhone;
    }
    public void setempPhone(String empPhone){
        this.empPhone=empPhone;
    }
    public String getempEmail(){
        return empEmail;
    }
    public void setempEmail(String empEmail){
        this.empEmail=empEmail;
    }
    public String getempSalary(){
        return empSalary;
    }
    public void setempSalary(String empSalary){
        this.empSalary=empSalary;
    }
    public String getempTarget(){
        return empTarget;
    }
    public void setempTarget(String empTarget){
        this.empTarget=empTarget;
    }
}
