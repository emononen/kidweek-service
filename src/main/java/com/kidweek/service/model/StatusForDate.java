package com.kidweek.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class StatusForDate {

    private Status status;
    private LocalDate date;

}
