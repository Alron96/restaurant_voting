package com.restaurant_voting.repository;

import com.restaurant_voting.model.Dish;
import com.restaurant_voting.util.error.DataConflictException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    Optional<Dish> get(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId")
    List<Dish> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.dishDate=:day")
    List<Dish> getAllByDate(@Param("restaurantId") int restaurantId, @Param("day") LocalDate day);

    default Dish getExistedOrBelonged(int id, int restaurantId) {
        return get(id, restaurantId).orElseThrow(
                () -> new DataConflictException("Dish id=" + id + " is not exist or doesn't belong to Restaurant id=" + restaurantId));
    }
}
