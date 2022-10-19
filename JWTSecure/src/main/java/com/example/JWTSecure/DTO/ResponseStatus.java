package com.example.JWTSecure.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseStatus {
    private String message;
    private boolean state;
}
