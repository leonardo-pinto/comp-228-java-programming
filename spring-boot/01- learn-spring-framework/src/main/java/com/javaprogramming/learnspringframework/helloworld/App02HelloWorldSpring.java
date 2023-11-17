package com.javaprogramming.learnspringframework.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {
    public static void main(String[] args) {
        // 1. Launch a Spring Context
        try( var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)) {
            // 2. Configure the things that we want Spring to manage @Configuration
            // HelloWorldConfiguration - @Configuration
            // name - @Bean

            // 3. Retrieving Beans managed by Spring
            //        System.out.println(context.getBean("name"));
            //        System.out.println(context.getBean("age"));
            //        System.out.println(context.getBean("person"));
            //        System.out.println(context.getBean("personNestedCall"));
            //        System.out.println(context.getBean("personParameterized"));
            //        System.out.println(context.getBean("customAddressBean"));
                        System.out.println(context.getBean(Person.class));
            //        for (String bean:context.getBeanDefinitionNames()) {
            //            System.out.println(bean);
        }
    }
}
