package com.kidweek.service.resource;

import com.kidweek.service.model.Pattern;
import com.kidweek.service.model.Status;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;

import static com.kidweek.service.model.Status.AWAY;
import static com.kidweek.service.model.Status.PRESENT;

@RestController
@RequestMapping(value = "/api/v1/me/pattern")
public class PatternResource {
    @GetMapping(value = "/{date}")
    public Pattern get(@PathVariable(name = "date") LocalDate date) {
        return new Pattern(LocalDate.now(), Arrays.asList(AWAY, PRESENT, PRESENT, AWAY, AWAY, PRESENT, AWAY));
    }
    @PostMapping()
    public Pattern create(@RequestBody Pattern pattern) {
        return pattern;
    }
    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(name = "id") String id) {
        return "OK";
    }
}
