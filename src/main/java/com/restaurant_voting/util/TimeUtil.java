package com.restaurant_voting.util;

import com.restaurant_voting.util.error.DataConflictException;
import lombok.experimental.UtilityClass;

import java.time.LocalTime;

@UtilityClass
public class TimeUtil {
    public static final LocalTime FINAL_VOTE_TIME = LocalTime.of(11, 0);

    public static void checkValidVoteTime(LocalTime voteTime) {
        if (voteTime.isAfter(FINAL_VOTE_TIME)) {
            throw new DataConflictException("Голосовать можно только до 11:00");
        }
    }
}
