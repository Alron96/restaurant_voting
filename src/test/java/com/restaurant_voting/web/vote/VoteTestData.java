package com.restaurant_voting.web.vote;

import com.restaurant_voting.model.Vote;
import com.restaurant_voting.web.MatcherFactory;

import static com.restaurant_voting.web.dish.DishTestData.TODAY;
import static com.restaurant_voting.web.restaurant.RestaurantTestData.RESTAURANT_3;
import static com.restaurant_voting.web.restaurant.RestaurantTestData.RESTAURANT_ID;
import static com.restaurant_voting.web.user.UserTestData.USER_1;
import static com.restaurant_voting.web.user.UserTestData.USER_2;
import static com.restaurant_voting.web.user.UserTestData.USER_ID;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user");

    public static final int VOTE_ID = 1;

    public static Vote getNew() {
        Vote newVote = new Vote(null, TODAY, RESTAURANT_3, USER_2);
        newVote.setRestaurant_fk(RESTAURANT_ID + 2);
        newVote.setUser_fk(USER_ID + 1);
        return newVote;
    }

    public static Vote getUpdated() {
        Vote updateVote = new Vote(VOTE_ID, TODAY, RESTAURANT_3, USER_1);
        updateVote.setRestaurant_fk(RESTAURANT_ID + 2);
        updateVote.setUser_fk(USER_ID);
        return updateVote;
    }
}
