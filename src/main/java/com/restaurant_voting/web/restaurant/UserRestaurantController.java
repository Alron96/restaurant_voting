package com.restaurant_voting.web.restaurant;

import com.restaurant_voting.service.RestaurantService;
import com.restaurant_voting.to.RestaurantTo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class UserRestaurantController {
    static final String REST_URL = "/api/restaurants";

    private final RestaurantService service;

    @GetMapping
    @Transactional(readOnly = true)
    public List<RestaurantTo> getAllWithDishesAndVotesToday() {
        return service.getAllWithDishesAndVotesToday();
    }
}
