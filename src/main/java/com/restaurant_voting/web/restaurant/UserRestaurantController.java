package com.restaurant_voting.web.restaurant;

import com.restaurant_voting.to.RestaurantTo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController extends AbstractRestaurantController {

    @Override
    @GetMapping
    public List<RestaurantTo> getAllWithDishesAndVotes() {
        return super.getAllWithDishesAndVotes();
    }
}
