package com.restaurant_voting.util;

import com.restaurant_voting.HasId;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new RuntimeException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new RuntimeException(bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new RuntimeException("Not found entity with " + msg);
        }
    }
}