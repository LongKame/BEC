package com.example.JWTSecure.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "student_sequence"
    )
    private Long id;
    @Column(name="user_id")
    private Long userId;
    @Column(name="role_id")
    private Long roleId;
    @Column(name="class_id")
    private Long classId;
}

