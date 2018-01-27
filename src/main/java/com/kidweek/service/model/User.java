package com.kidweek.service.model;

import com.kidweek.service.security.FacebookAuthentication;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.lang.Math.toIntExact;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel(description = "User")
public class User implements Serializable {
    @Id
    private String id; // facebook id
    @Transient
    private String name;
    @OneToMany(fetch = EAGER, cascade = ALL)
    private Set<Pattern> patterns = new HashSet<>();
    @OneToMany(fetch = EAGER, cascade = ALL)
    @ApiModelProperty(value = "Exceptions")
    private Set<StatusException> exceptions = new HashSet<>();

    public List<StatusForDate> calendarFor(YearMonth yearMonth) {
        List<StatusForDate> result = new ArrayList<>();
        for (int day = 1; day <= yearMonth.lengthOfMonth(); day++){
            LocalDate date = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), day);
            result.add(statusFor(date));
        }
        return result;
    }

    public StatusForDate statusFor(LocalDate date) {
        Optional<StatusException> exception = exceptionForDate(date);
        if (exception.isPresent()) {
            return new StatusForDate(exception.get().getStatus(), date);
        }
        Optional<Pattern> pattern = patternForDate(date);
        if (pattern.isPresent()) {
            return new StatusForDate(fromPattern(pattern.get(), date), date);
        }
        return new StatusForDate(Status.UNKNOWN, date);

    }

    public Optional<Pattern> patternForDate(LocalDate date) {

        List<Pattern> candidates = new ArrayList<>();

        for (Pattern pattern : patterns) {
            if (pattern.getStartDate().isEqual(date) || pattern.getStartDate().isBefore(date)) {
                candidates.add(pattern);
            }
        }

        Collections.sort(candidates, Comparator.comparing(Pattern::getCreatedOn));

        return candidates.isEmpty() ? Optional.empty() : Optional.of(candidates.get(0));
    }

    public Optional<StatusException> exceptionForDate(LocalDate date) {
        for (StatusException exception : exceptions) {
            if ((exception.getStart().isEqual(date) || exception.getStart().isBefore(date))
                    && date.isBefore(exception.getEnd()) || date.isEqual(exception.getEnd())) {
                return Optional.of(exception);
            }
        }
        return Optional.empty();
    }

    private Status fromPattern(Pattern pattern, LocalDate date) {
        LocalDate startDate = pattern.getStartDate();
        int between = toIntExact(ChronoUnit.DAYS.between(startDate, date));
        int patternSize = pattern.getStatuses().size();
        if (between < patternSize) {
            return pattern.getStatuses().get(between);
        }
        int index = between % pattern.getStatuses().size();
        return pattern.getStatuses().get(index);

    }

}
