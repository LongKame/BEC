package com.example.JWTSecure.domain;

import com.example.JWTSecure.repo.UserRepo;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "users_sequence"
    )
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="fullname")
    private String fullname;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private String phone;
    @Column(name="address")
    private String address;
    @Column(name="active")
    private boolean active;

    public User(Long id, String username, String fullname, String password, String email, String phone, String address, boolean active) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.active = active;
    }
}

