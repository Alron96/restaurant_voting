package com.restaurant_voting.service;

import com.restaurant_voting.repository.RestaurantRepository;
import com.restaurant_voting.repository.VoteRepository;
import com.restaurant_voting.to.RestaurantTo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.restaurant_voting.util.RestaurantUtil.asTos;
import static com.restaurant_voting.util.TimeUtil.TODAY;

@Service
@AllArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final VoteRepository voteRepository;

    public List<RestaurantTo> getAllWithDishesAndVotes() {
        return asTos(restaurantRepository.getAllWithDishes(TODAY), voteRepository.getAllToday(TODAY));
    }
}