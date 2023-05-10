package com.restaurant_voting.repository;

import com.restaurant_voting.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @Query(value = "SELECT v FROM Vote v WHERE v.voteDate=:voteDate AND v.user.id=:userId")
    Vote get(@Param("voteDate") LocalDate voteDate, @Param("userId") int userId);

    @Query(value = "SELECT v FROM Vote v WHERE v.voteDate=:voteDate")
    List<Vote> getAllToday(@Param("voteDate") LocalDate voteDate);
}
