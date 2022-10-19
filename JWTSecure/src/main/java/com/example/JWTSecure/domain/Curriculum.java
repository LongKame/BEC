package com.example.JWTSecure.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "curriculum")
public class Curriculum {

    @Id
    @SequenceGenerator(
            name = "curriculum_sequence",
            sequenceName = "curriculum_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "curriculum_sequence"
    )
    private Long id;
    @Column(name="aca_id")
    private Long acaId;
    @Column(name="course_id")
    private Long courseId;
    @Column(name="name")
    private String name;
    @Column(name="link_url")
    private String linkURL;
    @Column(name="description")
    private String description;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}