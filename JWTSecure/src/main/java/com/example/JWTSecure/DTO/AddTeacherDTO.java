package com.example.JWTSecure.DTO;

import com.example.JWTSecure.domain.Teacher;
import com.example.JWTSecure.domain.User;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
public class AddTeacherDTO {

    private Long id;
    private String username;
    private String fullname;
    private String password;
    private String email;
    private String phone;
    private String address;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String message;
    private boolean state;
}
