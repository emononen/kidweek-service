package com.kidweek.service.service;

import com.kidweek.service.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static java.lang.Math.toIntExact;

@Service
public class StatusService {

    public StatusForDate statusFor(User user, LocalDate date) {
        Optional<StatusException> exception = user.exceptionForDate(date);
        if (exception.isPresent()) {
            return new StatusForDate(exception.get().getStatus(), date);
        }
        Optional<Pattern> pattern = user.patternForDate(date);
        if (pattern.isPresent()) {
            return new StatusForDate(fromPattern(pattern.get(), date), date);
        }
        return new StatusForDate(Status.UNKNOWN, date);

    }

    Status fromPattern(Pattern pattern, LocalDate date) {
        LocalDate startDate = pattern.getStartDate();
        int between = toIntExact(ChronoUnit.DAYS.between(startDate, date));
        int patternSize = pattern.getStatuses().size();
        if (between <= patternSize) {
            return pattern.getStatuses().get(between);
        }
        int index = between % pattern.getStatuses().size();
        return pattern.getStatuses().get(index);

    }

    Optional<Status> fromException(StatusException statusException, LocalDate date) {
        if (!date.isBefore(statusException.getStart()) && !date.isAfter(statusException.getEnd())) {
            return Optional.of(statusException.getStatus());
        }
        return Optional.empty();
    }
}
