package com.kidweek.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.nonNull;

@Data
@AllArgsConstructor
public class Pattern {
    private LocalDate startDate;
    private List<Status> statuses;
}
