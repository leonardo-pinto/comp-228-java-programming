package com.javaprogramming.learnspringframework.exercise;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.javaprogramming.learnspringframework.exercise")
public class LauncherApp {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(LauncherApp.class)) {
            System.out.println(context.getBean(BusinessCalculationService.class).findMax());
        }
    }
}
