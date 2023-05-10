package com.example.dataanalyticrestfulwebservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String uploadFile(MultipartFile file);
    String deleteFileByName(String filename);
    String deleteAllFiles();
}
