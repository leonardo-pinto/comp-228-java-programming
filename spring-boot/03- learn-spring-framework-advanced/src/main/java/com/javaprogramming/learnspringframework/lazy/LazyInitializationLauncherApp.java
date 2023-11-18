package com.javaprogramming.learnspringframework.lazy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
class ClassA {}

@Component
@Lazy
class ClassB {
    private ClassA classA;

    public ClassB(ClassA classA) {
        System.out.println("Some initialization logic Class B");
        this.classA = classA;
    }

    public void doSomething() {
        System.out.println("doSomething in Class B");
    }
}


@Configuration
@ComponentScan("com.javaprogramming.learnspringframework.example")
public class LazyInitializationLauncherApp {
    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(LazyInitializationLauncherApp.class)) {
            System.out.println("Initialization of context is completed");
            context.getBean(ClassB.class).doSomething();
        }
    }
}
