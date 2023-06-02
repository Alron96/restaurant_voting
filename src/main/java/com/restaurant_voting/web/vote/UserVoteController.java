package com.restaurant_voting.web.vote;

import com.restaurant_voting.model.Vote;
import com.restaurant_voting.repository.VoteRepository;
import com.restaurant_voting.service.VoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.restaurant_voting.web.AuthorizedUser.authId;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class UserVoteController {
    static final String REST_URL = "/api/profile/votes";

    private final VoteRepository repository;
    private final VoteService service;

    @GetMapping
    public List<Vote> getAll() {
        log.info("getAll votes for user {}", authId());
        return repository.getAllByUser(authId());
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        log.info("get vote {} for user {}", id, authId());
        return service.get(id, authId());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam int restaurantId) {
        log.info("update vote for restaurant {} by user {}", restaurantId, authId());
        service.update(restaurantId, authId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vote> create(@RequestParam int restaurantId) {
        log.info("create vote for restaurant {} by user {}", restaurantId, authId());
        Vote created = service.create(restaurantId, authId());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete vote by user {}", authId());
        service.delete(id, authId());
    }
}
