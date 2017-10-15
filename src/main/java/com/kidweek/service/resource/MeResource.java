package com.kidweek.service.resource;


import com.kidweek.service.model.FacebookUser;
import com.kidweek.service.model.Status;
import com.kidweek.service.model.StatusForDate;
import com.kidweek.service.service.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/me")
public class MeResource {

    @Autowired
    private FacebookService facebookService;

    @GetMapping(value = "")
    public FacebookUser me(@RequestParam(name = "access_token") String fbToken) {
        return facebookService.getUser(fbToken);
    }

    @GetMapping(value = "/calendar/{yearMonth}")
    public List<StatusForDate> calendar(@PathVariable(name = "yearMonth") YearMonth yearMonth) {
        return Collections.emptyList();
    }

    @PostMapping()
    public String create() {
        return "OK";
    }

}
