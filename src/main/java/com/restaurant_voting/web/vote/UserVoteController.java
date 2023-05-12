package com.restaurant_voting.web.vote;

import com.restaurant_voting.model.Vote;
import com.restaurant_voting.repository.VoteRepository;
import com.restaurant_voting.service.VoteService;
import com.restaurant_voting.web.AuthorizedUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

import static com.restaurant_voting.util.TimeUtil.checkValidVoteTime;
import static com.restaurant_voting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class UserVoteController {
    static final String REST_URL = "/api/restaurants";

    private final VoteRepository repository;
    private final VoteService service;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime voteTime,
                       @Valid @RequestBody Vote vote,
                       @AuthenticationPrincipal AuthorizedUser authUser) {
        int userId = authUser.getId();
        log.info("update {} by user={} to restaurantId={}", vote, userId, vote.getRestaurant_fk());
        checkValidVoteTime(voteTime);
        repository.getExistedOrBelonged(vote.id(), userId);
        service.save(vote, userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Vote create(@RequestParam @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime voteTime,
                       @Valid @RequestBody Vote vote,
                       @AuthenticationPrincipal AuthorizedUser authUser) {
        int userId = authUser.getId();
        log.info("save {}} by userId={} to restaurantId={}", vote, userId, vote.getRestaurant_fk());
        checkValidVoteTime(voteTime);
        checkNew(vote);
        return service.save(vote, userId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalTime voteTime,
                       @RequestParam @NotNull int id,
                       @AuthenticationPrincipal AuthorizedUser authUser) {
        int userId = authUser.getId();
        log.info("delete vote with id={} by userId={}", id, userId);
        checkValidVoteTime(voteTime);
        Vote vote = repository.getExistedOrBelonged(id, userId);
        repository.delete(vote);
    }
}
