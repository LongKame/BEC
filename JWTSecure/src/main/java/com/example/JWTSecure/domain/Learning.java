package com.example.JWTSecure.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "learning")
public class Learning {
    @Id
    @SequenceGenerator(
            name = "learning_sequence",
            sequenceName = "learning_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "learning_sequence"
    )
    private Long id;
    @Column(name="student_id")
    private Long studentId;
    @Column(name="curriculum_id")
    private Long curriculumId;
}