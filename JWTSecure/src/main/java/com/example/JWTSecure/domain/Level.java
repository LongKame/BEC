package com.example.JWTSecure.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "level")
public class Level {
    @Id
    @SequenceGenerator(
            name = "level_sequence",
            sequenceName = "level_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "level_sequence"
    )
    private Long id;
    @Column(name="name")
    private String name;
}
