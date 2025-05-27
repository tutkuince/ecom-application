package com.incetutku.ecom.dto;

import com.incetutku.ecom.model.UserRole;
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
