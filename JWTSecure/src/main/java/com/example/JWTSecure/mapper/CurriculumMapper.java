package com.example.JWTSecure.mapper;

import com.example.JWTSecure.DTO.CurriculumDTO;
import com.example.JWTSecure.domain.Curriculum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CurriculumMapper {
    private CurriculumMapper() {
        throw new IllegalCallerException("Utilities class");
    }
    private static final ObjectMapper mapper = new ObjectMapper();

    public static CurriculumDTO toDto(Curriculum from) {
        CurriculumDTO to = new CurriculumDTO();
        to.setId(from.getId());
        to.setName(from.getName());
        to.setCourseId(from.getCourseId());
        to.setAcaId(from.getAcaId());
        to.setLinkURL(from.getLinkURL());
        to.setDescription(from.getDescription());
        return to;
    }
}
