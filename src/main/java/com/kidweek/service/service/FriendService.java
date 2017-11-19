package com.kidweek.service.service;

import com.kidweek.service.model.FriendList;
import com.kidweek.service.model.FriendStatus;
import com.kidweek.service.model.StatusForDate;
import com.kidweek.service.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class FriendService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FacebookService facebookService;

    public List<FriendStatus> statusesFor(LocalDate date, String fbToken) {
        List<FriendStatus> result = new ArrayList<>();
        FriendList friends = facebookService.friends(fbToken);
        for (User friend : friends.getFriends()) {
            User registeredFriend = userRepository.findOne(friend.getId());
            if (registeredFriend != null) {
                result.add(new FriendStatus(registeredFriend.getId(), registeredFriend.getName(), registeredFriend.statusFor(date)));
            }
        }
        return result;
    }

    public List<StatusForDate> calendarFor(String friendId, YearMonth yearMonth) {
        User friend = userRepository.findOne(friendId);
        if (friend == null) {
            return Collections.emptyList();
        }
        return friend.calendarFor(yearMonth);
    }
}