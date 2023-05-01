package com.restaurant_voting.repository.restaurant;

import com.restaurant_voting.model.Restaurant;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query(value = "SELECT r FROM Restaurant r JOIN FETCH r.dishes d WHERE d.dishDate=:day")
    List<Restaurant> getAllWithDishes(@Param("day") LocalDate day);
}