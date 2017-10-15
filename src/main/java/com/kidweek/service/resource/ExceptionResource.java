package com.kidweek.service.resource;

import com.kidweek.service.model.Status;
import com.kidweek.service.model.StatusForDate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/v1/me/exception")
public class ExceptionResource {

    @GetMapping(value = "/{date}")
    public StatusForDate get(@PathVariable LocalDate date) {
        return new StatusForDate(Status.PRESENT, date);
    }
    @PostMapping()
    public StatusForDate create(@RequestBody StatusForDate exception) {
        return exception;
    }
    @DeleteMapping(value = "/{date}")
    public StatusForDate delete(@PathVariable LocalDate date) {
        return new StatusForDate(Status.PRESENT, date);
    }
}
