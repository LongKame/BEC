package com.example.JWTSecure.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ClassDTO implements Serializable {
    private Long class_id;
    private String class_name;
    private Long room_id;
    private String room_name;
    private Long user_id;
    private Long teacher_id;
    private String full_name;
    private String email;
    private Integer number_of_student;
    private Integer capacity;
    private LocalDateTime start_date;
    private boolean active_room;
    private int page;
    private int pageSize;
    private String key_search;
    private int resultData;
}