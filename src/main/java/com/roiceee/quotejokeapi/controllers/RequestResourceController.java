package com.roiceee.quotejokeapi.controllers;

import com.roiceee.quotejokeapi.models.Phrase;
import com.roiceee.quotejokeapi.services.FetchResourceService;
import com.roiceee.quotejokeapi.util.ReqParamNames;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api")
public class RequestResourceController {
    FetchResourceService fetchResourceService;

    public RequestResourceController(FetchResourceService fetchResourceService) {
        this.fetchResourceService = fetchResourceService;
    }

    @GetMapping(params = {ReqParamNames.type})
    public ResponseEntity<Phrase> getRandomResource(
            @RequestParam (value = ReqParamNames.type) String type
            ) {
            Phrase phrase = fetchResourceService.getRandomPhrase(type);
        return ResponseEntity
                .ok()
                .body(phrase);
    }

    @GetMapping(params = {ReqParamNames.type, ReqParamNames.qty})
    public ResponseEntity<List<? extends Phrase>> getRandomResourceList(
             @RequestParam (value = ReqParamNames.type) String type,
             @RequestParam (value = ReqParamNames.qty) int qty
    ) {
        List<? extends Phrase> phraseList = fetchResourceService.getRandomPhraseList(type, qty);
        return ResponseEntity
                .ok()
                .body(phraseList);
    }


}
