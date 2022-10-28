package com.example.JWTSecure.DTO;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultDTO<T> {

    private String code;
    private boolean success;
    private String title;
    private String message;
    private List<T> resultData = new ArrayList<>();
    private Integer totalRecordNoLimit;

    public SearchResultDTO<T> addSingleResultData(T element) {
        resultData.add(element);
        return this;
    }

    public SearchResultDTO<T> addListResultData(List<T> data) {
        resultData.addAll(data);
        return this;
    }

    public static <T> SearchResultDTO<T> defaultSuccess() {
        return new SearchResultDTO<T>("0", true, "success",
                "success", new ArrayList<>(), 1);
    }

    public static <T> SearchResultDTO<T> defaultNotFound() {
        return new SearchResultDTO<T>("-1", true, "fail",
                "not found", new ArrayList<>(), 0);
    }
}
