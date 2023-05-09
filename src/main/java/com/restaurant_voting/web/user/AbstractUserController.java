package com.restaurant_voting.web.user;

import com.restaurant_voting.model.User;
import com.restaurant_voting.service.UserService;
import com.restaurant_voting.to.UserTo;
import com.restaurant_voting.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.restaurant_voting.util.ValidationUtil.assureIdConsistent;
import static com.restaurant_voting.util.ValidationUtil.checkNew;

@Slf4j
public abstract class AbstractUserController {

    @Autowired
    private UserService service;

    public User get(int id) {
        log.info("get user with id={}", id);
        return service.get(id);
    }

    public List<User> getAll() {
        log.info("getAll users");
        return service.getAll();
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public User create(UserTo userTo) {
        log.info("create {}", userTo);
        checkNew(userTo);
        return service.create(UserUtil.createNewFromTo(userTo));
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        service.update(userTo);
    }

    public void delete(int id) {
        log.info("delete user with id={}", id);
        service.delete(id);
    }
}