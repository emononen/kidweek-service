package com.kidweek.service.security;

import com.kidweek.service.model.FacebookUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class FacebookAuthentication extends AbstractAuthenticationToken {

    private FacebookUser user;

    public FacebookAuthentication(FacebookUser user) {
        super(Collections.emptySet());
        this.user = user;
    }

    @Override
    public FacebookUser getCredentials() {
        return user;
    }

    @Override
    public FacebookUser getPrincipal() {
        return user;
    }

    @Override
    public FacebookUser getDetails() {
        return user;
    }

    @Override
    public String getName() {
        return user.getName();
    }
}
