package com.restaurant_voting.repository.vote;

import com.restaurant_voting.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(int restaurantId, int userId);

    Vote get(LocalDate voteDate, int userId);

    List<Vote> getAllToday();

    boolean delete(int restaurantId, int userId);
}
