package com.example.JWTSecure.service;

import com.example.JWTSecure.DTO.CurriculumDTO;
import com.example.JWTSecure.DTO.SearchResultDTO;
import com.example.JWTSecure.domain.Curriculum;
import org.springframework.web.multipart.MultipartFile;

public interface CurriculumService {

    CurriculumDTO save(Curriculum curr);

    SearchResultDTO<CurriculumDTO> getCurriculum(Long id);

    SearchResultDTO<CurriculumDTO> findByCourseId(Long courseID, Integer page);

    Long countLearningStudent(Long id);

    CurriculumDTO uploadFile(Long id, MultipartFile file);

    CurriculumDTO deleteFile(Long id);
}
