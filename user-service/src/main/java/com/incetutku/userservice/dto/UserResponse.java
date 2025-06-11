package com.incetutku.userservice.dto;

import com.incetutku.userservice.model.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String mobileNumber;
    private UserRole role;
    private AddressDto address;
}
