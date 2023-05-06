package com.restaurant_voting.service;

import com.restaurant_voting.model.User;
import com.restaurant_voting.repository.user.UserRepository;
import com.restaurant_voting.to.UserTo;
import com.restaurant_voting.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository repository;

    public User get(int id) {
        return repository.get(id);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public User create(User user) {
        return repository.save(user);
    }

    public void update(User user) {
        repository.save(user);
    }

    @Transactional
    public void update(UserTo userTo) {
        User user = get(userTo.getId());
        repository.save(UserUtil.updateFromTo(user, userTo));
    }
}
