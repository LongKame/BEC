package com.example.JWTSecure.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurriculumDTO {
    private Long id;
    private Long acaId;
    private Long courseId;
    private String name;
    private String linkURL;
    private String description;
}
