package com.restaurant_voting.web.vote;

import com.restaurant_voting.service.VoteService;
import com.restaurant_voting.web.AuthorizedUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

import static com.restaurant_voting.util.TimeUtil.checkValidVoteTime;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class UserVoteController {
    static final String REST_URL = "/api/profile/restaurants";

    private final VoteService service;

    @PostMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(LocalTime voteTime, @PathVariable int restaurantId, @AuthenticationPrincipal AuthorizedUser authUser) {
        int userId = authUser.getId();
        log.info("save vote by userId={} to restaurantId={}", userId, restaurantId);
        checkValidVoteTime(voteTime);
        service.save(restaurantId, userId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        int userId = authUser.getId();
        log.info("delete vote by userId={}", userId);
        service.delete(userId);
    }
}
