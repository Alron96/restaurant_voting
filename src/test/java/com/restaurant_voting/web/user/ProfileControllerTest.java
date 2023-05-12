package com.restaurant_voting.web.user;

import com.restaurant_voting.web.AbstractControllerTest;
import com.restaurant_voting.model.User;
import com.restaurant_voting.repository.UserRepository;
import com.restaurant_voting.to.UserTo;
import com.restaurant_voting.util.JsonUtil;
import com.restaurant_voting.util.UserUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.restaurant_voting.web.user.ProfileController.REST_URL;
import static com.restaurant_voting.web.user.UniqueMailValidator.EXCEPTION_DUPLICATE_EMAIL;
import static com.restaurant_voting.web.user.UserTestData.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProfileControllerTest extends AbstractControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @WithUserDetails(value = USER_MAIL_1)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(USER_1));
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL_1)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL))
                .andDo(print())
                .andExpect(status().isNoContent());
        USER_MATCHER.assertMatch(userRepository.findAll(), ADMIN, USER_2, GUEST);
    }

    @Test
    @WithUserDetails(value = USER_MAIL_1)
    void update() throws Exception {
        UserTo updatedTo = UserUtil.asTo(getUpdated());
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(userRepository.getExisted(USER_ID), UserUtil.updateFromTo(new User(USER_1), updatedTo));
    }

    @Test
    @WithUserDetails(value = USER_MAIL_1)
    void updateInvalid() throws Exception {
        UserTo invalidTo = UserUtil.asTo(USER_1);
        invalidTo.setName(null);
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalidTo)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_MAIL_1)
    void updateDuplicateMail() throws Exception {
        UserTo invalidTo = UserUtil.asTo(USER_1);
        invalidTo.setEmail(ADMIN_MAIL);
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalidTo)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString(EXCEPTION_DUPLICATE_EMAIL)));
    }

    @Test
    void register() throws Exception {
        UserTo newTo = getNewTo();
        User newUser = UserUtil.createNewFromTo(newTo);
        ResultActions actions = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        User created = USER_MATCHER.readFromJson(actions);
        int newId = created.id();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userRepository.getExisted(newId), newUser);
    }

    @Test
    void registerInvalid() throws Exception {
        UserTo invalidTo = UserUtil.asTo(USER_1);
        invalidTo.setEmail("");
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalidTo)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void registerDuplicateMail() throws Exception {
        UserTo invalidTo = UserUtil.asTo(USER_1);
        invalidTo.setEmail(ADMIN_MAIL);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalidTo)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString(EXCEPTION_DUPLICATE_EMAIL)));
    }
}