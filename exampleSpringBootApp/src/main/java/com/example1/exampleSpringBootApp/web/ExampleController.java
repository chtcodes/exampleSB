package com.example1.exampleSpringBootApp.web;

import com.example1.exampleSpringBootApp.model.Photo;
import com.example1.exampleSpringBootApp.service.PhotozService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;

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
    public Collection<Photo> getPhotos(){
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
    public Photo createAPhoto(@RequestPart("image") MultipartFile file) throws IOException{

        return  photozService.savePhoto(file.getOriginalFilename(), file.getContentType(), file.getBytes());

    }

}
