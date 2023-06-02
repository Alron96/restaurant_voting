package com.restaurant_voting.service;

import com.restaurant_voting.model.Dish;
import com.restaurant_voting.repository.DishRepository;
import com.restaurant_voting.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@AllArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        dish.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        return dishRepository.save(dish);
    }

    @Transactional
    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        dishRepository.getExistedOrBelonged(dish.id(), restaurantId);
        dishRepository.save(dish);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id, int restaurantId) {
        Dish dish = dishRepository.getExistedOrBelonged(id, restaurantId);
        dishRepository.delete(dish);
    }
}
