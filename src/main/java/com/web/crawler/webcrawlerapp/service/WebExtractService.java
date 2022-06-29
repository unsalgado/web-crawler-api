package com.web.crawler.webcrawlerapp.service;

import com.web.crawler.webcrawlerapp.dto.ResponseDto;


public interface WebExtractService {

    ResponseDto crawlingWeb(String text);
}
