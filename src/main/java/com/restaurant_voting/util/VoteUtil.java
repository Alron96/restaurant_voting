package com.restaurant_voting.util;

import java.time.DateTimeException;
import java.time.LocalTime;

import static com.restaurant_voting.util.TimeUtil.FINAL_VOTE_TIME;

public class VoteUtil {
    public static void checkValidVoteTime(LocalTime voteTime) throws DateTimeException {
        if (voteTime.isAfter(FINAL_VOTE_TIME)) {
            throw new DateTimeException("Голосовать можно только до 11:00");
        }
    }
}