package com.restaurant_voting.web.dish;

import com.restaurant_voting.model.Dish;
import com.restaurant_voting.repository.DishRepository;
import com.restaurant_voting.service.DishService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.restaurant_voting.util.validation.ValidationUtil.assureIdConsistent;
import static com.restaurant_voting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class AdminDishController {
    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/dishes";

    private final DishRepository repository;
    private final DishService service;

    @GetMapping
    public List<Dish> getAll(@PathVariable int restaurantId) {
        log.info("getAll dishes");
        return repository.getAll(restaurantId);
    }

    @GetMapping("/filter")
    public List<Dish> getAllByDate(@PathVariable int restaurantId, @RequestParam LocalDate day) {
        log.info("getAll dishes");
        return repository.getAllByDate(restaurantId, day);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Dish> create(@Valid @RequestBody Dish dish, @PathVariable int restaurantId) {
        log.info("create {} for restaurantId={}", dish, restaurantId);
        checkNew(dish);
        Dish created = service.create(dish, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/admin/restaurants" + "/{restaurantId}" + "/dishes" + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @PathVariable int restaurantId, @PathVariable int id) {
        log.info("update {} for restaurantId={}", dish, restaurantId);
        assureIdConsistent(dish, id);
        service.update(dish, restaurantId);
    }

    @GetMapping("/{id}")
    public Optional<Dish> get(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("get dish with id={}", id);
        return repository.get(id, restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("delete dish with id={}", id);
        service.delete(id, restaurantId);
    }
}