package com.roiceee.quotejokeapi.controllers;

import com.roiceee.quotejokeapi.models.Phrase;
import com.roiceee.quotejokeapi.services.FetchResourceService;
import com.roiceee.quotejokeapi.util.ReqParamNames;
import org.springframework.data.domain.Page;
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

    @GetMapping(params = {ReqParamNames.TYPE})
    public ResponseEntity<Phrase> getRandomResource(
            @RequestParam (value = ReqParamNames.TYPE) String type
            ) {
            Phrase phrase = fetchResourceService.getRandomPhrase(type);
        return ResponseEntity
                .ok()
                .body(phrase);
    }

    @GetMapping(params = {ReqParamNames.TYPE, ReqParamNames.QTY})
    public ResponseEntity<List<? extends Phrase>> getRandomResourceList(
             @RequestParam (value = ReqParamNames.TYPE) String type,
             @RequestParam (value = ReqParamNames.QTY) int qty
    ) {
        List<? extends Phrase> phraseList = fetchResourceService.getRandomPhraseList(type, qty);
        return ResponseEntity
                .ok()
                .body(phraseList);
    }

    @GetMapping(params = {ReqParamNames.TYPE, ReqParamNames.PAGE, ReqParamNames.QTY})
    public ResponseEntity<Page<? extends Phrase>> getResourcesWithPagination(
            @RequestParam (value = ReqParamNames.TYPE) String type,
            @RequestParam (value = ReqParamNames.PAGE) int page,
            @RequestParam (value = ReqParamNames.QTY) int qty
    ) {
       return this.getResourcesByKeywordWithPagination(type, page, qty, "");
    }
    @GetMapping(params = {ReqParamNames.TYPE, ReqParamNames.PAGE, ReqParamNames.QTY, ReqParamNames.QUERY,})
    public ResponseEntity<Page<? extends Phrase>> getResourcesByKeywordWithPagination(
            @RequestParam (value = ReqParamNames.TYPE) String type,
            @RequestParam (value = ReqParamNames.PAGE) int page,
            @RequestParam (value = ReqParamNames.QTY) int qty,
             @RequestParam (value = ReqParamNames.QUERY) String query
    ) {

        Page<? extends Phrase> phrases = fetchResourceService.getPhraseListWithQueryPagination(type, query, page, qty);
        return ResponseEntity
                .ok()
                .body(phrases);
    }
}
