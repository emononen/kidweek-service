package com.kidweek.service.service;

import com.kidweek.service.model.Pattern;
import com.kidweek.service.model.StatusException;
import com.kidweek.service.model.User;
import com.kidweek.service.security.KidweekContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    KidweekContext context;

    public User register(String userId) {
        if (userRepository.exists(userId)) {
            throw new IllegalArgumentException(("The user is already registered"));
        }
        User user = new User();
        user.setId(userId);
        return userRepository.save(user);
    }

    public User save(User user) {
        validate(user.getId());
        return userRepository.save(user);
    }

    public void delete(String id) {
        userRepository.delete(id);
    }

    public void deleteException(Long id) {
        User user = getUser();
        Iterator<StatusException> it = user.getExceptions().iterator();
        while(it.hasNext()) {
            if (it.next().getId().equals(id)) {
                it.remove();
            }
        }
        userRepository.save(user);
    }

    public void deletePattern(Long id) {
        User user = getUser();
        Iterator<Pattern> it = user.getPatterns().iterator();
        while(it.hasNext()) {
            if (it.next().getId().equals(id)) {
                it.remove();
            }
        }
        userRepository.save(user);
    }

    public void validate(String userId) {
        if (!userRepository.exists(userId)) {
            throw new UserNotFoundException(userId);
        }
    }

    public User getUser(String userId) {
        User user = userRepository.findOne(userId);
        user.setName(context.currentUser().getName());
        return user;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser() {
        return userRepository.findOne(context.currentUserId());
    }

}
