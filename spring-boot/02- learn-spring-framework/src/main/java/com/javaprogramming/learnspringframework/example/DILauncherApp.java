package com.javaprogramming.learnspringframework.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
class YourBusinessClass {
//    @Autowired
    Dependency1 dependency1;
//    @Autowired

    Dependency2 dependency2;

    @Autowired
    public YourBusinessClass(Dependency1 dependency1, Dependency2 dependency2) {
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
    }

    //    @Autowired
//    public void setDependency1(Dependency1 dependency1) {
//        this.dependency1 = dependency1;
//    }
//
//    @Autowired
//    public void setDependency2(Dependency2 dependency2) {
//        this.dependency2 = dependency2;
//    }

    @Override
    public String toString() {
        return "YourBusinessClass{" +
                "dependency1=" + dependency1 +
                ", dependency2=" + dependency2 +
                '}';
    }
}

@Component
class Dependency1 {}

@Component
class Dependency2{}

@Configuration
@ComponentScan("com.javaprogramming.learnspringframework.example")
public class DILauncherApp {
    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(DILauncherApp.class)) {
            for (String bean:context.getBeanDefinitionNames()) {
                System.out.println(bean);
            }

            System.out.println(context.getBean(YourBusinessClass.class));
        }
    }
}
