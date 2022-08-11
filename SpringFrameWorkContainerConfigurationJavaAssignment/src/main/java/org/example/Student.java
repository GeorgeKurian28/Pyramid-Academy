package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

public class Student {

    @Value("20")
    private int iD;
    @Value("George Kurian")
    private String name;
    @Autowired
    private List<Phone> ph;
    @Autowired
    private Address add;



    @Override
    public String toString() {
        return "Student{" +
                "iD=" + iD +
                ", name='" + name + '\'' +
                ", ph=" + ph +
                ", add=" + add +
                '}';
    }
}
