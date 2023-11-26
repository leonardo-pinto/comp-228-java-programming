package com.springboot.todowebapp.hello;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String name, String password) {
        return name.equals("leonardo") && password.equals("1234");
    }
}
