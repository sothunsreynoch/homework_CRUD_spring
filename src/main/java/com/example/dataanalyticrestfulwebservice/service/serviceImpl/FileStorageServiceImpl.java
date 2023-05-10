package com.example.dataanalyticrestfulwebservice.service.serviceImpl;

import com.example.dataanalyticrestfulwebservice.service.FileStorageService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final String serverLocation = "src/main/resources/images/";
    Path fileLocationStorage;
    public FileStorageServiceImpl() throws IOException {
        fileLocationStorage = Paths.get(serverLocation);
        try {
            if(!Files.exists(fileLocationStorage)){
                Files.createDirectories(fileLocationStorage);
            } else {
                System.out.println("Directory is already exist !");
            }
        }catch (Exception ex){
            System.out.println("");
        }
    }
    @Override
    public String uploadFile(MultipartFile file)
    {
        String filename = file.getOriginalFilename();
        String[] fileCompartment = filename.split("\\.");

        filename = UUID.randomUUID()+"."+fileCompartment[1];

        Path resolvedPath = fileLocationStorage.resolve(filename);

        try{
            Files.copy(file.getInputStream(),resolvedPath, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        }catch (Exception ex){
            return ex.getMessage();
        }

    }

    @Override
    public String deleteFileByName(String filename) {
        return null;
    }

    @Override
    public String deleteAllFiles() {
        return null;
    }
}
