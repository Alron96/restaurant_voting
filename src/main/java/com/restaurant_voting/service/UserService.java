package com.restaurant_voting.service;

import com.restaurant_voting.model.User;
import com.restaurant_voting.repository.user.UserRepository;
import com.restaurant_voting.to.UserTo;
import com.restaurant_voting.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.restaurant_voting.util.UserUtil.prepareToSave;
import static com.restaurant_voting.util.ValidationUtil.checkNotFoundWithId;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return repository.getAll();
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return prepareAndSave(user);
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        prepareAndSave(user);
    }

    @Transactional
    public void update(UserTo userTo) {
        User user = get(userTo.getId());
        prepareAndSave(UserUtil.updateFromTo(user, userTo));
    }

    private User prepareAndSave(User user) {
        return repository.save(prepareToSave(user, passwordEncoder));
    }
}