package com.example1.exampleSpringBootApp.web;

import com.example1.exampleSpringBootApp.model.Photo;
import com.example1.exampleSpringBootApp.service.PhotozService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {

        //  constructor injection
        private final PhotozService photozService;

        // constructor
        public DownloadController(PhotozService photozService) {
            this.photozService = photozService;
        }



        @GetMapping("/download/{id}")
        private ResponseEntity<byte[]> downloadPhoto(@PathVariable String id){
                Photo photo = photozService.getPhotoById(id);
                if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

                byte[] data = photo.getData();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.valueOf(photo.getContentType()));
                ContentDisposition build = ContentDisposition
                        .builder("attachement")
                        .filename(photo.getFileName())
                        .build();
                headers.setContentDisposition(build);
            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        }
    }

