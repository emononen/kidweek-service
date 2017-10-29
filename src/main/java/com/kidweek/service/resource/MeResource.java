package com.kidweek.service.resource;


import com.kidweek.service.model.User;
import com.kidweek.service.model.StatusForDate;
import com.kidweek.service.service.FacebookService;
import com.kidweek.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;

import static com.kidweek.service.model.User.currentUserId;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/me")
public class MeResource {

    @Autowired
    private FacebookService facebookService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/info")
    public User info(@RequestParam(name = "access_token") String fbToken) {
        userService.validate(currentUserId());
        return userService.getUser(currentUserId());
    }

    @PostMapping(value = "/register")
    @ResponseStatus(CREATED)
    public User register(@RequestParam(name = "access_token") String fbToken) {
        return userService.register(currentUserId());
    }

    @DeleteMapping(value = "")
    public void delete(@RequestParam(name = "access_token") String fbToken) {
        userService.delete(currentUserId());
    }

    @GetMapping(value = "/calendar/{yearMonth}")
    public List<StatusForDate> calendar(@PathVariable(name = "yearMonth") YearMonth yearMonth) {
        userService.validate(currentUserId());
        return userService.getUser(currentUserId()).calendarFor(yearMonth);
    }

    @GetMapping(value = "/status/{date}")
    public StatusForDate status(@PathVariable(name = "date") LocalDate date) {
        userService.validate(currentUserId());
        return userService.getUser(currentUserId()).statusFor(date);
    }


}
