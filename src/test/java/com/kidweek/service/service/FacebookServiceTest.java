package com.kidweek.service.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@RestClientTest(FacebookService.class)
public class FacebookServiceTest {

    @Autowired
    private FacebookService facebookService;

    @Test
    public void should_get_user() {
    }
}