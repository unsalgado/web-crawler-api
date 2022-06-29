package com.web.crawler.webcrawlerapp.util;

import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Log4j2
public class WebExtracter {

    private static Pattern patternDomainName;
    private Matcher matcher;
    private static final String DOMAIN_NAME_PATTERN ="(?:(?:https?):\\/\\/)?[\\w/\\-?=%.]+\\.[\\w/\\-&?=%.]+\\/[\\w/\\-?=%.]+";

    static {
        patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
    }

    private String getUrl(String url){

        String domainName = "";
        matcher = patternDomainName.matcher(url);
        if (matcher.find()) {
            domainName = matcher.group(0).trim();
        }
        return domainName;

    }

    public Set<String> getDataFromGoogle(String query){

        Set<String> result ;
        String request = "https://www.google.com/search?q=" + query + "&num=20";

        try{
            Document doc = Jsoup
                    .connect(request)
                    .userAgent(
                            "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
                    .timeout(5000).get();

            // get all links
            Elements links = doc.select("a[href]");

            result =links.stream()
                    .filter(link -> !link.attr("href").isEmpty() && link.attr("href").startsWith("/url?q="))// filtering data
                    .map(link->getUrl(link.attr("href")))
                    .collect(Collectors.toSet());


        } catch (Exception e) {
            log.error(e.getMessage());
            throw new IllegalStateException("Connection Error");
        }
        return result;
    }


}
