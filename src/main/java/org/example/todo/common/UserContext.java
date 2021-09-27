package org.example.todo.common;

import org.example.todo.domain.model.UserBO;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>todolist</h3>
 *
 * @author : ck
 * @date : 2021-09-26 20:43
 **/
public class UserContext {
    private static ThreadLocal<UserBO> userBOThreadLocal = new ThreadLocal<>();
    private static Map<Long, UserBO> loginUser = new HashMap<>();

    public static void set(UserBO userBO) {
        userBOThreadLocal.set(userBO);
    }

    public static UserBO get() {
        return userBOThreadLocal.get();
    }

    public static void loginUser(Long id, UserBO userBO) {
        loginUser.put(id, userBO);
    }

    public static Boolean loginOut(Long id) {
        return loginUser.remove(id)!=null;
    }

    public static UserBO getLoginUser(Long id) {
        return loginUser.get(id);
    }
}
