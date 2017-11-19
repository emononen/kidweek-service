package com.kidweek.service.service;

import com.kidweek.service.model.FriendList;
import com.kidweek.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class FacebookService {
    @Value("${facebook.graph.url}")
    private String graphApiUrl;
    @Autowired
    private RestTemplateBuilder builder;
    private RestTemplate rest;

    public User getUser(String fbToken) {
        checkNotNull(fbToken);
        checkArgument(fbToken.trim().length() > 0);
        System.out.println(friends(fbToken));
        return rest.getForObject("/?access_token={fbToken}", User.class, fbToken);
    }

    public FriendList friends(String fbToken) {
        checkNotNull(fbToken);
        checkArgument(fbToken.trim().length() > 0);
        return rest.getForObject("/friends?access_token={fbToken}", FriendList.class, fbToken);

    }

    @PostConstruct
    public void init() {
        this.rest = builder.rootUri(graphApiUrl).build();
    }
}
