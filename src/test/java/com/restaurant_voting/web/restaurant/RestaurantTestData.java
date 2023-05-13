package com.restaurant_voting.web.restaurant;

import com.restaurant_voting.model.Restaurant;
import com.restaurant_voting.to.RestaurantTo;
import com.restaurant_voting.web.MatcherFactory;

import java.util.List;

import static com.restaurant_voting.web.dish.DishTestData.DISHES_TODAY_BY_RESTAURANT_1;
import static com.restaurant_voting.web.dish.DishTestData.DISHES_TODAY_BY_RESTAURANT_2;
import static com.restaurant_voting.web.dish.DishTestData.DISHES_TODAY_BY_RESTAURANT_3;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "dishes");
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_WITH_DISHES_AND_VOTES_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(RestaurantTo.class, "dishes.restaurant", "authUserVote");

    public static final int VOTES_RESTAURANT_1 = 1;
    public static final int VOTES_RESTAURANT_2 = 0;
    public static final int VOTES_RESTAURANT_3 = 0;
    public static final int NOT_FOUND = 100;

    public static final int RESTAURANT_ID = 1;
    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID, "Клод Мане");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID + 1, "Хачапури и Вино");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_ID + 2, "KFC");
    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT_3, RESTAURANT_1, RESTAURANT_2);

    public static final RestaurantTo RESTAURANT_TO_1 = new RestaurantTo(RESTAURANT_ID, "Клод Мане", DISHES_TODAY_BY_RESTAURANT_1, VOTES_RESTAURANT_1, null);
    public static final RestaurantTo RESTAURANT_TO_2 = new RestaurantTo(RESTAURANT_ID + 1, "Хачапури и Вино", DISHES_TODAY_BY_RESTAURANT_2, VOTES_RESTAURANT_2, null);
    public static final RestaurantTo RESTAURANT_TO_3 = new RestaurantTo(RESTAURANT_ID + 2, "KFC", DISHES_TODAY_BY_RESTAURANT_3, VOTES_RESTAURANT_3, null);
    public static final List<RestaurantTo> RESTAURANTS_WITH_DISHES_AND_VOTES_TODAY = List.of(RESTAURANT_TO_1, RESTAURANT_TO_2, RESTAURANT_TO_3);

    public static Restaurant getNew() {
        return new Restaurant(null, "Новый ресторан");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Обновленный ресторан");
    }
}
