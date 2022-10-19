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
@Table(name = "activity")
public class Activity {
    @Id
    @SequenceGenerator(
            name = "activity_sequence",
            sequenceName = "activity_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "activity_sequence"
    )
    private Long id;
    @Column(name="question")
    private String question;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

}
