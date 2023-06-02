package com.restaurant_voting.service;

import com.restaurant_voting.model.User;
import com.restaurant_voting.repository.UserRepository;
import com.restaurant_voting.to.UserTo;
import com.restaurant_voting.util.UserUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static com.restaurant_voting.config.SecurityConfig.PASSWORD_ENCODER;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User get(int id) {
        return userRepository.getExisted(id);
    }

    public void delete(int id) {
        userRepository.deleteExisted(id);
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return prepareAndSave(user);
    }

    public User create(UserTo userTo) {
        Assert.notNull(userTo, "user must not be null");
        return prepareAndSave(UserUtil.createNewFromTo(userTo));
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        prepareAndSave(user);
    }

    @Transactional
    public void update(UserTo userTo) {
        Assert.notNull(userTo, "user must not be null");
        User user = get(userTo.id());
        prepareAndSave(UserUtil.updateFromTo(user, userTo));
    }

    private User prepareAndSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return userRepository.save(user);
    }
}
