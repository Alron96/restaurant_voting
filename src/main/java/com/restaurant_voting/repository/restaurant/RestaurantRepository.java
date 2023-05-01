package com.restaurant_voting.repository.restaurant;

import com.restaurant_voting.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    Restaurant get(int id);

    List<Restaurant> getAll();

    List<Restaurant> getAllWithDishes();

    boolean delete(int id);
}
