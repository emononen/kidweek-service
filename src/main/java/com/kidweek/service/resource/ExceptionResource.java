package com.kidweek.service.resource;

import com.kidweek.service.model.Status;
import com.kidweek.service.model.StatusException;
import com.kidweek.service.model.StatusForDate;
import com.kidweek.service.model.User;
import com.kidweek.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.kidweek.service.model.User.currentUserId;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/me/exception")
public class ExceptionResource {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{date}")
    public StatusForDate get(@PathVariable LocalDate date) {
        return new StatusForDate(Status.PRESENT, date);
    }
    @PostMapping()
    @ResponseStatus(CREATED)
    public User create(
            @RequestParam(name = "access_token") String fbToken,
            @RequestBody StatusException exception) {
        User user = userService.getUser(currentUserId());
        user.getExceptions().add(exception);
        return userService.save(user);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        userService.deleteException(id);
        return "OK";
    }
}
