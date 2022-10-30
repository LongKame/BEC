package com.example.JWTSecure.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    void init();

    Boolean isExist(String filename);

    String save(MultipartFile file);

    Resource load(String filename);

    Boolean delete(String filename);
}
