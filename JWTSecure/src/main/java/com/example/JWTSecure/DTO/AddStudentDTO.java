package com.example.JWTSecure.DTO;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AddStudentDTO {

    private Long id;
    private String username;
    private String fullname;
    private String password;
    private String email;
    private String phone;
    private String address;
    private Long classId;
    private String classname;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String message;
    private boolean state;
}
