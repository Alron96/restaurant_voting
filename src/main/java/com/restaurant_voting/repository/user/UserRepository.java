package com.restaurant_voting.repository.user;

import com.restaurant_voting.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    User get(int id);

    List<User> getAll();

    boolean delete(int id);

    Optional<User> findByEmailIgnoreCase(String email);
}