package com.restaurant_voting.util;

import com.restaurant_voting.util.error.DataConflictException;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class TimeUtil {
    public static LocalTime FINAL_VOTE_TIME = LocalTime.of(11, 0);

    public static void setEndVoteTimeForTests(LocalTime time) {
        FINAL_VOTE_TIME = time;
    }

    public static final LocalDate TODAY = LocalDate.now();

    public static void checkValidVoteTime() {
        if (LocalTime.now().isAfter(FINAL_VOTE_TIME)) {
            throw new DataConflictException("Голосовать можно только до " + FINAL_VOTE_TIME.format(DateTimeFormatter.ofPattern("HH:mm")));
        }
    }
}
