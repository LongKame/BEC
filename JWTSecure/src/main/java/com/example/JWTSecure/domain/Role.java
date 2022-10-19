package com.example.JWTSecure.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "role_sequence"
    )
    private Long id;
    @Column(name="name")
    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
