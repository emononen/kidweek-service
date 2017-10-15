package com.kidweek.service.service;

import com.kidweek.service.model.FacebookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class FacebookService {
    @Value("${facebook.graph.url}")
    private String graphApiUrl;
    @Autowired
    private RestTemplateBuilder builder;
    private RestTemplate rest;

    public FacebookUser getUser(String fbToken) {
        return rest.getForObject("/?access_token={fbToken}", FacebookUser.class, fbToken);
    }

    @PostConstruct
    public void init() {
        this.rest = builder.rootUri(graphApiUrl).build();
    }
}
