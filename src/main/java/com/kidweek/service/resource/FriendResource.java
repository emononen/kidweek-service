package com.kidweek.service.resource;

import com.kidweek.service.model.FriendStatus;
import com.kidweek.service.model.StatusForDate;
import com.kidweek.service.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/friends")
public class FriendResource {

    @Autowired
    FriendService friendService;

    @GetMapping(value = "/{date}")
    public List<FriendStatus> get(@PathVariable(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                  @RequestParam(name = "access_token") String fbToken) {
        return friendService.statusesFor(date, fbToken);
    }

    @GetMapping(value = "/{id}/calendar/{year-month}")
    public List<StatusForDate> calendar(@PathVariable(name = "id") String id,
                                        @PathVariable(name = "year-month") YearMonth yearMonth,
                                        @RequestParam(name = "access_token") String fbToken) {
        return friendService.calendarFor(id, yearMonth);
    }
}
