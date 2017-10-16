package com.kidweek.service.security;

import com.kidweek.service.model.FacebookUser;
import com.kidweek.service.service.FacebookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class FacebookAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private FacebookService facebookService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getParameter("access_token");
        try {
            FacebookUser user = facebookService.getUser(token);
            FacebookAuthentication fbAuthentication = new FacebookAuthentication(user);
            fbAuthentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(fbAuthentication);
        } catch (Exception e) {
            logger.error(e);
        }

        filterChain.doFilter(request, response);
    }
}
