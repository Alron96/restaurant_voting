package com.restaurant_voting.web.restaurant;

import com.restaurant_voting.model.Restaurant;
import com.restaurant_voting.service.RestaurantService;
import com.restaurant_voting.to.RestaurantTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public abstract class AbstractRestaurantController {
    @Autowired
    RestaurantService service;

    public Restaurant get(int id) {
        log.info("get restaurant with id={}", id);
        return service.get(id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll restaurants");
        return service.getAll();
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        return service.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {}", restaurant);
        service.update(restaurant);
    }

    public void delete(int id) {
        log.info("delete restaurant with id={}", id);
        service.delete(id);
    }

    public List<RestaurantTo> getAllWithDishesAndVotes() {
        log.info("getAllWithVotesToday restaurants");
        return service.getAllWithDishesAndVotes();
    }
}
