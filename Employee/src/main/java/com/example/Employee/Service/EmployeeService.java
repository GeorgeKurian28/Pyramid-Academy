package com.example.Employee.Service;

import com.example.Employee.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    //View all employee
    List<Employee> showAllEmployees();
    //View employee by ID
    Employee showEmployee(int employeeId);
    //Add employee
    Employee addEmployee(Employee emp);
    //Update Employee
    Employee updateEmployee(Employee emp);
    //remove empolyee
    String removeEmployee(int employeeID);
}
