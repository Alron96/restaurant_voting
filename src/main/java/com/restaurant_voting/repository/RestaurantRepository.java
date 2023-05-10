package com.restaurant_voting.repository;

import com.restaurant_voting.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query(value = "SELECT r FROM Restaurant r JOIN FETCH r.dishes d WHERE d.dishDate=:day")
    List<Restaurant> getAllWithDishes(@Param("day") LocalDate day);
}
