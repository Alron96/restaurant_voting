package com.restaurant_voting.web;

import com.restaurant_voting.model.User;
import lombok.Getter;
import org.springframework.lang.NonNull;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    @Getter
    private final User user;

    public AuthorizedUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int getId() {
        return user.getId();
    }

    @Override
    public String toString() {
        return "AuthorizedUser(id=" + user.getId() + " ,email=" + user.getEmail() + ")";
    }
}