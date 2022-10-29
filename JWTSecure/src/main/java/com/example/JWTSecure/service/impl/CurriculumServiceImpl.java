package com.example.JWTSecure.service.impl;

import com.example.JWTSecure.DTO.CurriculumDTO;
import com.example.JWTSecure.DTO.SearchResultDTO;
import com.example.JWTSecure.domain.Curriculum;
import com.example.JWTSecure.domain.Learning;
import com.example.JWTSecure.mapper.CurriculumMapper;
import com.example.JWTSecure.repo.CurriculumRepo;
import com.example.JWTSecure.repo.LearningRepo;
import com.example.JWTSecure.service.CurriculumService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CurriculumServiceImpl implements CurriculumService {
    private CurriculumRepo curriculumRepo;
    private LearningRepo learningRepo;

    @Override
    public CurriculumDTO save(Curriculum curr) {
        return CurriculumMapper.toDto(curriculumRepo.save(curr));
    }

    @Override
    public SearchResultDTO<CurriculumDTO> getCurriculum(Long id) {
        return curriculumRepo
                .findById(id)
                .map(rs -> SearchResultDTO.<CurriculumDTO>defaultSuccess().addSingleResultData(CurriculumMapper.toDto(rs)))
                .orElse(SearchResultDTO.defaultNotFound());
    }

    @Override
    public SearchResultDTO<CurriculumDTO> findByCourseId(Long courseID, Integer page) {
        List<CurriculumDTO> rs = curriculumRepo.findByCourseId(courseID, PageRequest.of(page, 20))
                .stream().map(CurriculumMapper::toDto).collect(Collectors.toList());
        return rs.isEmpty() ?
                SearchResultDTO.defaultNotFound() : SearchResultDTO.<CurriculumDTO>defaultSuccess().addListResultData(rs);
    }

    @Override
    public Long countLearningStudent(Long id) {
        return learningRepo.countByCurriculumId(id);
    }
}
