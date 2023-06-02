package com.restaurant_voting.util;

import com.restaurant_voting.model.Restaurant;
import com.restaurant_voting.to.RestaurantTo;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class RestaurantUtil {

    public static List<RestaurantTo> asTos(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantUtil::createTo)
                .toList();
    }

    private static RestaurantTo createTo(Restaurant restTo) {
        return new RestaurantTo(restTo.id(), restTo.getName(), restTo.getDishes());
    }
}