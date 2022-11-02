package com.example.JWTSecure.mapper;

import com.example.JWTSecure.DTO.SlotDTO;
import com.example.JWTSecure.domain.Slot;

import java.sql.Date;

public class SlotMapper {
    private SlotMapper() {
        throw new IllegalCallerException("Utilities class");
    }

    public static SlotDTO toDto(Slot from) {
        return SlotDTO.builder()
                .id(from.getId())
                .roomId(from.getRoomId())
                .classId(from.getClassId())
                .activitiesId(from.getActivitiesId())
                .courseId(from.getCourseId())
                .curriculumId(from.getCurriculumId())
                .fromTime(from.getFromTime())
                .toTime(from.getToTime())
                .day(from.getDay())
                .build();
    }

    public static Slot toEntity(SlotDTO dto) {
        Slot entity = new Slot();
        entity.setId(dto.getId());
        entity.setRoomId(dto.getRoomId());
        entity.setClassId(dto.getClassId());
        entity.setActivitiesId(dto.getActivitiesId());
        entity.setCourseId(dto.getCourseId());
        entity.setCurriculumId(dto.getCurriculumId());
        entity.setFromTime(dto.getFromTime());
        entity.setToTime(dto.getToTime());
        entity.setDay(new Date(dto.getDay().getTime()));
        return entity;
    }
}
