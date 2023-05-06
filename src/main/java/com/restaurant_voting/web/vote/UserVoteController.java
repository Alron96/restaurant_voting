package com.restaurant_voting.web.vote;

import com.restaurant_voting.repository.vote.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

import static com.restaurant_voting.util.VoteUtil.checkValidVoteTime;

@Slf4j
@RestController
@RequestMapping(value = "/api/profile/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVoteController {
    @Autowired
    VoteRepository repository;

    @PostMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(LocalTime voteTime, @PathVariable int restaurantId, int userId) {
        log.info("save vote by userId={} to restaurantId={}", userId, restaurantId);
        checkValidVoteTime(voteTime);
        repository.save(restaurantId, userId);
    }

    @DeleteMapping(value = "/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId, int userId) {
        log.info("delete vote by userId={} to restaurantId={}", userId, restaurantId);
        repository.delete(restaurantId, userId);
    }
}
