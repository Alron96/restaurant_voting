package com.restaurant_voting.service;

import com.restaurant_voting.model.Dish;
import com.restaurant_voting.repository.DishRepository;
import com.restaurant_voting.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        dish.setRestaurant(restaurantRepository.getExisted(restaurantId));
        return dishRepository.save(dish);
    }
}
