package com.restaurant_voting.web.user;

import com.restaurant_voting.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractUserController {
    protected final Logger log = getLogger(getClass());

    @Autowired
    protected UserRepository repository;

    public void delete(int id) {
        log.info("delete user with id={}", id);
        repository.deleteExisted(id);
    }
}