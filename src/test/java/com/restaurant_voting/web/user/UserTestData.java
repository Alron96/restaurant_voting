package com.restaurant_voting.web.user;

import com.restaurant_voting.to.UserTo;
import com.restaurant_voting.web.MatcherFactory;
import com.restaurant_voting.model.Role;
import com.restaurant_voting.model.User;
import com.restaurant_voting.util.JsonUtil;

import java.util.List;

public class UserTestData {

    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "password");
    public static final int ADMIN_ID = 1;
    public static final int USER_ID = 2;
    public static final int NOT_FOUND = 100;
    public static final String ADMIN_MAIL = "admin@gmail.com";
    public static final String USER_MAIL_1 = "user_1@gmail.com";
    public static final String USER_MAIL_2 = "user_2@gmail.com";
    public static final String GUEST_MAIL = "guest@gmail.com";

    public static final User ADMIN = new User(ADMIN_ID, "admin", ADMIN_MAIL, "admin_password", Role.ADMIN, Role.USER);
    public static final User USER_1 = new User(USER_ID, "user_1", USER_MAIL_1, "user_password_1", Role.USER);
    public static final User USER_2 = new User(USER_ID + 1, "user_2", USER_MAIL_2, "user_password_2", Role.USER);
    public static final User GUEST = new User(USER_ID + 2, "guest", GUEST_MAIL, "guest_password_2");
    public static final List<User> USERS = List.of(ADMIN, GUEST, USER_1, USER_2);

    public static User getNew() {
        return new User(null, "new_user", "new_user@gmail.ru", "new_user_password", Role.USER);
    }

    public static User getUpdated() {
        return new User(USER_ID, "update_user", "update_user@gmail.ru", "update_user_password", Role.USER);
    }

    public static UserTo getNewTo() {
        return new UserTo(null, "new_user", "new_user@gmail.ru", "new_user_password");
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
