package com.example.urlshortener;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@RestController
public class UrlController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlController.class);
    private final UrlConverterService urlConverterService;

    public UrlController(UrlConverterService urlConverterService) {
        this.urlConverterService = urlConverterService;
    }

    @PostMapping(value = "/shortener", consumes = {"application/json"})
    public String shortenUrl(@RequestBody @Valid final ShortenRequest shortenRequest, HttpServletRequest request) throws Exception {
        LOGGER.info("Received url to shorten: {}", shortenRequest.getUrl());
        String longUrl = shortenRequest.getUrl();
        if (UrlValidator.INSTANCE.validateURL(longUrl)) {
            String localURL = request.getRequestURL().toString();
            String shortenedUrl = urlConverterService.shortenURL(localURL, shortenRequest.getUrl());
            LOGGER.info("Shortened url to: {}", shortenedUrl);
            return shortenedUrl;
        }
        throw new Exception("Please enter a valid URL");
    }

    @GetMapping(value = "/{id}")
    public RedirectView redirectUrl(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.debug("Received shortened url to redirect: {}", id);
        String redirectUrlString = urlConverterService.getLongURLFromID(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://" + redirectUrlString);
        return redirectView;
    }
}


class ShortenRequest{
    private String url;

    @JsonCreator
    public ShortenRequest() {

    }

    @JsonCreator
    public ShortenRequest(@JsonProperty("url") String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

