package com.javaprogramming.learnspringframework.helloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


record Person(String name, int age){};
record Address(String firstLine, String city){}

@Configuration // define Spring Beans
public class HelloWorldConfiguration {


    @Bean
    public String name() {
        return "Leonardo";
    }

    @Bean
    public String nameDefault() {
        return "Foo";
    }

    @Bean
    public int age() {
        return 30;
    }

    @Bean
    public Person person() {
        return new Person("John", 38);
    }

    @Bean
    public Person personNestedCall() {
        return new Person(name(), age()); // reusing Beans
    }

    @Bean
    @Primary //makes the bean name default
    public Person personParameterized(String nameDefault, int age) {
        return new Person(nameDefault, age);
    }

    @Bean(name = "customAddressBean")
    public Address address() {
        return new Address("Conway Avenue", "Toronto");
    }
}
