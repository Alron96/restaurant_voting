package com.restaurant_voting.repository;

import com.restaurant_voting.model.Restaurant;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query(value = "SELECT r FROM Restaurant r JOIN FETCH r.dishes d WHERE d.dishDate=CURRENT_DATE()")
    @Cacheable("restaurants")
    List<Restaurant> getAllWithDishes();
}
