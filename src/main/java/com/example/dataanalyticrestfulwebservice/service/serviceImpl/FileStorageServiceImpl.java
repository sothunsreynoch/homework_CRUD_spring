package com.example.dataanalyticrestfulwebservice.service.serviceImpl;

import com.example.dataanalyticrestfulwebservice.service.FileStorageService;
import com.github.pagehelper.Page;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
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
        Path imagesLocation = Paths.get(serverLocation);
        List<File> allFiles  = List.of(imagesLocation.toFile().listFiles());

        File deleteFile = allFiles.stream().filter(
                file -> file.getName().equals(filename)
        ).findFirst().orElse(null);

        try {


        if(deleteFile!=null){
            Files.delete(deleteFile.toPath());
            return "delete file successfully";
        } else {
            return "file with "+filename + "doesn't exit";
        }

        }catch (Exception ex){
            System.out.println("Error delete file by name:"+ex.getMessage());
            return "exception occurred! failed to delete file by name";
        }

    }

    @Override
    public String deleteAllFiles() {
        Path imageLocation = Paths.get(serverLocation);
        File[] files = imageLocation.toFile().listFiles();
        try{
            if(files==null || files.length == 0){
                return "There is no file to delete!!";
            }
            for(File file : files){
                Files.delete(file.toPath());
            }
            return  "Successfully delete all files";
        }catch (Exception ex ){
            ex.printStackTrace();
            System.out.println("Exception Delete all files : "+ex.getMessage());
            return "Failed to delete all files ! Exception occurred!";
        }

    }

    @Override
    public Resource loadFilAsResource(String filename) throws Exception {
        Path resuourcePath = this.fileLocationStorage.resolve(filename).normalize();
        try {
            Resource resource = new UrlResource(resuourcePath.toUri());
            if(resource.exists()){
                return resource;
            }else {
                throw new Exception("Resource doesn't exist !");
            }
        }catch (Exception ex){
            throw new Exception("Exception Occrred!! Failed to download the image");
        }
    }
    }


