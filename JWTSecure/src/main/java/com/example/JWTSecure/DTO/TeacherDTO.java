package com.example.JWTSecure.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class TeacherDTO implements Serializable {

    private Long teacher_Id;
    private Long user_Id;
    private Long role_Id;
    private String user_name;
    private String full_name;
    private String email;
    private String phone;
    private String address;
    private boolean active;
    private String key_search;
    private int page;
    private int pageSize;
    private int resultData;
}
