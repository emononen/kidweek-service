package com.kidweek.service.resource;

import com.kidweek.service.model.FriendStatus;
import com.kidweek.service.model.StatusForDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/friends")
public class FriendResource {

    @GetMapping(value = "/{date}")
    public List<FriendStatus> get(@PathVariable(name = "date") LocalDate date) {
        return null;
    }

    @GetMapping(value = "/{id}/calendar/{year-month}")
    public List<StatusForDate> calendar(@PathVariable(name = "id") String id,
                                        @PathVariable(name = "year-month") YearMonth yearMonth) {
        return Collections.emptyList();
    }
}
