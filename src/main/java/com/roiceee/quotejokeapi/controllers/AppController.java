package com.roiceee.quotejokeapi.controllers;

import com.roiceee.quotejokeapi.models.Phrase;
import com.roiceee.quotejokeapi.services.FetchResourceService;
import com.roiceee.quotejokeapi.util.ReqParamNames;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AppController {
    FetchResourceService fetchResourceService;

    public AppController(FetchResourceService fetchResourceService) {
        this.fetchResourceService = fetchResourceService;
    }

    @GetMapping
    public ResponseEntity<Phrase> getRandomResource(
            @RequestParam (value = ReqParamNames.type) String type
            ) {

            Phrase phrase = fetchResourceService.getRandomPhrase(type);
        return ResponseEntity
                .ok()
                .body(phrase);
    }
}
