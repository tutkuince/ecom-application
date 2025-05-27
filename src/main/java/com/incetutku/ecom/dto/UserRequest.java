package com.incetutku.ecom.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String surname;
    private String email;
    private String mobileNumber;
    private AddressDto address;
}
