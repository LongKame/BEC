package com.example.JWTSecure.service.impl;

import com.example.JWTSecure.DTO.*;
import com.example.JWTSecure.domain.Activity;
import com.example.JWTSecure.domain.Teacher;
import com.example.JWTSecure.domain.User;
import com.example.JWTSecure.repo.AcademicAdminRepo;
import com.example.JWTSecure.repo.ActivityRepo;
import com.example.JWTSecure.repo.TeacherRepo;
import com.example.JWTSecure.repo.UserRepo;
import com.example.JWTSecure.repo.impl.TeacherCustomRepo;
import com.example.JWTSecure.service.TeacherService;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TeacherServiceImpl implements TeacherService {


    private final PasswordEncoder passwordEncoder;
    private final TeacherRepo teacherRepo;
    private final UserRepo userRepo;
    private final TeacherCustomRepo teacherCustomRepo;
    private final ActivityRepo activityRepo;

    public SearchResultDTO<TeacherDTO> getAllTeacher(TeacherDTO productDTO) {
        List<TeacherDTO> dataResult;
        SearchResultDTO<TeacherDTO> searchResult = new SearchResultDTO<>();
        try {
            Integer totalRecord = teacherCustomRepo.doSearch(productDTO).size();
            dataResult = teacherCustomRepo.doSearch(productDTO);
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
    public ResponseStatus addTeacher(AddTeacherDTO addTeacherDTO) {
        AddTeacherDTO dto = new AddTeacherDTO();
        User user = new User();
        Teacher teacher = new Teacher();


        ResponseStatus rs = new ResponseStatus();
        StringBuilder message = new StringBuilder();
        try {
            if (addTeacherDTO != null) {

                if (userRepo.findByUsername(addTeacherDTO.getUsername()) != null) {
                    message.append("Username ");
                }
                if (userRepo.findByEmail(addTeacherDTO.getEmail()) != null) {
                    message.append("Email ");
                }
                if (userRepo.findByPhone(addTeacherDTO.getPhone()) != null) {
                    message.append("Phone ");
                }

                if (userRepo.findByUsername(addTeacherDTO.getUsername()) == null
                        || userRepo.findByEmail(addTeacherDTO.getEmail()) == null
                        || userRepo.findByPhone(addTeacherDTO.getPhone()) == null) {
                    user.setUsername(addTeacherDTO.getUsername());
                    user.setFullname(addTeacherDTO.getFullname());
                    user.setPassword(passwordEncoder.encode(addTeacherDTO.getPassword()));
                    user.setEmail(addTeacherDTO.getEmail());
                    user.setPhone(addTeacherDTO.getPhone());
                    user.setAddress(addTeacherDTO.getAddress());
                    user.setActive(addTeacherDTO.isActive());
                    userRepo.save(user);

                    teacher.setUserId(userRepo.findTopByOrderByIdDesc().getId());
                    teacher.setRoleId(3L);
                    teacherRepo.save(teacher);
                    rs.setMessage("Ok");
                    rs.setState(true);
                }

                if (!StringUtils.isBlank(message)) {
                    message.append("is existed");
                    rs.setMessage(message.toString());
                    rs.setState(false);
                }
            } else {
                rs.setMessage("Failure");
                rs.setState(false);
            }
            return rs;
        } catch (Exception ex) {
            rs.setMessage("Failure");
            rs.setState(false);
            return rs;
        }
    }

    @Override
    public TeacherDTO viewProfile(TeacherDTO teacherDTO) {
        try {
            return teacherCustomRepo.getTeacher(teacherDTO);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ResponseStatus editTeacher(AddTeacherDTO addTeacherDTO) {
        User user = new User();
        Teacher teacher = new Teacher();
        ResponseStatus rs = new ResponseStatus();
        StringBuilder message = new StringBuilder();


        if (addTeacherDTO != null) {
            if (userRepo.findByUsername(addTeacherDTO.getUsername()) != null) {
                message.append("Username ");
            }
            if (userRepo.findByEmail(addTeacherDTO.getEmail()) != null) {
                message.append("Email ");
            }
            if (!StringUtils.isBlank(message)) {
                message.append("is existed");
                rs.setMessage(message.toString());
                rs.setState(false);
                return rs;
            }

            try {
                if (userRepo.findByUsername(addTeacherDTO.getUsername()) == null) {
                    if (userRepo.findByEmail(addTeacherDTO.getEmail()) == null) {
                        teacher.setUserId(addTeacherDTO.getId());
                        teacher.setRoleId(4L);
                        teacherRepo.save(teacher);
                        user.setId(addTeacherDTO.getId());
                        user.setUsername(userRepo.findById(addTeacherDTO.getId()).get().getUsername());
                        user.setFullname(addTeacherDTO.getFullname());
                        user.setPassword(userRepo.findById(addTeacherDTO.getId()).get().getPassword());
                        user.setEmail(userRepo.findById(addTeacherDTO.getId()).get().getEmail());
                        user.setPhone(addTeacherDTO.getPhone());
                        user.setAddress(addTeacherDTO.getAddress());
                        user.setActive(addTeacherDTO.isActive());
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

    @Override
    public ResponseStatus createAct(Activity activity) {
        ResponseStatus responseStatus = new ResponseStatus();
        try {
            String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(timeStamp, formatter);
            activity.setCreatedAt(localDateTime);
            activity.setUpdatedAt(localDateTime);
            activityRepo.save(activity);
            responseStatus.setMessage("Ok");
            responseStatus.setState(true);
            return responseStatus;
        } catch (Exception e) {
            responseStatus.setMessage("Failure");
            responseStatus.setState(false);
            return responseStatus;
        }
    }

    @Override
    public ResponseStatus deleteTeacher(AddTeacherDTO addTeacherDTO) {
        ResponseStatus responseStatus = new ResponseStatus();
        try {
            Long userId = teacherRepo.findById(addTeacherDTO.getId()).get().getUserId();
            userRepo.deActive(false, userId);
            responseStatus.setMessage("Ok");
            responseStatus.setState(true);
            return responseStatus;
        } catch (Exception e) {
            responseStatus.setMessage("Failure");
            responseStatus.setState(false);
            return responseStatus;
        }
    }

}
