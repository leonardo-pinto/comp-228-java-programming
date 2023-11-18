package com.javaprogramming.learnspringframework.cdi;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// @Component
@Named
class BusinessService {
    private DataService dataservice;

    public DataService getDataservice() {
        return dataservice;
    }

//    @Autowired
    @Inject
    public void setDataservice(DataService dataservice) {
        System.out.println("Setter injection performed");
        this.dataservice = dataservice;
    }
}

//@Component
@Named
class DataService {}


@Configuration
@ComponentScan
public class CdiContextLauncherApplication {
    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(CdiContextLauncherApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(context.getBean(BusinessService.class).getDataservice());
        }
    }
}
