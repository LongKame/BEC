package com.example.JWTSecure.service.impl;

import com.example.JWTSecure.DTO.CurriculumDTO;
import com.example.JWTSecure.DTO.SearchResultDTO;
import com.example.JWTSecure.domain.Curriculum;
import com.example.JWTSecure.mapper.CurriculumMapper;
import com.example.JWTSecure.repo.CurriculumRepo;
import com.example.JWTSecure.repo.LearningRepo;
import com.example.JWTSecure.service.CurriculumService;
import com.example.JWTSecure.service.FilesStorageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CurriculumServiceImpl implements CurriculumService {
    private CurriculumRepo curriculumRepo;
    private LearningRepo learningRepo;
    private FilesStorageService filesStorageService;

    @Override
    public CurriculumDTO save(Curriculum curr) {
        curr.setLinkURL("");
        if (curr.getId() == null)
            curr.setCreatedAt(LocalDateTime.now());
        else
            curr.setUpdatedAt(LocalDateTime.now());
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
        Page<Curriculum> curriculums = curriculumRepo.findByCourseId(courseID, PageRequest.of(page, 20));
        if (curriculums.isEmpty() && curriculums.getTotalElements() == 0L) {
            return SearchResultDTO.defaultNotFound();
        }
        SearchResultDTO<CurriculumDTO> rs = SearchResultDTO.defaultSuccess();
        rs.setResultData(curriculums.get().map(CurriculumMapper::toDto).collect(Collectors.toList()));
        rs.setTotalRecordNoLimit((int) curriculums.getTotalElements());
        return rs;
    }

    @Override
    public Long countLearningStudent(Long id) {
        return learningRepo.countByCurriculumId(id);
    }

    @Override
    public CurriculumDTO uploadFile(Long id, MultipartFile file) {
        Optional<Curriculum> optional = curriculumRepo.findById(id);
        if (optional.isPresent()) {
            Curriculum curriculum = optional.get();
            String filename = curriculum.getLinkURL();
            if (StringUtils.hasLength(filename) && filesStorageService.isExist(filename)) {
                if (!filesStorageService.delete(filename))
                    throw new RuntimeException("Delete Curriculum old file error");
            }
            filename = filesStorageService.save(file);
            curriculum.setLinkURL(filename);
            curriculum.setUpdatedAt(LocalDateTime.now());
            curriculumRepo.save(curriculum);
            return optional.map(CurriculumMapper::toDto).orElse(null);
        } else {
            throw new RuntimeException("Not found Curriculum");
        }
    }

    @Override
    public CurriculumDTO deleteFile(Long id) {
        Optional<Curriculum> optional = curriculumRepo.findById(id);
        if (optional.isPresent()) {
            Curriculum curriculum = optional.get();
            String filename = curriculum.getLinkURL();
            if (StringUtils.hasLength(filename) && filesStorageService.isExist(filename)) {
                if (filesStorageService.delete(filename)) {
                    curriculum.setLinkURL("");
                    curriculum.setUpdatedAt(LocalDateTime.now());
                    optional.map(e -> curriculumRepo.save(curriculum));
                } else
                    throw new RuntimeException("Delete Curriculum file error");
            }
            return optional.map(CurriculumMapper::toDto).orElse(null);
        } else {
            throw new RuntimeException("Not found Curriculum");
        }
    }
}
