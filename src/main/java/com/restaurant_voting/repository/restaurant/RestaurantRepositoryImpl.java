package com.restaurant_voting.repository.restaurant;

import com.restaurant_voting.model.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.restaurant_voting.util.RestaurantUtil.TODAY;


@Repository
@AllArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;


    @Override
    public Restaurant save(Restaurant restaurant) {
        if (!restaurant.isNew() && get(restaurant.getId()) == null) {
            return null;
        }
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> getAllWithDishes() {
        return crudRestaurantRepository.getAllWithDishes(TODAY);
    }

    @Override
    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }
}
