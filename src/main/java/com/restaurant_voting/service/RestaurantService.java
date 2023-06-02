package com.restaurant_voting.service;

import com.restaurant_voting.model.Restaurant;
import com.restaurant_voting.repository.RestaurantRepository;
import com.restaurant_voting.repository.VoteRepository;
import com.restaurant_voting.to.RestaurantTo;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.restaurant_voting.util.RestaurantUtil.asTos;
import static com.restaurant_voting.util.TimeUtil.TODAY;

@Service
@AllArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final VoteRepository voteRepository;

    public Restaurant get(int id) {
        return restaurantRepository.getExisted(id);
    }

    public List<RestaurantTo> getAllWithDishesAndVotesToday() {
        return asTos(restaurantRepository.getAllWithDishesByDate(TODAY));
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) {
        restaurantRepository.deleteExisted(id);
    }
}