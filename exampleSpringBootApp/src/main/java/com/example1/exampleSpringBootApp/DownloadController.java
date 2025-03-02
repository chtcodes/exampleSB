package com.example1.exampleSpringBootApp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {
    @RestController
    public class DownloadController {

        //  constructor injection
        private final PhotozService photozService;

        // constructor
        public DownloadController(PhotozService photozService) {
            this.photozService = photozService;
        }



        @GetMapping("/download/{id")
        private ResponseEntity<byte[]> downloadPhoto(@PathVariable String id){
                byte[] data;
                HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        }
    }
}
