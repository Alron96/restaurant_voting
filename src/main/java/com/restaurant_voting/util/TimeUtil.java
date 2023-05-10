package com.restaurant_voting.util;

import lombok.experimental.UtilityClass;

import java.time.*;

@UtilityClass
public class TimeUtil {
    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalTime FINAL_VOTE_TIME = LocalTime.of(11, 0);

    public static void checkValidVoteTime(LocalTime voteTime) throws DateTimeException {
        if (voteTime.isAfter(FINAL_VOTE_TIME)) {
            throw new DateTimeException("Голосовать можно только до 11:00");
        }
    }
}
