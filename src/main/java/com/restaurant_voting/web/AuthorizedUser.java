package com.restaurant_voting.web;

import com.restaurant_voting.model.User;
import com.restaurant_voting.to.UserTo;
import com.restaurant_voting.util.UserUtil;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.io.Serial;

@Getter
@ToString(of = "userTo")
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    @Serial
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthorizedUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRoles());
        setTo(UserUtil.asTo(user));
    }

    public int getId() {
        return userTo.getId();
    }

    public void setTo(UserTo newTo) {
        newTo.setPassword(null);
        userTo = newTo;
    }
}