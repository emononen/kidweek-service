package com.kidweek.service.security;

import com.kidweek.service.model.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class FacebookAuthentication extends AbstractAuthenticationToken {

    private User user;

    public FacebookAuthentication(User user) {
        super(Collections.emptySet());
        this.user = user;
    }

    @Override
    public User getCredentials() {
        return user;
    }

    @Override
    public User getPrincipal() {
        return user;
    }

    @Override
    public User getDetails() {
        return user;
    }

    @Override
    public String getName() {
        return user.getName();
    }
}
