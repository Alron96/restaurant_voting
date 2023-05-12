package com.restaurant_voting.util;

import com.restaurant_voting.model.Restaurant;
import com.restaurant_voting.model.Vote;
import com.restaurant_voting.to.RestaurantTo;
import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {

    public static List<RestaurantTo> asTos(List<Restaurant> restaurants, List<Vote> votes, Vote authUserVote) {
        Map<Integer, Integer> voteCountByRestaurantId = votes.stream()
                .collect(Collectors.toMap(Vote::getRestaurant_fk, Vote::getRestaurant_fk, Integer::sum));

        Integer restIdVotedByAuthUser;
        if (authUserVote == null) {
            restIdVotedByAuthUser = null;
        } else {
            restIdVotedByAuthUser = authUserVote.getRestaurant_fk();
        }

        return restaurants.stream()
                .map(r -> createTo(r, voteCountByRestaurantId.getOrDefault(r.id(), 0),
                        Objects.equals(restIdVotedByAuthUser, r.id()) ? authUserVote : null))
                .toList();
    }

    private static RestaurantTo createTo(Restaurant restTo, int votes, Vote authUserVote) {
        return new RestaurantTo(restTo.id(), restTo.getName(), restTo.getDishes(), votes, authUserVote);
    }
}