package com.restaurant_voting.web.restaurant;

import com.restaurant_voting.model.Restaurant;
import com.restaurant_voting.to.RestaurantTo;
import com.restaurant_voting.web.MatcherFactory;

import java.util.List;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "dishes");
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_WITH_DISHES_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(RestaurantTo.class, "dishes.restaurant");

    public static final int NOT_FOUND = 100;

    public static final int RESTAURANT_ID = 1;
    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID, "Клод Мане");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID + 1, "Хачапури и Вино");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_ID + 2, "KFC");
    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT_3, RESTAURANT_1, RESTAURANT_2);


    public static Restaurant getNew() {
        return new Restaurant(null, "Новый ресторан");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Обновленный ресторан");
    }
}
