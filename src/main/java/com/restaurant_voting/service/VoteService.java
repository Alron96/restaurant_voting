package com.restaurant_voting.service;

import com.restaurant_voting.model.Vote;
import com.restaurant_voting.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.restaurant_voting.util.TimeUtil.TODAY;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void save(int restaurantId, int userId) {
        Vote updated = voteRepository.get(TODAY, userId);
        if (updated == null) {
            updated = new Vote(null, TODAY, null, userRepository.getReferenceById(userId));
        }
        updated.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        voteRepository.save(updated);
    }

    @Transactional
    public void delete(int userId) {
        Vote toDelete = voteRepository.get(TODAY, userId);
        voteRepository.delete(toDelete);
    }
}
