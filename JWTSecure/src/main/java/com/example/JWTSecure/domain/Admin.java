package com.example.JWTSecure.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "admin")
public class Admin {

    @Id
    @SequenceGenerator(
            name = "admin_sequence",
            sequenceName = "admin_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "admin_sequence"
    )
    private Long id;
    @Column(name="user_id")
    private Long userId;
    @Column(name="role_id")
    private Long roleId;
}
