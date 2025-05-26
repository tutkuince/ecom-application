package com.incetutku.ecom.service;

import com.incetutku.ecom.repository.UserRepository;
import com.incetutku.ecom.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(updatedUser.getName());
                    existingUser.setSurname(updatedUser.getSurname());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setMobileNumber(updatedUser.getMobileNumber());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> fetchUserById(Long id) {
        return userRepository.findById(id);
    }


}
