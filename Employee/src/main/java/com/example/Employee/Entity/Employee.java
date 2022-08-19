package com.example.Employee.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tblEmp")
public class Employee {

    @Id
    @Column(name = "e_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeID;
    private String employeeName;
    private String employeePhone;

    public Employee(){

    }

    public Employee( String employeeName, String employeePhone) {
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
