package com.rest.webservices.restfulwebservices.user;


import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Leonardo", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "John Doe", LocalDate.now().minusYears(21)));
        users.add(new User(++usersCount, "Johnny", LocalDate.now().minusYears(35)));
        users.add(new User(++usersCount, "Jane", LocalDate.now().minusYears(40)));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public void delete(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
