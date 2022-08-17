package com.example.Employee.Service;

import com.example.Employee.Entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private List<Employee> list;

    public EmployeeServiceImpl() {
        list = new ArrayList<>();
        list.add(new Employee(101,"George Kurian","5463456789"));
        list.add(new Employee(102,"Melvin Kurian","5863456789"));
        list.add(new Employee(103,"Ryan Goslin","7893456789"));
        list.add(new Employee(104,"Kate Simmer","5493456789"));
    }

    @Override
    public List<Employee> showAllEmployees() {
        return list;
    }

    @Override
    public Employee showEmployee(int employeeId) {
        Employee emp = null;
        for(Employee e : list)
            if(employeeId == e.getEmployeeID()){
                emp =  e;
                break;
            }
        return emp;
    }

    @Override
    public Employee addEmployee(Employee emp) {
        list.add(emp);
        return emp;
    }

    @Override
    public Employee updateEmployee(Employee emp) {
        for(Employee e: list){
            if(e.getEmployeeID() == emp.getEmployeeID()){
                e.setEmployeeName(emp.getEmployeeName());
                e.setEmployeePhone(emp.getEmployeePhone());
                break;
            }
        }
        return emp;
    }

    @Override
    public String removeEmployee(int employeeID) {
        for(Employee e : list){
            if(e.getEmployeeID() == employeeID){
                list.remove(e);
                break;
            }
        }
        return "Succesfully Removed";
    }
}
