package com.incetutku.userservice.service;

import com.incetutku.userservice.dto.AddressDto;
import com.incetutku.userservice.dto.UserRequest;
import com.incetutku.userservice.dto.UserResponse;
import com.incetutku.userservice.model.Address;
import com.incetutku.userservice.model.User;
import com.incetutku.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addUser(UserRequest userRequest) {
        User user = new User();
        updateUserFromRequest(user, userRequest);
        userRepository.save(user);
    }

    public boolean updateUser(String id, UserRequest userRequest) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    updateUserFromRequest(existingUser, userRequest);
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }

    public List<UserResponse> fetchAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public Optional<UserResponse> fetchUserById(String id) {
        return userRepository.findById(id).map(this::mapToUserResponse);
    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setMobileNumber(userRequest.getMobileNumber());

        if (!Objects.isNull(userRequest.getAddress())) {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZipcode(userRequest.getAddress().getZipcode());

            user.setAddress(address);
        }
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setEmail(user.getEmail());
        response.setMobileNumber(user.getMobileNumber());
        response.setRole(user.getRole());

        if (!Objects.isNull(user.getAddress())) {
            AddressDto addressDto = new AddressDto();
            addressDto.setStreet(user.getAddress().getStreet());
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setState(user.getAddress().getState());
            addressDto.setCountry(user.getAddress().getCountry());
            addressDto.setZipcode(user.getAddress().getZipcode());

            response.setAddress(addressDto);
        }

        return response;
    }
}
