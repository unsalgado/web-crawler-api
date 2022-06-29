package com.web.crawler.webcrawlerapp.service;

import com.web.crawler.webcrawlerapp.dto.ResponseDto;
import com.web.crawler.webcrawlerapp.dto.SiteDto;
import com.web.crawler.webcrawlerapp.util.WebExtracter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Log4j2
@Service
public class WebExtractServiceImpl implements WebExtractService{


    @Override
    public ResponseDto crawlingWeb(String text) {

        ResponseDto responseDto = new ResponseDto();
        List<SiteDto> siteDtos = new ArrayList<>();
        WebExtracter extracter = new WebExtracter();

        Set<String> result = extracter.getDataFromGoogle(text);

        result.forEach(
                url -> {
                    if(!url.isEmpty()){
                        SiteDto siteDto = new SiteDto();
                        siteDto.setUrl(url);
                        siteDtos.add(siteDto);
                    }

                }
        );
        responseDto.setSites(siteDtos);

        return  responseDto;
    }

}
