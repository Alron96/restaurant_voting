package com.restaurant_voting.util;

import com.restaurant_voting.model.Restaurant;
import com.restaurant_voting.model.Vote;
import com.restaurant_voting.to.RestaurantTo;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {

    public static List<RestaurantTo> asTos(List<Restaurant> restaurants, List<Vote> votes) {
        Map<Integer, Integer> voteCountByRestaurantId = votes.stream()
                .collect(Collectors.toMap(Vote::getRestaurant_fk, Vote::getRestaurant_fk, Integer::sum));
        return restaurants.stream()
                .map(r -> createTo(r, voteCountByRestaurantId.getOrDefault(r.getId(), 0)))
                .toList();
    }

    private static RestaurantTo createTo(Restaurant restaurantTo, int votes) {
        return new RestaurantTo(restaurantTo.getId(), restaurantTo.getName(), restaurantTo.getDishes(), votes);
    }
}