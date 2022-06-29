package com.web.crawler.webcrawlerapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseDto {

    private List<SiteDto> sites;
}
