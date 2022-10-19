package com.example.JWTSecure.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResultDTO<T> {

    private String code;
    private boolean success;
    private String title;
    private String message;
    private List<T> resultData;
    private Integer totalRecordNoLimit;

}
