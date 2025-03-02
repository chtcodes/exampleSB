package com.example1.exampleSpringBootApp;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
public class ExampleController {

//    private List<Photo> db = List.of(new Photo("1", "hello.jpg"));

//    field injection
//    @Autowired
//    private PhotozService photozService;

//  constructor injection
    private final PhotozService photozService;

// constructor
    public ExampleController(PhotozService photozService) {
        this.photozService = photozService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello Spring Boot";
    }

    @GetMapping("/photoz")
    public Collection<Photo> getPhoto(){
        return photozService.getPhotos();
    }

    @GetMapping("/photoz/{id}")
    public Photo getPhoto(@PathVariable String id){
        Photo photo =  photozService.getPhotoById(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void deletePhoto(@PathVariable String id){
         Photo photo = photozService.deletePhoto(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }


    @PostMapping("/photoupload")
    public Photo createAPhoto(@RequestPart("image") MultipartFile file){
      String fileName = file.getOriginalFilename();
        byte[] data;
        try {
             data =    file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException( "Error getting the image file: " + e );
        }

        Photo photo = photozService.savePhoto(fileName, data);
        return photo;

    }

}
