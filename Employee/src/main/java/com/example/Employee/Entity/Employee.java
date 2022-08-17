package com.example.Employee.Entity;

public class Employee {
    private int employeeID;
    private String employeeName;
    private String employeePhone;

    public Employee(int employeeID, String employeeName, String employeePhone) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }
}
