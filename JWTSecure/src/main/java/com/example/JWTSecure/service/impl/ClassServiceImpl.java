package com.example.JWTSecure.service.impl;

import com.example.JWTSecure.DTO.ClassDTO;
import com.example.JWTSecure.DTO.SearchResultDTO;
import com.example.JWTSecure.repo.impl.ClassCustomRepo;
import com.example.JWTSecure.service.ClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ClassServiceImpl implements ClassService {

    private final ClassCustomRepo classCustomRepo;

    @Override
    public SearchResultDTO<ClassDTO> getAllClass(ClassDTO classDTO) {
        List<ClassDTO> dataResult;
        SearchResultDTO<ClassDTO> searchResult = new SearchResultDTO<>();
        try {
            Integer totalRecord = classCustomRepo.getTotal(classDTO).size();
            dataResult = classCustomRepo.doSearch(classDTO);
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
}
