package com.restaurant_voting.web.dish;

import com.restaurant_voting.model.Dish;
import com.restaurant_voting.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static com.restaurant_voting.web.restaurant.RestaurantTestData.RESTAURANT_1;
import static com.restaurant_voting.web.restaurant.RestaurantTestData.RESTAURANT_2;
import static com.restaurant_voting.web.restaurant.RestaurantTestData.RESTAURANT_3;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate YESTERDAY = TODAY.minusDays(1);
    public static final int DISH_ID = 1;

    public static final Dish DISH_1 = new Dish(DISH_ID, "Бефстроганов", 1500, TODAY, RESTAURANT_1);
    public static final Dish DISH_2 = new Dish(DISH_ID + 1, "Борщ", 1000, TODAY, RESTAURANT_1);
    public static final Dish DISH_3 = new Dish(DISH_ID + 2, "Оливье", 700, TODAY, RESTAURANT_1);

    public static final Dish DISH_4 = new Dish(DISH_ID + 3, "Вчерашний Бефстроганов", 1500, YESTERDAY, RESTAURANT_1);
    public static final Dish DISH_5 = new Dish(DISH_ID + 4, "Вчерашний Борщ", 1000, YESTERDAY, RESTAURANT_1);
    public static final Dish DISH_6 = new Dish(DISH_ID + 5, "Вчерашнее Оливье", 700, YESTERDAY, RESTAURANT_1);

    public static final Dish DISH_7 = new Dish(DISH_ID + 6, "Хачапури", 800, TODAY, RESTAURANT_2);
    public static final Dish DISH_8 = new Dish(DISH_ID + 7, "Вино", 1200, TODAY, RESTAURANT_2);

    public static final Dish DISH_9 = new Dish(DISH_ID + 10, "Кола", 100, TODAY, RESTAURANT_3);
    public static final Dish DISH_10 = new Dish(DISH_ID + 11, "Бургер", 150, TODAY, RESTAURANT_3);
    public static final Dish DISH_11 = new Dish(DISH_ID + 12, "Баскет", 400, TODAY, RESTAURANT_3);
    public static final Dish DISH_12 = new Dish(DISH_ID + 13, "Мороженое", 100, TODAY, RESTAURANT_3);

    public static final List<Dish> DISHES_TODAY_BY_RESTAURANT_1 = List.of(DISH_1, DISH_2, DISH_3);
    public static final List<Dish> DISHES_TODAY_BY_RESTAURANT_2 = List.of(DISH_7, DISH_8);
    public static final List<Dish> DISHES_TODAY_BY_RESTAURANT_3 = List.of(DISH_9, DISH_10, DISH_11, DISH_12);

    public static final List<Dish> DISHES_ALL_BY_RESTAURANT_1 = List.of(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6);

    public static Dish getNew() {
        return new Dish(null, "Новое блюдо", 30000, TODAY, RESTAURANT_1);
    }

    public static Dish getUpdated() {
        return new Dish(DISH_ID, "Обновленное блюдо", 10000, TODAY, RESTAURANT_1);
    }
}
