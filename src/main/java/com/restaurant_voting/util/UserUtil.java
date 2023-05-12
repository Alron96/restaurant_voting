package com.restaurant_voting.util;

import com.restaurant_voting.model.Role;
import com.restaurant_voting.model.User;
import com.restaurant_voting.to.UserTo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.id(), user.getName(), user.getEmail(), user.getPassword());
    }
}