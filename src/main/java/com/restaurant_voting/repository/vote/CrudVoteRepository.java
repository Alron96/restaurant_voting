package com.restaurant_voting.repository.vote;

import com.restaurant_voting.model.Vote;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Vote v WHERE v.restaurant.id=:restaurantId AND v.user.id=:userId")
    int delete(@Param("restaurantId") int restaurantId, @Param("userId") int userId);

    @Query(value = "SELECT v FROM Vote v WHERE v.voteDate=:voteDate AND v.user.id=:userId")
    Vote get(@Param("voteDate") LocalDate voteDate, @Param("userId") int userId);

    @Query(value = "SELECT v FROM Vote v WHERE v.voteDate=:voteDate")
    List<Vote> getAllToday(@Param("voteDate") LocalDate voteDate);
}
