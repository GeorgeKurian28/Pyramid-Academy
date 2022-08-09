package org.example;

public class Phone {
    @Override
    public String toString() {
        return "Phone{" +
                "mob='" + mob + '\'' +
                '}';
    }

    public Phone(String mob) {
        this.mob = mob;
    }

    private String mob;
}
