package com.kidweek.service.security;

import com.kidweek.service.model.User;
import com.kidweek.service.service.FacebookService;
import com.kidweek.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if  (!request.getRequestURI().startsWith("/api/")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getParameter("access_token");
        try {
            User user = facebookService.getUser(token);
            if (!request.getRequestURI().endsWith("/me/register")) {
                userService.validate(user.getId());
            }
            FacebookAuthentication fbAuthentication = new FacebookAuthentication(user);
            fbAuthentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(fbAuthentication);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            logger.error("Failed to validate token", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token or non-existing user");
        }

    }
}
