package com.restaurant_voting.web.user;

import com.restaurant_voting.model.User;
import com.restaurant_voting.to.UserTo;
import com.restaurant_voting.util.UserUtil;
import com.restaurant_voting.web.AuthorizedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.restaurant_voting.util.ValidationUtil.assureIdConsistent;
import static com.restaurant_voting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = ProfileController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController extends AbstractUserController {
    static final String REST_URL = "/api/profile";

    @GetMapping
    public User get(@AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("get {}", authUser);
        return authUser.getUser();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        super.delete(authUser.getId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@Valid @RequestBody UserTo userTo) {
        log.info("register {}", userTo);
        checkNew(userTo);
        return repository.prepareAndSave(UserUtil.createNewFromTo(userTo));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(@Valid @RequestBody UserTo userTo, @AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("update {} with id={}", userTo, authUser.getId());
        assureIdConsistent(userTo, authUser.getId());
        User user = authUser.getUser();
        repository.prepareAndSave(UserUtil.updateFromTo(user, userTo));
    }
}