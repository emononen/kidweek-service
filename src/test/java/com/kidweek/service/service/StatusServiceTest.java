package com.kidweek.service.service;

import com.kidweek.service.model.Pattern;
import com.kidweek.service.model.Status;
import com.kidweek.service.model.StatusForDate;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static com.kidweek.service.model.Status.AWAY;
import static com.kidweek.service.model.Status.PRESENT;
import static java.time.LocalDate.now;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StatusServiceTest {

    StatusService service = new StatusService();

    @Test
    public void should_return_status_away_for_today() throws Exception {
        Pattern pattern = new Pattern(now(), asList(AWAY, PRESENT, PRESENT, AWAY, AWAY, PRESENT, AWAY));

        Status status = service.fromPattern(pattern, now());

        assertThat(status, is(AWAY));
    }

    @Test
    public void should_return_status_present_for_tomorrow() throws Exception {
        Pattern pattern = new Pattern(now(), asList(AWAY, PRESENT, PRESENT, AWAY, AWAY, PRESENT, AWAY));

        Status status = service.fromPattern(pattern, now().plusDays(2));

        assertThat(status, is(PRESENT));
    }

    @Test
    public void should_return_status_present_for_august23() throws Exception {
        LocalDate august23 = LocalDate.parse("2017-08-23");
        LocalDate july31 = LocalDate.parse("2017-07-31");
        Pattern pattern = new Pattern(july31, asList(AWAY, PRESENT, PRESENT, AWAY, AWAY, PRESENT, AWAY));

        Status status = service.fromPattern(pattern, august23);

        assertThat(status, is(PRESENT));
    }

}