package com.restaurant_voting.web.restaurant;

import com.restaurant_voting.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.restaurant_voting.web.restaurant.RestaurantTestData.RESTAURANTS_WITH_DISHES_AND_VOTES_TODAY;
import static com.restaurant_voting.web.restaurant.RestaurantTestData.RESTAURANT_WITH_DISHES_AND_VOTES_MATCHER;
import static com.restaurant_voting.web.restaurant.UserRestaurantController.REST_URL;
import static com.restaurant_voting.web.user.UserTestData.USER_MAIL_1;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserRestaurantControllerTest extends AbstractControllerTest {

    @Test
    @WithUserDetails(value = USER_MAIL_1)
    void getAllWithDishesAndVotes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_WITH_DISHES_AND_VOTES_MATCHER.contentJson(RESTAURANTS_WITH_DISHES_AND_VOTES_TODAY));
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }
}