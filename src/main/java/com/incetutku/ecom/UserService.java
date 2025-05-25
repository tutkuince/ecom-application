package com.incetutku.ecom;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();
    private Long nextId = 1L;

    public List<User> fetchAllUsers() {
        return userList;
    }

    public void addUser(User user) {
        user.setId(nextId++);
        userList.add(user);
    }

    public User fetchUserById(Long id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(RuntimeException::new);
    }
}
