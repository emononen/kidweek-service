package com.kidweek.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pattern implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @DateTimeFormat(iso = DATE)
    private LocalDate startDate;
    @ElementCollection(fetch = FetchType.EAGER, targetClass = Status.class)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Status> statuses;
    @Column(nullable = false)
    private LocalDateTime createdOn = LocalDateTime.now();

    public Pattern(LocalDate startDate, List<Status> statuses) {
        this.startDate = startDate;
        this.statuses = statuses;
    }
}
