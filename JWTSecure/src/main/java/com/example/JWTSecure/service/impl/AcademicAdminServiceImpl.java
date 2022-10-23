package com.example.JWTSecure.service.impl;

import com.example.JWTSecure.DTO.*;
import com.example.JWTSecure.domain.*;
import com.example.JWTSecure.repo.AcademicAdminRepo;
import com.example.JWTSecure.repo.QuizRepo;
import com.example.JWTSecure.repo.StudentRepo;
import com.example.JWTSecure.repo.UserRepo;
import com.example.JWTSecure.repo.impl.AcademicAdminCustomRepo;
import com.example.JWTSecure.repo.impl.QuizCustomRepo;
import com.example.JWTSecure.repo.impl.RoomCustomRepo;
import com.example.JWTSecure.service.AcademicAdminService;
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
public class AcademicAdminServiceImpl implements AcademicAdminService {

    private final PasswordEncoder passwordEncoder;
    private final StudentRepo studentRepo;
    private final AcademicAdminRepo academicAdminRepo;
    private final QuizRepo quizRepo;
    private final AcademicAdminCustomRepo academicAdminCustomRepo;
    private final UserRepo userRepo;
    private final AcademicAdminRepo acadRepo;
    private final QuizCustomRepo quizCustomRepo;
    private final RoomCustomRepo roomCustomRepo;

    @Override
    public List<Quiz> getQuiz(Long levelId) {
        return quizCustomRepo.doSearch(levelId);
    }

    @Override
    public SearchResultDTO<RoomDTO> getRoom(RoomDTO roomDTO) {
        List<RoomDTO> dataResult;
        SearchResultDTO<RoomDTO> searchResult = new SearchResultDTO<>();
        try {
            Integer totalRecord = roomCustomRepo.doSearch(roomDTO).size();
            dataResult = roomCustomRepo.doSearch(roomDTO);
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
    public SearchResultDTO<AcademicAdminDTO> getAllAcad(AcademicAdminDTO academicAdminDTO) {
        List<AcademicAdminDTO> dataResult;
        SearchResultDTO<AcademicAdminDTO> searchResult = new SearchResultDTO<>();
        try {
            Integer totalRecord = academicAdminCustomRepo.doSearch(academicAdminDTO).size();
            dataResult = academicAdminCustomRepo.doSearch(academicAdminDTO);
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
    public ResponseStatus addAcad(AddAcademicAdminDTO addAcademicAdminDTO) {

        User user = new User();
        AcademicAdmin academicAdmin = new AcademicAdmin();
        ResponseStatus rs = new ResponseStatus();
        StringBuilder message = new StringBuilder();

        if (addAcademicAdminDTO != null) {
            if (userRepo.findByUsername(addAcademicAdminDTO.getUsername()) != null) {
                message.append("Username ");
            }
            if (userRepo.findByEmail(addAcademicAdminDTO.getEmail()) != null) {
                message.append("Email ");
            }
            if (!StringUtils.isBlank(message)) {
                message.append("is existed");
                rs.setMessage(message.toString());
                rs.setState(false);
                return rs;
            }
            try {
                if (userRepo.findByUsername(addAcademicAdminDTO.getUsername()) == null) {
                    if (userRepo.findByEmail(addAcademicAdminDTO.getEmail()) == null) {
                        if (userRepo.findByPhone(addAcademicAdminDTO.getPhone()) == null) {
                            user.setUsername(addAcademicAdminDTO.getUsername());
                            user.setFullname(addAcademicAdminDTO.getFullname());
                            user.setPassword(passwordEncoder.encode(addAcademicAdminDTO.getPassword()));
                            user.setEmail(addAcademicAdminDTO.getEmail());
                            user.setPhone(addAcademicAdminDTO.getPhone());
                            user.setAddress(addAcademicAdminDTO.getAddress());
                            user.setActive(addAcademicAdminDTO.isActive());
                            userRepo.save(user);

                            academicAdmin.setUserId(userRepo.findTopByOrderByIdDesc().getId());
                            academicAdmin.setRoleId(2L);

                            academicAdminRepo.save(academicAdmin);
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
    public AcademicAdminDTO viewProfile(AcademicAdminDTO academicAdminDTO) {
        return null;
    }

    @Override
    public ResponseStatus editAcad(AddAcademicAdminDTO addAcademicAdminDTO) {
        User user = new User();
        Teacher teacher = new Teacher();
        ResponseStatus rs = new ResponseStatus();
        StringBuilder message = new StringBuilder();
        AcademicAdmin academicAdmin = new AcademicAdmin();

        if (addAcademicAdminDTO != null) {
            if (userRepo.findByUsername(addAcademicAdminDTO.getUsername()) != null) {
                message.append("Username ");
            }
            if (userRepo.findByEmail(addAcademicAdminDTO.getEmail()) != null) {
                message.append("Email ");
            }
            if (!StringUtils.isBlank(message)) {
                message.append("is existed");
                rs.setMessage(message.toString());
                rs.setState(false);
                return rs;
            }

            try {
                if (userRepo.findByUsername(addAcademicAdminDTO.getUsername()) == null) {
                    if (userRepo.findByEmail(addAcademicAdminDTO.getEmail()) == null) {
                        academicAdmin.setUserId(addAcademicAdminDTO.getId());
                        academicAdmin.setRoleId(2L);
                        acadRepo.save(academicAdmin);
                        user.setId(addAcademicAdminDTO.getId());
                        user.setUsername(userRepo.findById(addAcademicAdminDTO.getId()).get().getUsername());
                        user.setFullname(addAcademicAdminDTO.getFullname());
                        user.setPassword(userRepo.findById(addAcademicAdminDTO.getId()).get().getPassword());
                        user.setEmail(userRepo.findById(addAcademicAdminDTO.getId()).get().getEmail());
                        user.setPhone(addAcademicAdminDTO.getPhone());
                        user.setAddress(addAcademicAdminDTO.getAddress());
                        user.setActive(addAcademicAdminDTO.isActive());
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
    public ResponseStatus deleteAcad(AddAcademicAdminDTO addAcademicAdminDTO) {
        ResponseStatus responseStatus = new ResponseStatus();
        try {
            Long userId = acadRepo.findById(addAcademicAdminDTO.getId()).get().getUserId();
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
