package com.kidweek.service.resource;

import com.kidweek.service.model.Pattern;
import com.kidweek.service.model.User;
import com.kidweek.service.security.KidweekContext;
import com.kidweek.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/me/pattern")
public class PatternResource {

    @Autowired
    private UserService userService;
    @Autowired
    private KidweekContext context;
    @GetMapping(value = "/{date}")
    public ResponseEntity<Pattern> get(@PathVariable(name = "date") @DateTimeFormat(iso = DATE) LocalDate date,
                              @RequestParam(name = "access_token") String fbToken) {
        User user = userService.getUser(context.currentUserId());
        Optional<Pattern> pattern = user.patternForDate(date);
        return pattern.isPresent()
                ? new ResponseEntity<>(pattern.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public User create(@RequestParam(name = "access_token") String fbToken,
                          @RequestBody Pattern pattern) {
        User user = userService.getUser(context.currentUserId());
        pattern.setCreatedOn(LocalDateTime.now());
        user.getPatterns().add(pattern);
        return userService.save(user);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        userService.deletePattern(id);
        return "OK";
    }
}
