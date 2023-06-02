package com.restaurant_voting.service;

import com.restaurant_voting.model.Vote;
import com.restaurant_voting.repository.RestaurantRepository;
import com.restaurant_voting.repository.UserRepository;
import com.restaurant_voting.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.restaurant_voting.util.TimeUtil.TODAY;
import static com.restaurant_voting.util.TimeUtil.checkValidVoteTime;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public Vote get(int id, int userId) {
        return voteRepository.getExistedOrBelonged(id, userId, TODAY);
    }

    public Vote create(int restaurantId, int userId) {
        Vote createVote = new Vote(null, LocalDate.now(), null, userRepository.getReferenceById(userId));
        createVote.setUser_fk(userId);
        createVote.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        createVote.setRestaurant_fk(restaurantId);
        return voteRepository.save(createVote);
    }

    @Transactional
    public void update(int restaurantId, int userId) {
        checkValidVoteTime();
        Vote updatedVote = voteRepository.getByDateAndUserId(userId, TODAY).orElse(null);
        if (updatedVote != null) {
            updatedVote.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
            updatedVote.setRestaurant_fk(restaurantId);
            voteRepository.save(updatedVote);
        }
    }

    public void delete(int id, int userId) {
        checkValidVoteTime();
        Vote vote = voteRepository.getExistedOrBelonged(id, userId, TODAY);
        voteRepository.delete(vote);
    }
}
