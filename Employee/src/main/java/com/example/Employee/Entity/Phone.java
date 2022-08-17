package com.example.Employee.Entity;

import org.springframework.util.StringUtils;

public class Phone {
    private String phone;

    public String getPhone() {
        return phone;
    }

    public Phone(String phone) {
        setPhone(phone);
        this.phone = "";
    }

    public void setPhone(String phone) {
        try{
            Integer.parseInt(phone);
            this.phone = phone;
        }
        catch (Exception e){
            System.out.println("This is not a Number");
        }
    }
}
