package com.example.JWTSecure.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RoomDTO implements Serializable {
    private Long id;
    private String roomname;
    private Integer capacity;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;
    private String key_search;
    private int page;
    private int pageSize;
    private int resultData;
}