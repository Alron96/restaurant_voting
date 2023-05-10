package com.restaurant_voting.web.user;

import com.restaurant_voting.model.User;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.restaurant_voting.util.ValidationUtil.assureIdConsistent;
import static com.restaurant_voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController extends AbstractUserController {
    static final String REST_URL = "/api/admin/users";

    @GetMapping
    public List<User> getAll() {
        log.info("getAll users");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name", "email"));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {
        log.info("create {}", user);
        checkNew(user);
        return repository.prepareAndSave(user);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @PathVariable int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        repository.prepareAndSave(user);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        log.info("get user with id={}", id);
        return repository.getExisted(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
