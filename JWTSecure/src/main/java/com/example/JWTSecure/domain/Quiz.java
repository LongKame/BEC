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
@Table(name = "quiz")
public class Quiz {

    @Id
    @SequenceGenerator(
            name = "quiz_sequence",
            sequenceName = "quiz_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "quiz_sequence"
    )
    private Long id;
    @Column(name="aca_id")
    private Long acaId;
    @Column(name="level_id")
    private Long levelId;
    @Column(name="question")
    private String question;
    @Column(name="answer_a")
    private String answerA;
    @Column(name="answer_b")
    private String answerB;
    @Column(name="answer_c")
    private String answerC;
    @Column(name="answer_d")
    private String answerD;
    @Column(name="correct")
    private String correct;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
