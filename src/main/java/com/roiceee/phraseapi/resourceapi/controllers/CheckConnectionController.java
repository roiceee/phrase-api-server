package com.roiceee.phraseapi.resourceapi.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "check")
@CrossOrigin(value = "*")
public class CheckConnectionController {

    @GetMapping
    public ResponseEntity<Object> returnOK() {
        return ResponseEntity.ok().build();
    }
}
