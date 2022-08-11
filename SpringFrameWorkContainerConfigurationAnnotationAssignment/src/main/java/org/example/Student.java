package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

public class Student {
    private int iD;
    private String name;

    @Autowired
    private List<Phone> ph;
    @Autowired
    private Address add;

    public Student(List<Phone> ph) {
        this.ph = ph;
    }

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
