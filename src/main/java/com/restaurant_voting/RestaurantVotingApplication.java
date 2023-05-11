package com.restaurant_voting;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantVotingApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantVotingApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {

    }
}
