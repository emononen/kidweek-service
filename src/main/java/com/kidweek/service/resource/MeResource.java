package com.kidweek.service.resource;


import com.kidweek.service.model.Status;
import com.kidweek.service.model.StatusForDate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/me")
public class MeResource {

    @GetMapping(value = "/status/{date}")
    public StatusForDate statusForDate(@PathVariable(name = "date") LocalDate date) {
        return new StatusForDate(Status.PRESENT, date);
    }
    @GetMapping(value = "/calendar/{year-month}")
    public List<StatusForDate> calendar(@PathVariable(name = "id") String id,
                                        @PathVariable(name = "year") YearMonth yearMonth) {
        return Collections.emptyList();
    }

    @PostMapping()
    public String create() {
        return "OK";
    }

}
