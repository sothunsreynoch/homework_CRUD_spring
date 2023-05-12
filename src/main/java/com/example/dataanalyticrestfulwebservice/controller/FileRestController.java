package com.example.dataanalyticrestfulwebservice.controller;

import com.example.dataanalyticrestfulwebservice.model.FileResponse;
import com.example.dataanalyticrestfulwebservice.service.FileStorageService;
import com.example.dataanalyticrestfulwebservice.util.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.IllformedLocaleException;
import java.util.List;

@RestController
@RequestMapping("/file-service")
public class FileRestController {
    @Autowired
    FileStorageService fileStorageService;
    private final List<String> ALLOWED_EXTENSIONS =List.of("jpg","png","jpeg");
    private final  long MAX_FILE_SIZE = 1024 * 1024 * 5;
    @PostMapping("/file-upload")
    public Response<?> fileUpload(@RequestParam("file") MultipartFile file) {
//       String filename = fileStorageService.uploadFile(file);
//       return Response.<Object>ok().setPayload(filename);

            FileResponse response = uploadFile(file);
            return Response.<FileResponse>ok().setPayload(response)
                    .setMessage("Successfully upload a file");


    }
    @PostMapping("/multiple-file-upload")
    public Response<List<FileResponse>> uploadMultipleFiles (@RequestParam("files")MultipartFile[] files){
        try {
            List<FileResponse> responses = Arrays.stream(files)
                       .map(this::uploadFile)
                       .toList();
       return Response.<List<FileResponse>>ok().setPayload(responses).setMessage("successfully upload multiple file");
        }catch (Exception ex){
            System.out.println("Exception happened! "+ ex.getMessage());
            return Response.<List<FileResponse>>exception().setMessage("Failed to upload multiple  image!" +
                    "Exception occurred!");
        }

    }

    @DeleteMapping("/deletefile/{filename}")
    public String deleteSingleFile(@PathVariable String filename){
        String result = fileStorageService.deleteFileByName(filename);
        return result;

    }

    @DeleteMapping("/delete-all-files")
    public  String deleteAllFiles(){
       String result = fileStorageService.deleteAllFiles();

        return result;
    }

    @GetMapping("/download-file/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename, HttpServletRequest request) throws Exception {


        Resource resource = fileStorageService.loadFilAsResource(filename);
        String contentType =null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        }catch (IOException exception){
            System.out.println("Error Getting content type is: "+exception.getMessage());
        }
        if(contentType==null){
            contentType="application/octet-stream";

        }
        return ResponseEntity.ok().
                contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ resource.getFilename()+"\"" )
                .body(resource);

    }



    private FileResponse uploadFile(MultipartFile file) {

        if (file.isEmpty())
            throw new IllformedLocaleException("File cannot be empty");
        if (file.getSize() > MAX_FILE_SIZE)
            throw new MaxUploadSizeExceededException(MAX_FILE_SIZE);
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        if (!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())) {
            throw new IllegalArgumentException("Allowed Extension are 'jpg','png','jpeg'");
        }


        String filename = fileStorageService.uploadFile(file);
        String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file-service/download-file/")
                .path(filename)
                .toUriString();
        return new FileResponse().setFilename(filename)
                .setFileDownloadUrl(fileDownloadUrl)
                .setFileType(file.getContentType())
                .setSize(file.getSize());
    }

}