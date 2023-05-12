package com.restaurant_voting.repository;

import com.restaurant_voting.model.Vote;
import com.restaurant_voting.util.error.DataConflictException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @Query(value = "SELECT v FROM Vote v WHERE v.voteDate=CURRENT_DATE() AND v.user.id=:userId")
    Vote get(@Param("userId") int userId);

    @Query(value = "SELECT v FROM Vote v WHERE v.voteDate=CURRENT_DATE() AND v.id=:id AND v.user.id=:userId")
    Optional<Vote> get(@Param("id") int id, @Param("userId") int userId);

    @Query(value = "SELECT v FROM Vote v WHERE v.voteDate=CURRENT_DATE()")
    List<Vote> getAllToday();

    default Vote getExistedOrBelonged(int id, int userId) {
        return get(id, userId).orElseThrow(
                () -> new DataConflictException("Vote id=" + id + " is not exist or doesn't belong to User id=" + userId));
    }
}
