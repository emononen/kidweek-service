package com.kidweek.service.resource;


import com.kidweek.service.model.StatusForDate;
import com.kidweek.service.model.User;
import com.kidweek.service.security.KidweekContext;
import com.kidweek.service.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/me")
public class MeResource {

    @Autowired
    private UserService userService;
    @Autowired
    private KidweekContext context;

    @GetMapping(value = "/info")
    @ApiOperation(value = "Information about the current user")
    public User info(@ApiParam(value = "Facebook access token", required = true) @RequestParam(name = "access_token") String fbToken) {
        return userService.getUser(context.currentUserId());
    }

    @PostMapping(value = "/register")
    @ApiOperation(value = "Creates a new user")
    @ResponseStatus(CREATED)
    public User register(@ApiParam(value = "Facebook access token", required = true) @RequestParam(name = "access_token") String fbToken) {
        return userService.register(context.currentUserId());
    }

    @DeleteMapping(value = "")
    @ApiOperation(value = "Deletes the current user")
    public void delete(@ApiParam(value = "Facebook access token", required = true) @RequestParam(name = "access_token") String fbToken) {
        userService.delete(context.currentUserId());
    }

    @GetMapping(value = "/calendar/{yearMonth}")
    @ApiOperation(value = "Fetches the statuses for a given month")
    public List<StatusForDate> calendar(
            @ApiParam(value = "Facebook access token", required = true) @RequestParam(name = "access_token") String fbToken,
            @ApiParam(value = "Year and month, e.g. \"2017-01\"") @PathVariable(name = "yearMonth") YearMonth yearMonth) {
        return userService.getUser(context.currentUserId()).calendarFor(yearMonth);
    }

    @GetMapping(value = "/status/{date}")
    @ApiOperation(value = "Fetches the status for a given day")
    public StatusForDate status(
            @ApiParam(value = "Date for the status, e.g. \"2017-12-21\"", example = "2017-12-21")
            @PathVariable(name = "date")
            @DateTimeFormat(iso = DATE) LocalDate date) {
        return userService.getUser(context.currentUserId()).statusFor(date);
    }


}
