package org.example;

import org.springframework.stereotype.Component;

import java.util.Scanner;


public class Phone {
    public Phone(String mob) {
        this.mob = mob;
    }

    private String mob;

    @Override
    public String toString() {
        return "Phone{" +
                "mob='" + mob + '\'' +
                '}';
    }
}
