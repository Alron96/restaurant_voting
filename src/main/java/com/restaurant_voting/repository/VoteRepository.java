package com.restaurant_voting.repository;

import com.restaurant_voting.model.Vote;
import com.restaurant_voting.util.error.DataConflictException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v WHERE v.voteDate=:day AND v.user.id=:userId")
    Optional<Vote> getByDateAndUserId(@Param("userId") int userId, @Param("day") LocalDate day);

    @Query("SELECT v FROM Vote v WHERE v.voteDate=:day AND v.id=:id AND v.user.id=:userId")
    Optional<Vote> get(@Param("id") int id, @Param("userId") int userId, @Param("day") LocalDate day);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId")
    List<Vote> getAllByUser(@Param("userId") int userId);

    default Vote getExistedOrBelonged(int id, int userId, LocalDate day) {
        return get(id, userId, day).orElseThrow(
                () -> new DataConflictException("Vote id=" + id + " is not exist or doesn't belong to User id=" + userId));
    }
}
