package com.ampinity.inv.Model;

import java.util.ArrayList;

public class EmployeeAttendanceDetails extends ArrayList<EmployeeAttendanceDetails> {
    String Id;
    String Name;
    String Attendance;
    String Date;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAttendance() {
        return Attendance;
    }

    public void setAttendance(String attendance) {
        Attendance = attendance;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
