package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;

import java.util.Scanner;

@Configuration
public class AppConfig {
    Scanner scan = new Scanner(System.in);
    @Bean
    public Student getStudent(){
        return new Student();
    }

    @Bean
    public Address getAddress(){
        return new Address();
    }
    @Bean
    public List<Phone> getListPhone(){
        return Arrays.asList(new Phone("8378273"),new Phone("40980980"));
    }

}
