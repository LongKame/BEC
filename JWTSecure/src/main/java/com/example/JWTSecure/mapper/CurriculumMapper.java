package com.example.JWTSecure.mapper;

import com.example.JWTSecure.DTO.CurriculumDTO;
import com.example.JWTSecure.domain.Curriculum;

public class CurriculumMapper {
    private CurriculumMapper() {
        throw new IllegalCallerException("Utilities class");
    }

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
