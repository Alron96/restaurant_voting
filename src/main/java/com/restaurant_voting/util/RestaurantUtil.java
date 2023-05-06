package com.restaurant_voting.util;

import com.restaurant_voting.model.Restaurant;
import com.restaurant_voting.model.Vote;
import com.restaurant_voting.to.RestaurantTo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RestaurantUtil {

    public static List<RestaurantTo> asTos(List<RestaurantTo> restaurants, List<Vote> votes) {
        Map<Integer, Integer> voteCountByRestaurantId = votes.stream()
                .collect(Collectors.toMap(Vote::getRestaurant_fk, Vote::getRestaurant_fk, Integer::sum));
        return restaurants.stream()
                .peek(r -> r.setVotes(voteCountByRestaurantId.getOrDefault(r.getId(), 0)))
                .toList();
    }

    public static List<RestaurantTo> asTosWithoutVotes(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantUtil::createToWithoutVotes)
                .toList();
    }

    private static RestaurantTo createToWithoutVotes(Restaurant restaurantTo) {
        return new RestaurantTo(restaurantTo.getId(), restaurantTo.getName(), restaurantTo.getDishes(), 0);
    }
}
