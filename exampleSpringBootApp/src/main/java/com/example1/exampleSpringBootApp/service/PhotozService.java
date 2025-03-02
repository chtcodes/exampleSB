package com.example1.exampleSpringBootApp.service;

import com.example1.exampleSpringBootApp.model.Photo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Service
public class PhotozService {

    private HashMap<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "hello.jpg"));
    }};


    public Collection<Photo> getPhotos() {
        return db.values();
    }

    public Photo getPhotoById(String id) {
        return db.get(id);
    }


    public Photo savePhoto(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(fileName);
        photo.setData(data);
        photo.setContentType(contentType);

        db.put(photo.getId(),photo);
        return photo;

    }

    public Photo deletePhoto(String id) {
        return db.remove(id);
    }
}
