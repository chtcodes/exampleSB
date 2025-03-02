package com.example1.exampleSpringBootApp;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
public class ExampleController {

//    private List<Photo> db = List.of(new Photo("1", "hello.jpg"));

    private HashMap <String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "hello.jpg"));
    }};

    @GetMapping("/")
    public String hello() {
        return "Hello Spring Boot";
    }

    @GetMapping("/photoz")
    public Collection<Photo> getPhoto(){
        return db.values();
    }

    @GetMapping("/photoz/{id}")
    public Photo getPhoto(@PathVariable String id){
        Photo photo =  db.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void deletePhoto(@PathVariable String id){
         Photo photo = db.remove(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/photoz")
    public Photo createPhoto(@RequestBody @Valid Photo photo){
        photo.setId(UUID.randomUUID().toString());
        db.put(photo.getId(),photo);
        return photo;
    }

    @PostMapping("/photoupload")
    public Photo createAPhoto(@RequestPart("image") MultipartFile file){
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(file.getOriginalFilename());

        db.put(photo.getId(), photo);
        return photo;
    }
}
