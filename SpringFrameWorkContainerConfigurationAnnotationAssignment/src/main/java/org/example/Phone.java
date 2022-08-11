package org.example;

import org.springframework.stereotype.Component;


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
