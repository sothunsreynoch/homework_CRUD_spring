package com.example.dataanalyticrestfulwebservice.controller;

import com.example.dataanalyticrestfulwebservice.model.response.UploadResponse;
import com.example.dataanalyticrestfulwebservice.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {
    private final FileUploadService fileUploadService;
    FileUploadController(FileUploadService fileUploadService){
        this.fileUploadService = fileUploadService;
    }
    @Value("images/")
    private String path;
    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> fileUpload(@RequestParam("image") MultipartFile image){
        String filename = null;
        try {
            filename = this.fileUploadService.uploadImage(path, image);

        } catch (IOException e) {
            return new ResponseEntity<>(new UploadResponse(null, " not upload !!!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new UploadResponse(filename, " successfully upload to server!!"), HttpStatus.OK);
    }
}

