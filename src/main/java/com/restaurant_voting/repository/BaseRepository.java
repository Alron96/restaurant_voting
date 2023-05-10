package com.restaurant_voting.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.id=:id")
    int delete(int id);

    default void deleteExisted(int id) {
        if (delete(id) == 0) {
            throw new RuntimeException("Entity with id=" + id + " not found");
        }
    }

    default T getExisted(int id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Entity with id=" + id + " not found"));
    }
}