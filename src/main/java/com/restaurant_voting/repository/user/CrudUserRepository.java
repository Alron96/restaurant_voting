package com.restaurant_voting.repository.user;

import com.restaurant_voting.model.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    Optional<User> findByEmailIgnoreCase(@Param("email") String email);
}