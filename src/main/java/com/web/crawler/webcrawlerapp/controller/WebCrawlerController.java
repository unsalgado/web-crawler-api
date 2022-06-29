package com.web.crawler.webcrawlerapp.controller;

import com.web.crawler.webcrawlerapp.dto.ResponseDto;
import com.web.crawler.webcrawlerapp.exception.ResourceNotFoundException;
import com.web.crawler.webcrawlerapp.service.WebExtractService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@RequestMapping("/api/web-crawler/v1")
@CrossOrigin
public class WebCrawlerController {

    @Autowired
    private WebExtractService webExtractService;

    @Operation(summary = "Get list of URLs for the given text.")
    @GetMapping("/extracted-url-by-text")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<ResponseDto> getExtractedUrl(@RequestParam(name = "text") final String text) {
        ResponseDto responseDto = webExtractService.crawlingWeb(text);
        if (responseDto.getSites().isEmpty()) {
            log.debug("Sites are empty");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(responseDto);
    }
}
