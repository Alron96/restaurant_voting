package com.restaurant_voting.service;

import com.restaurant_voting.model.Restaurant;
import com.restaurant_voting.repository.restaurant.RestaurantRepository;
import com.restaurant_voting.repository.vote.VoteRepository;
import com.restaurant_voting.to.RestaurantTo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.restaurant_voting.util.RestaurantUtil.asTos;
import static com.restaurant_voting.util.RestaurantUtil.asTosWithoutVotes;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    VoteRepository voteRepository;

    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant get(int id) {
        return restaurantRepository.get(id);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    public void delete(int id) {
        restaurantRepository.delete(id);
    }

    public List<RestaurantTo> getAllWithDishesAndVotes() {
        return asTos(getTosWithoutVotes(), voteRepository.getAllToday());
    }

    private List<RestaurantTo> getTosWithoutVotes() {
        return asTosWithoutVotes(restaurantRepository.getAllWithDishes());
    }
}
