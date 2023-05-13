package com.restaurant_voting.web.vote;

import com.restaurant_voting.model.Vote;
import com.restaurant_voting.repository.VoteRepository;
import com.restaurant_voting.util.JsonUtil;
import com.restaurant_voting.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.restaurant_voting.web.user.UserTestData.USER_ID;
import static com.restaurant_voting.web.user.UserTestData.USER_MAIL_1;
import static com.restaurant_voting.web.user.UserTestData.USER_MAIL_2;
import static com.restaurant_voting.web.vote.UserVoteController.REST_URL;
import static com.restaurant_voting.web.vote.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserVoteControllerTest extends AbstractControllerTest {
    @Autowired
    private VoteRepository voteRepository;

    @Test
    @WithUserDetails(value = USER_MAIL_1)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL)
                .param("id", String.valueOf(VOTE_ID))
                .param("voteTime", String.valueOf(VALID_VOTE_TIME)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(voteRepository.get(VOTE_ID, USER_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = USER_MAIL_1)
    void deleteNotValidTime() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL)
                .param("id", String.valueOf(VOTE_ID))
                .param("voteTime", String.valueOf(INVALID_VOTE_TIME)))
                .andDo(print())
                .andExpect(status().isConflict());
        assertTrue(voteRepository.get(VOTE_ID, USER_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = USER_MAIL_1)
    void update() throws Exception {
        Vote updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .param("voteTime", String.valueOf(VALID_VOTE_TIME))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        VOTE_MATCHER.assertMatch(voteRepository.getExisted(VOTE_ID), updated);
    }

    @Test
    @WithUserDetails(value = USER_MAIL_1)
    void updateNotValidTime() throws Exception {
        Vote updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .param("voteTime", String.valueOf(INVALID_VOTE_TIME))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = USER_MAIL_2)
    void create() throws Exception {
        Vote newVote = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("voteTime", String.valueOf(VALID_VOTE_TIME))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andDo(print())
                .andExpect(status().isCreated());

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteRepository.getExisted(newId), newVote);
    }

    @Test
    @WithUserDetails(value = USER_MAIL_2)
    void createNotValidTime() throws Exception {
        Vote newVote = getNew();
        perform(MockMvcRequestBuilders.post(REST_URL)
                .param("voteTime", String.valueOf(INVALID_VOTE_TIME))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}