package com.ampinity.inv.Model;

public class EmployeeAdvanceDetails {
    String id;
    String Name;
    String Salary;
    String TotalAdvancement;
    String RemainingSalary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;

    }

    public void setName(String name) {
        Name = name;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getTotalAdvancement() {
        return TotalAdvancement;
    }

    public void setTotalAdvancement(String totalAdvancement) {
        TotalAdvancement = totalAdvancement;
    }

    public String getRemainingSalary() {
        return RemainingSalary;
    }

    public void setRemainingSalary(String remainingSalary) {
        RemainingSalary = remainingSalary;
    }
}
