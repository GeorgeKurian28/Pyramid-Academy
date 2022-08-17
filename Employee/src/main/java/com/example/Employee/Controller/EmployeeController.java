package com.example.Employee.Controller;

import com.example.Employee.Entity.Employee;
import com.example.Employee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/home")
    public String display(@RequestParam(value = "name", defaultValue = "Supervisor") String name){
        return"<Html><h1>Welcome  " + name +"!!!</h1></Html>";
    }

    @GetMapping("/employee")
    public List<Employee> showAllEmployees(){
        return this.employeeService.showAllEmployees();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee showEmployee(@PathVariable String employeeId){
        return this.employeeService.showEmployee(Integer.parseInt(employeeId));
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee emp){
        return this.employeeService.addEmployee(emp);
    }
    @PutMapping("/employee")
    public Employee updateEmployee(@RequestBody Employee emp){
        return this.employeeService.updateEmployee(emp);
    }

    @DeleteMapping("/employee/{employeeId}")
    public String removeEmployee(@PathVariable int employeeId){
        return this.employeeService.removeEmployee(employeeId);
    }
}
