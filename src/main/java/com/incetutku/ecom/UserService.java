package com.incetutku.ecom;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();
    private Long nextId = 1L;

    public void addUser(User user) {
        user.setId(nextId++);
        userList.add(user);
    }

    public boolean updateUser(Long id, User updatedUser) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setName(updatedUser.getName());
                    existingUser.setSurname(updatedUser.getSurname());
                    return true;
                }).orElse(false);
    }

    public List<User> fetchAllUsers() {
        return userList;
    }

    public Optional<User> fetchUserById(Long id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst();
    }




}
