package com.incetutku.userservice.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document(collection = "users")
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private String surname;
    @Indexed(unique = true)
    private String email;
    private String mobileNumber;
    private UserRole role = UserRole.CUSTOMER;
    private Address address;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
