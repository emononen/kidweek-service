package com.kidweek.service.model;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;

import static com.kidweek.service.model.Status.AWAY;
import static com.kidweek.service.model.Status.PRESENT;
import static java.time.LocalDate.now;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {

    @Test
    public void should_return_status_away_for_today() throws Exception {
        Pattern pattern = new Pattern(now(), asList(AWAY, PRESENT, PRESENT, AWAY, AWAY, PRESENT, AWAY));

        User user = new User();
        user.setPatterns(Collections.singleton(pattern));

        assertThat(user.statusFor(now()).getStatus(), is(AWAY));
        assertThat(user.statusFor(now()).getDate(), is(now()));
    }
    @Test
    public void should_return_status_present_for_day_after_tomorrow() throws Exception {
        Pattern pattern = new Pattern(now(), asList(AWAY, PRESENT, PRESENT, AWAY, AWAY, PRESENT, AWAY));
        LocalDate dayAfterTomorrow = now().plusDays(2);

        User user = new User();
        user.setPatterns(Collections.singleton(pattern));

        assertThat(user.statusFor(dayAfterTomorrow), is(new StatusForDate(PRESENT, dayAfterTomorrow)));
    }

    @Test
    public void should_return_status_present_for_august23() throws Exception {
        LocalDate august23 = LocalDate.parse("2017-08-23");
        LocalDate july31 = LocalDate.parse("2017-07-31");
        Pattern pattern = new Pattern(july31, asList(AWAY, PRESENT, PRESENT, AWAY, AWAY, PRESENT, AWAY));

        User user = new User();
        user.setPatterns(Collections.singleton(pattern));

        StatusForDate status = user.statusFor(august23);

        assertThat(status.getStatus(), is(PRESENT));
        assertThat(status.getDate(), is(august23));
    }

}