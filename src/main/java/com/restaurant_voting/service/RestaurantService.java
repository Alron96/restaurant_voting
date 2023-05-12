package com.restaurant_voting.service;

import com.restaurant_voting.model.Vote;
import com.restaurant_voting.repository.RestaurantRepository;
import com.restaurant_voting.repository.VoteRepository;
import com.restaurant_voting.to.RestaurantTo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.restaurant_voting.util.RestaurantUtil.asTos;

@Service
@AllArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final VoteRepository voteRepository;

    public List<RestaurantTo> getAllWithDishesAndVotes(int userId) {
        Vote authUserVote = voteRepository.get(userId);
        return asTos(restaurantRepository.getAllWithDishes(), voteRepository.getAllToday(), authUserVote);
    }
}