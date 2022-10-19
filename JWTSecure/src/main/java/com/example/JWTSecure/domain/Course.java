package com.example.JWTSecure.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "course")
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "course_sequence"
    )
    private Long id;
    @Column(name="level_id")
    private Long levelId;
    @Column(name="name")
    private String name;
    @Column(name="curriculum_id")
    private Long curriculumId;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
