package com.kidweek.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendStatus {
    private String id;
    private String name;
    private Status status;
}
