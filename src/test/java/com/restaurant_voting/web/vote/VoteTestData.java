package com.restaurant_voting.web.vote;

import com.restaurant_voting.model.Vote;
import com.restaurant_voting.web.MatcherFactory;

import java.time.LocalTime;

import static com.restaurant_voting.web.dish.DishTestData.TODAY;
import static com.restaurant_voting.web.restaurant.RestaurantTestData.RESTAURANT_3;
import static com.restaurant_voting.web.user.UserTestData.USER_1;
import static com.restaurant_voting.web.user.UserTestData.USER_2;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user");

    public static final int VOTE_ID = 1;
    public static final LocalTime VALID_VOTE_TIME = LocalTime.of(10, 0);
    public static final LocalTime INVALID_VOTE_TIME = LocalTime.of(14, 0);

    public static Vote getNew() {
        return new Vote(null, TODAY, RESTAURANT_3, USER_2);
    }

    public static Vote getUpdated() {
        return new Vote(VOTE_ID, TODAY, RESTAURANT_3, USER_1);
    }
}
