package com.restaurant_voting.repository.vote;

import com.restaurant_voting.model.Vote;
import com.restaurant_voting.repository.restaurant.CrudRestaurantRepository;
import com.restaurant_voting.repository.user.CrudUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.restaurant_voting.util.TimeUtil.TODAY;


@Repository
@AllArgsConstructor
public class VoteRepositoryImpl implements VoteRepository {
    private final CrudVoteRepository crudVoteRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudUserRepository crudUserRepository;

    @Override
    public Vote save(int restaurantId, int userId) {
        Vote updated = get(TODAY, userId);
        if (updated == null) {
            updated = new Vote(null, TODAY, null, crudUserRepository.getReferenceById(userId));
        }
        updated.setRestaurant(crudRestaurantRepository.getReferenceById(restaurantId));
        return crudVoteRepository.save(updated);
    }

    @Override
    public Vote get(LocalDate voteDate, int userId) {
        return crudVoteRepository.get(voteDate, userId);
    }

    @Override
    public List<Vote> getAllToday() {
        return crudVoteRepository.getAllToday(TODAY);
    }

    @Override
    public boolean delete(int restaurantId, int userId) {
        return crudVoteRepository.delete(restaurantId, userId) != 0;
    }
}
