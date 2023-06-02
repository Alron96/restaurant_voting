package com.restaurant_voting.web.restaurant;

import com.restaurant_voting.to.RestaurantTo;

import java.util.List;

import static com.restaurant_voting.web.dish.DishTestData.DISHES_TODAY_BY_RESTAURANT_1;
import static com.restaurant_voting.web.dish.DishTestData.DISHES_TODAY_BY_RESTAURANT_2;
import static com.restaurant_voting.web.dish.DishTestData.DISHES_TODAY_BY_RESTAURANT_3;
import static com.restaurant_voting.web.restaurant.RestaurantTestData.RESTAURANT_ID;

public class RestaurantToTestData {
    public static final RestaurantTo RESTAURANT_TO_1 = new RestaurantTo(RESTAURANT_ID, "Клод Мане", DISHES_TODAY_BY_RESTAURANT_1);
    public static final RestaurantTo RESTAURANT_TO_2 = new RestaurantTo(RESTAURANT_ID + 1, "Хачапури и Вино", DISHES_TODAY_BY_RESTAURANT_2);
    public static final RestaurantTo RESTAURANT_TO_3 = new RestaurantTo(RESTAURANT_ID + 2, "KFC", DISHES_TODAY_BY_RESTAURANT_3);
    public static final List<RestaurantTo> RESTAURANTS_WITH_DISHES_AND_VOTES_TODAY = List.of(RESTAURANT_TO_1, RESTAURANT_TO_2, RESTAURANT_TO_3);
}
