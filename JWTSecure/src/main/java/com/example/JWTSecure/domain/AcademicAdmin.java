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
@Table(name = "academic_admin")
public class AcademicAdmin {

    @Id
    @SequenceGenerator(
            name = "academicadmin_sequence",
            sequenceName = "academicadmin_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "academicadmin_sequence"
    )
    private Long id;
    @Column(name="user_id")
    private Long userId;
    @Column(name="role_id")
    private Long roleId;
}
