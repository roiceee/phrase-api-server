package com.roiceee.phraseapi.mainapi.controllers;
import com.roiceee.phraseapi.mainapi.models.Phrase;
import com.roiceee.phraseapi.mainapi.services.FetchResourceService;
import com.roiceee.phraseapi.mainapi.util.Params;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/phrase")
public class RequestResourceController {
    FetchResourceService fetchResourceService;

    public RequestResourceController(FetchResourceService fetchResourceService) {
        this.fetchResourceService = fetchResourceService;
    }

    @GetMapping(params = {Params.appID, Params.TYPE})
    public ResponseEntity<Phrase> getRandomResource(
            @RequestParam (value = Params.appID) String appid,
            @RequestParam (value = Params.TYPE) String type
            ) {
            Phrase phrase = fetchResourceService.getRandomPhrase(type);
        return ResponseEntity
                .ok()
                .body(phrase);
    }

    @GetMapping(params = {Params.appID, Params.TYPE, Params.QTY})
    public ResponseEntity<List<? extends Phrase>> getRandomResourceList(
             @RequestParam (value = Params.appID) String appid,
             @RequestParam (value = Params.TYPE) String type,
             @RequestParam (value = Params.QTY) int qty
    ) {
        List<? extends Phrase> phraseList = fetchResourceService.getRandomPhraseList(type, qty);
        return ResponseEntity
                .ok()
                .body(phraseList);
    }

    @GetMapping(params = {Params.appID, Params.TYPE, Params.QTY, Params.QUERY})
    public ResponseEntity<List<? extends Phrase>> getResourcesWithPagination(
            @RequestParam (value = Params.appID) String appid,
            @RequestParam (value = Params.TYPE) String type,
            @RequestParam (value = Params.QTY) int qty,
            @RequestParam (value = Params.QUERY) String query
    ) {
        List<? extends Phrase> phraseList = fetchResourceService.getRandomPhraseListWithQuery(type, qty, query);
       return ResponseEntity
               .ok()
               .body(phraseList);
    }
    @GetMapping(params = {Params.appID, Params.TYPE, Params.PAGE, Params.QTY, Params.QUERY,})
    public ResponseEntity<Page<? extends Phrase>> getResourcesByKeywordWithPagination(
            @RequestParam (value = Params.appID) String appid,
            @RequestParam (value = Params.TYPE) String type,
            @RequestParam (value = Params.PAGE) int page,
            @RequestParam (value = Params.QTY) int qty,
             @RequestParam (value = Params.QUERY) String query
    ) {

        Page<? extends Phrase> phrases = fetchResourceService.getPhraseListWithQueryPagination(type, query, page, qty);
        return ResponseEntity
                .ok()
                .body(phrases);
    }
}
