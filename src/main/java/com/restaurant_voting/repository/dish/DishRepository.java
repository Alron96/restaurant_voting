package com.restaurant_voting.repository.dish;

import com.restaurant_voting.model.Dish;

import java.util.List;

public interface DishRepository {
    Dish save(Dish dish, int restaurantId);

    Dish get(int id, int restaurantId);

    List<Dish> getAll(int restaurantId);

    boolean delete(int id, int restaurantId);
}
