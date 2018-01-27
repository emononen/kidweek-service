package com.kidweek.service.security;

import com.kidweek.service.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class KidweekContext {

    public String currentUserId() {
        return currentUser().getId();
    }

    public User currentUser() {
        FacebookAuthentication authentication =
                (FacebookAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getDetails();
    }
}
