package com.kidweek.service.security;

import com.google.common.base.Strings;
import com.kidweek.service.model.FacebookUser;
import com.kidweek.service.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static com.google.common.base.Strings.isNullOrEmpty;

//@Component
public class FacebookAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private FacebookService facebookService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getParameter("access_token");
        if (isNullOrEmpty(token)) {
            throw new RuntimeException("Missing access token");
        }

        FacebookUser user = facebookService.getUser(token);

    }
}
