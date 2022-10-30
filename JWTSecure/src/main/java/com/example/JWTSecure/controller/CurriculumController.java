package com.example.JWTSecure.controller;

import com.example.JWTSecure.DTO.CurriculumDTO;
import com.example.JWTSecure.DTO.SearchResultDTO;
import com.example.JWTSecure.domain.Curriculum;
import com.example.JWTSecure.service.CurriculumService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/curriculum")
@AllArgsConstructor
public class CurriculumController {
    private CurriculumService curriculumService;

    @PostMapping("/")
    public CurriculumDTO createCurriculum(@RequestBody Curriculum curr) throws Exception {
        if (curr.getId() != null) throw new Exception("Id of Curriculum must null");
        return curriculumService.save(curr);
    }

    @PutMapping("/")
    public CurriculumDTO updateCurriculum(@RequestBody Curriculum curr) throws Exception {
        if (curr.getId() == null) throw new Exception("Id of Curriculum must not null");
        return curriculumService.save(curr);
    }

    @GetMapping("/{id}")
    public SearchResultDTO<CurriculumDTO> findById(@PathVariable Long id) {
        return curriculumService.getCurriculum(id);
    }

    @GetMapping("/get-by-course")
    public SearchResultDTO<CurriculumDTO> findByCourseId(
            @RequestParam Long courseId, @RequestParam Integer page
    ) {
        return curriculumService.findByCourseId(courseId, page);
    }

    @GetMapping("/{id}/count-learning")
    public Long countLearningStudent(@PathVariable Long id) {
        return curriculumService.countLearningStudent(id);
    }

    @PutMapping("/{id}/upload-file")
    public CurriculumDTO uploadFile(@PathVariable Long id ,@RequestParam MultipartFile file) {
        return curriculumService.uploadFile(id, file);
    }

    @PutMapping("/{id}/delete-file")
    public CurriculumDTO deleteFile(@PathVariable Long id ) {
        return curriculumService.deleteFile(id);
    }

}
