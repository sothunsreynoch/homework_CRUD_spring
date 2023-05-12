package com.example.dataanalyticrestfulwebservice.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String uploadFile(MultipartFile file);
    String deleteFileByName(String filename);
    String deleteAllFiles();
    Resource loadFilAsResource (String filename) throws Exception;
}
