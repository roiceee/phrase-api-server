package com.roiceee.phraseapi.resourceapi.controller;

import com.roiceee.phraseapi.apikeymanagement.service.ApiKeyService;
import com.roiceee.phraseapi.resourceapi.dto.PhraseDTO;
import com.roiceee.phraseapi.resourceapi.service.FetchResourceService;
import com.roiceee.phraseapi.resourceapi.service.RequestCountService;
import com.roiceee.phraseapi.resourceapi.service.ResourceControllerLimiterService;
import com.roiceee.phraseapi.resourceapi.util.Params;
import com.roiceee.phraseapi.resourceapi.util.ReqParamOrderByValues;
import com.roiceee.phraseapi.resourceapi.util.ReqParamSearchByValues;
import com.roiceee.phraseapi.util.Origins;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/phrase")
@AllArgsConstructor
public class RequestResourceController {

    private final FetchResourceService fetchResourceService;
    private final ApiKeyService apiKeyService;
    private final RequestCountService requestCountService;

    private final ResourceControllerLimiterService resourceControllerLimiterService;


    @GetMapping(params = {Params.APP_ID, Params.TYPE})
    public ResponseEntity<PhraseDTO> getRandomResource(
            @RequestParam(value = Params.APP_ID) String appid,
            @RequestParam(value = Params.TYPE) String type
    ) {
        this.beforeAction(appid);
        PhraseDTO phrase = fetchResourceService.getRandomPhrase(type);
        this.afterAction(appid);
        return ResponseEntity
                .ok()
                .body(phrase);
    }

    @GetMapping(params = {Params.APP_ID, Params.TYPE, Params.QTY})
    public ResponseEntity<List<PhraseDTO>> getRandomResourceList(
            @RequestParam(value = Params.APP_ID) String appid,
            @RequestParam(value = Params.TYPE) String type,
            @RequestParam(value = Params.QTY) int qty
    ) {
        this.beforeAction(appid);
        List<PhraseDTO> phraseList = fetchResourceService.getRandomPhraseList(type, qty);
        this.afterAction(appid);
        return ResponseEntity
                .ok()
                .body(phraseList);
    }

    @GetMapping(params = {Params.APP_ID, Params.TYPE, Params.QTY, Params.QUERY})
    public ResponseEntity<List<PhraseDTO>> getResourcesWithPagination(
            @RequestParam(value = Params.APP_ID) String appid,
            @RequestParam(value = Params.TYPE) String type,
            @RequestParam(value = Params.QTY) int qty,
            @RequestParam(value = Params.QUERY) String query
    ) {
        this.beforeAction(appid);
        List<PhraseDTO> phraseList = fetchResourceService.getRandomPhraseListWithQuery(type, qty, query);
        this.afterAction(appid);
        return ResponseEntity
                .ok()
                .body(phraseList);
    }




    @GetMapping(params = {Params.APP_ID, Params.TYPE, Params.PAGE, Params.QTY, Params.QUERY})
    public ResponseEntity<Page<PhraseDTO>> getResourcesByKeywordWithPagination(
            @RequestParam(value = Params.APP_ID) String appid,
            @RequestParam(value = Params.TYPE) String type,
            @RequestParam(value = Params.PAGE) int page,
            @RequestParam(value = Params.QTY) int qty,
            @RequestParam(value = Params.QUERY) String query
    ) {
        this.beforeAction(appid);
        Page<PhraseDTO> phrases = fetchResourceService.getPhraseListWithQueryPagination(type, query, page,
                qty);
        this.afterAction(appid);
        return ResponseEntity
                .ok()
                .body(phrases);
    }

    //this is meant to be requested by the phrase api client page, without the need of an api key
    @GetMapping("client-search")
    @CrossOrigin(origins = {Origins.PROD, Origins.LOCAL})
    public ResponseEntity<Page<PhraseDTO>> getResourcesToClientByKeywordWithPagination(
            @RequestParam(value = Params.TYPE, defaultValue = "joke") String type,
            @RequestParam(value = Params.PAGE, defaultValue = "0") int page,
            @RequestParam(value = Params.QTY, defaultValue = "12") int qty,
            @RequestParam(value = Params.QUERY, defaultValue = "") String query,
            @RequestParam(value = Params.SEARCH_BY, defaultValue = ReqParamSearchByValues.PHRASE) String searchBy,
            @RequestParam(value = Params.ORDER_BY, defaultValue = ReqParamOrderByValues.DESC) String orderBy
    ) {
        Page<PhraseDTO> phrases = fetchResourceService.getPhraseListToClientApp(type, page, qty,
                query, searchBy, orderBy);

        return ResponseEntity
                .ok()
                .body(phrases);
    }

    private void beforeAction(String appid) {
        apiKeyService.checkIfApiKeyExists(appid);
        resourceControllerLimiterService.addExistingKeyIfAbsentInCache(appid);
        resourceControllerLimiterService.consumeOne(appid);
    }

    private void afterAction(String appid) {
        requestCountService.addCount(appid);
    }
}
