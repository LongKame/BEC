package com.example.JWTSecure.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "slot")
public class Slot {

    @Id
    @SequenceGenerator(
            name = "slot_sequence",
            sequenceName = "slot_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "slot_sequence"
    )
    private Long id;
    @Column(name="room_id")
    private int roomId;
    @Column(name="class_id")
    private Long classId;
    @Column(name="activities_id")
    private Long activitiesId;
    @Column(name="course_id")
    private Long courseId;
    @Column(name="curriculum_id")
    private Long curriculumId;
    @Column(name="from_time")
    private LocalDateTime fromTime;
    @Column(name="to_time")
    private LocalDateTime toTime;
    @Column(name="day")
    private Date day;
}
