package com.example.JWTSecure.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "class")
public class Class {

    @Id
    @SequenceGenerator(
            name = "class_sequence",
            sequenceName = "class_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "class_sequence"
    )
    private Long id;
    @Column(name="room_id")
    private String roomId;
    @Column(name="teacher_id")
    private Long teacherId;
    @Column(name="name")
    private String name;
    @Column(name="number_of_student")
    private int numberOfStudent;
    @Column(name="start_date")
    private Date startDate;
}
