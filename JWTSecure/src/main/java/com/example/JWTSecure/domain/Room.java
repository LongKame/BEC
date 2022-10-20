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
@Table(name = "room")
public class Room {

    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
//    @GeneratedValue(
//            strategy = GenerationType.AUTO,
//            generator = "room_sequence"
//    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="roomname")
    private String roomname;
    @Column(name="capacity")
    private int capacity;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
