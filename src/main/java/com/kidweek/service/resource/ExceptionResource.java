package com.kidweek.service.resource;

import com.kidweek.service.model.Status;
import com.kidweek.service.model.StatusException;
import com.kidweek.service.model.StatusForDate;
import com.kidweek.service.model.User;
import com.kidweek.service.security.KidweekContext;
import com.kidweek.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/me/exception")
public class ExceptionResource {

    @Autowired
    private UserService userService;
    @Autowired
    private KidweekContext context;

    @GetMapping(value = "/{date}")
    public StatusForDate get(@PathVariable @DateTimeFormat(iso = DATE) LocalDate date) {
        return new StatusForDate(Status.PRESENT, date);
    }
    @PostMapping()
    @ResponseStatus(CREATED)
    public User create(
            @RequestParam(name = "access_token") String fbToken,
            @RequestBody StatusException exception) {
        User user = userService.getUser(context.currentUserId());
        user.getExceptions().add(exception);
        return userService.save(user);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        userService.deleteException(id);
        return "OK";
    }
}
