package com.example.Employee.Service;

import com.example.Employee.DAO.EmployeeDAO;
import com.example.Employee.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDAO employeeDAO;
    @Override
    public List<Employee> showAllEmployees() {
        return this.employeeDAO.findAll();
    }

    @Override
    public Employee showEmployee(int employeeId) {
        Employee emp = null;
        Optional<Employee> opt = this.employeeDAO.findById(employeeId);
        if(opt.isPresent()){
            emp = opt.get();
        }
        else {
            throw new RuntimeException("There is no employee with ID - "+ employeeId);
        }
        return emp;
    }

    @Override
    public Employee addEmployee(Employee emp) {
        return this.employeeDAO.save(emp);
    }

    @Override
    public Employee updateEmployee(Employee emp) {
        return this.employeeDAO.save(emp);
    }

    @Override
    public String removeEmployee(int employeeID) {
        this.employeeDAO.deleteById(employeeID);
        return "Succesfully Removed";
    }
}
