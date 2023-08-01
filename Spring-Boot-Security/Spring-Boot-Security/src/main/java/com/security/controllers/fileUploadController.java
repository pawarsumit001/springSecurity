package com.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class fileUploadController {

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(){
        return  ResponseEntity.ok("working");
    }
}
