package com.restaurant_voting.repository.dish;

import com.restaurant_voting.model.Dish;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor
public class DishRepositoryImpl implements DishRepository {
    private final CrudDishRepository crudDishRepository;

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        return crudDishRepository.save(dish);
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return crudDishRepository.findById(id)
                .filter(dish -> dish.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return crudDishRepository.getAll(restaurantId);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudDishRepository.delete(id, restaurantId) != 0;
    }
}
