package com.example.JWTSecure.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlotDTO {
    private Long id;
    private Integer roomId;
    private Long classId;
    private Long activitiesId;
    private Long courseId;
    private Long curriculumId;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fromTime;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime toTime;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date day;
}
