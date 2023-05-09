package com.restaurant_voting.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalTime;

@UtilityClass
public class TimeUtil {
    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalTime FINAL_VOTE_TIME = LocalTime.of(11, 0);
}
