package com.example.JWTSecure.service.impl;

import com.example.JWTSecure.domain.Role;
import com.example.JWTSecure.domain.User;
import com.example.JWTSecure.repo.*;
import com.example.JWTSecure.service.TeacherService;
import com.example.JWTSecure.service.UserService;
import com.example.JWTSecure.validate.Validate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AcademicAdminRepo academicAdminRepo;
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private GuestRepo guestRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
//        User user = userRepo.findByUserName(username);
//        Role role = roleRepo.findByName(rolename);
//        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<Role> getRoles() {
        return roleRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        Long userId;
        if (Validate.validateEmail(username)) {
            user = userRepo.findByEmail(username);
            username = user.getUsername();
            userId = user.getId();
        } else {
            user = userRepo.findByUsername(username);
            username = user.getUsername();
            userId = user.getId();
        }
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Set<Role> set = new HashSet<Role>();

        if (adminRepo.findByUserId(userId) != null) {
            set.add(new Role(1L, "ROLE_ADMIN"));
        } else if (academicAdminRepo.findByUserId(userId) != null) {
            set.add(new Role(2L, "ROLE_ACADEMIC_ADMIN"));
        }else if (teacherRepo.findByUserId(userId) != null) {
            set.add(new Role(3L, "ROLE_TEACHER"));
        }else if (studentRepo.findByUserId(userId) != null) {
            set.add(new Role(4L, "ROLE_STUDENT"));
        }else {
            set.add(new Role(5L, "ROLE_GUEST"));
        }

        set.stream().forEach(i -> authorities.add(new SimpleGrantedAuthority(i.getName())));

        List<String> list = new ArrayList<>();
        list.add(user.getUsername());
        list.add(user.getFullname());

        Map<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("name", user.getFullname());
        return new org.springframework.security.core.userdetails.User(map.toString(), user.getPassword(), authorities);
    }
}
