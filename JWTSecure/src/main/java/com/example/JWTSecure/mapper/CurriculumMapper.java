package com.example.JWTSecure.mapper;

import com.example.JWTSecure.DTO.CurriculumDTO;
import com.example.JWTSecure.domain.Curriculum;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CurriculumMapper {
    private CurriculumMapper() {
        throw new IllegalCallerException("Utilities class");
    }
    private static final ObjectMapper mapper = new ObjectMapper();

    public static CurriculumDTO toDto(Curriculum from) {
//        CurriculumDTO to = new CurriculumDTO();
        return mapper.convertValue(from, CurriculumDTO.class);
    }
}
