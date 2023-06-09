package com.restaurant_voting.web.vote;

import com.restaurant_voting.model.Vote;
import com.restaurant_voting.repository.VoteRepository;
import com.restaurant_voting.util.TimeUtil;
import com.restaurant_voting.web.AbstractControllerTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;

import static com.restaurant_voting.web.dish.DishTestData.TODAY;
import static com.restaurant_voting.web.restaurant.RestaurantTestData.RESTAURANT_ID;
import static com.restaurant_voting.web.user.UserTestData.USER_ID;
import static com.restaurant_voting.web.user.UserTestData.USER_MAIL_1;
import static com.restaurant_voting.web.user.UserTestData.USER_MAIL_2;
import static com.restaurant_voting.web.vote.UserVoteController.REST_URL;
import static com.restaurant_voting.web.vote.VoteTestData.VOTE_ID;
import static com.restaurant_voting.web.vote.VoteTestData.VOTE_MATCHER;
import static com.restaurant_voting.web.vote.VoteTestData.getNew;
import static com.restaurant_voting.web.vote.VoteTestData.getUpdated;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserVoteControllerTest extends AbstractControllerTest {
    @Autowired
    private VoteRepository voteRepository;

    @AfterAll
    public static void returnEndVoteTime() {
        setValidVoteTime();
    }

    @Test
    @WithUserDetails(value = USER_MAIL_1)
    void delete() throws Exception {
        setValidVoteTime();
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + VOTE_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(voteRepository.get(VOTE_ID, USER_ID, TODAY).isPresent());
    }

    @Test
    @WithUserDetails(value = USER_MAIL_1)
    void deleteNotValidTime() throws Exception {
        setInvalidVoteTime();
        perform(MockMvcRequestBuilders.delete(REST_URL + "/" + VOTE_ID))
                .andDo(print())
                .andExpect(status().isConflict());
        assertTrue(voteRepository.get(VOTE_ID, USER_ID, TODAY).isPresent());
    }

    @Test
    @WithUserDetails(value = USER_MAIL_1)
    void update() throws Exception {
        setValidVoteTime();
        Vote updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT_ID + 2)))
                .andDo(print())
                .andExpect(status().isNoContent());
        VOTE_MATCHER.assertMatch(voteRepository.getExisted(VOTE_ID), updated);
    }

    @Test
    @WithUserDetails(value = USER_MAIL_1)
    void updateNotValidTime() throws Exception {
        setInvalidVoteTime();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT_ID)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = USER_MAIL_2)
    void create() throws Exception {
        setValidVoteTime();
        Vote newVote = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT_ID + 2)))
                .andDo(print())
                .andExpect(status().isCreated());

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteRepository.getExisted(newId), newVote);
    }

    private static void setValidVoteTime() {
        TimeUtil.setEndVoteTimeForTests(LocalTime.of(23, 59));
    }

    private static void setInvalidVoteTime() {
        TimeUtil.setEndVoteTimeForTests(LocalTime.of(0, 0));
    }
}