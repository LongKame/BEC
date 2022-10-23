package com.example.JWTSecure.service.impl;

import com.example.JWTSecure.DTO.*;
import com.example.JWTSecure.domain.Student;
import com.example.JWTSecure.domain.User;
import com.example.JWTSecure.repo.StudentRepo;
import com.example.JWTSecure.repo.UserRepo;
import com.example.JWTSecure.repo.impl.StudentCustomRepo;
import com.example.JWTSecure.service.StudentService;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final PasswordEncoder passwordEncoder;
    private final StudentRepo studentRepo;
    private final UserRepo userRepo;
    private final StudentCustomRepo studentCustomRepo;

    @Override
    public StudentDTO getStudent(StudentDTO studentDTO) {
        try {
            return studentCustomRepo.getStudent(studentDTO);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public SearchResultDTO<StudentDTO> getAllStudent(StudentDTO studentDTO) {
        List<StudentDTO> dataResult;
        SearchResultDTO<StudentDTO> searchResult = new SearchResultDTO<>();
        try {
            Integer totalRecord = studentCustomRepo.doSearch(studentDTO).size();
            dataResult = studentCustomRepo.doSearch(studentDTO);
            if (dataResult != null && !dataResult.isEmpty()) {
                searchResult.setCode("0");
                searchResult.setSuccess(true);
                searchResult.setTitle("Success");
                searchResult.setMessage("Success");
                searchResult.setResultData(dataResult);
                searchResult.setTotalRecordNoLimit(totalRecord);
            } else {
                searchResult.setCode("0");
                searchResult.setSuccess(false);
                searchResult.setTitle("Failure");
                searchResult.setMessage("Failure");
                searchResult.setResultData(Collections.emptyList());
                searchResult.setTotalRecordNoLimit(0);
            }
            return searchResult;
        } catch (Exception e) {
            searchResult.setCode("0");
            searchResult.setSuccess(false);
            searchResult.setTitle("Failure");
            searchResult.setMessage("Failure");
            searchResult.setResultData(Collections.emptyList());
            searchResult.setTotalRecordNoLimit(0);
            return searchResult;
        }
    }

    @Override
    public ResponseStatus addStudent(AddStudentDTO addStudentDTO) {
        User user = new User();
        Student student = new Student();
        ResponseStatus rs = new ResponseStatus();
        StringBuilder message = new StringBuilder();

        if (addStudentDTO != null) {
            if (userRepo.findByUsername(addStudentDTO.getUsername()) != null) {
                message.append("Username ");
            }
            if (userRepo.findByEmail(addStudentDTO.getEmail()) != null) {
                message.append("Email ");
            }
            if (!StringUtils.isBlank(message)) {
                message.append("is existed");
                rs.setMessage(message.toString());
                rs.setState(false);
                return rs;
            }
            try {
                if (userRepo.findByUsername(addStudentDTO.getUsername()) == null) {
                    if (userRepo.findByEmail(addStudentDTO.getEmail()) == null) {
                        if (userRepo.findByPhone(addStudentDTO.getPhone()) == null) {
                            user.setUsername(addStudentDTO.getUsername());
                            user.setFullname(addStudentDTO.getFullname());
                            user.setPassword(passwordEncoder.encode(addStudentDTO.getPassword()));
                            user.setEmail(addStudentDTO.getEmail());
                            user.setPhone(addStudentDTO.getPhone());
                            user.setAddress(addStudentDTO.getAddress());
                            user.setActive(addStudentDTO.isActive());
                            userRepo.save(user);

                            student.setUserId(userRepo.findTopByOrderByIdDesc().getId());
                            student.setRoleId(4L);
                            student.setClassId(addStudentDTO.getClassId());

                            studentRepo.save(student);
                            rs.setMessage("Ok");
                            rs.setState(true);
                        }
                    }
                } else {
                    rs.setMessage("Failure");
                    rs.setState(false);
                }
            } catch (Exception ex) {
                rs.setMessage("Failure");
                rs.setState(false);
            }
        }
        return rs;
    }


    @Override
    public ResponseStatus editStudent(AddStudentDTO addStudentDTO) {
        User user = new User();
        Student student = new Student();
        ResponseStatus rs = new ResponseStatus();
        StringBuilder message = new StringBuilder();

        if (addStudentDTO != null) {
            if (userRepo.findByUsername(addStudentDTO.getUsername()) != null) {
                message.append("Username ");
            }
            if (userRepo.findByEmail(addStudentDTO.getEmail()) != null) {
                message.append("Email ");
            }
            if (!StringUtils.isBlank(message)) {
                message.append("is existed");
                rs.setMessage(message.toString());
                rs.setState(false);
                return rs;
            }

            try {
                if (userRepo.findByUsername(addStudentDTO.getUsername()) == null) {
                    if (userRepo.findByEmail(addStudentDTO.getEmail()) == null) {
                        student.setUserId(addStudentDTO.getId());
                        student.setRoleId(4L);
                        student.setClassId(addStudentDTO.getClassId());
                        studentRepo.save(student);

                        user.setId(addStudentDTO.getId());
                        user.setUsername(addStudentDTO.getUsername());
                        user.setFullname(addStudentDTO.getFullname());
                        user.setPassword(userRepo.findById(addStudentDTO.getId()).get().getPassword());
                        user.setEmail(addStudentDTO.getEmail());
                        user.setPhone(addStudentDTO.getPhone());
                        user.setAddress(addStudentDTO.getAddress());
                        user.setActive(addStudentDTO.isActive());
                        userRepo.save(user);
                        rs.setMessage("Ok");
                        rs.setState(true);
                    }
                } else {
                    rs.setMessage("Failure");
                    rs.setState(false);
                }
            } catch (Exception ex) {
                rs.setMessage("Failure");
                rs.setState(false);
            }
        }
        return rs;
    }

}

