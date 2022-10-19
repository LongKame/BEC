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
@Table(name = "guest")
public class Guest {

    @Id
    @SequenceGenerator(
            name = "guest_sequence",
            sequenceName = "guest_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "guest_sequence"
    )
    private Long id;
    @Column(name="role_id")
    private int roleId;
    @Column(name="user_id")
    private Long userId;
    @Column(name="is_paid")
    private boolean isPaid;
}
