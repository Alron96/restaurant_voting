package com.restaurant_voting.repository.user;

import com.restaurant_voting.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final CrudUserRepository crudUserRepository;

    @Override
    public User save(User user) {
        return crudUserRepository.save(user);
    }

    @Override
    public User get(int id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return crudUserRepository.findAll();
    }

    @Override
    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }
}
