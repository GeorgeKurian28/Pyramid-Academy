package org.example;
import java.util.*;

public class Student {

    private int iD;
    private String name;
    private List<Phone> ph;
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

    public Student(int iD, String name, List<Phone> ph, Address add) {
        this.iD = iD;
        this.name = name;
        this.ph = ph;
        this.add = add;
    }
}
