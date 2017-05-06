package ua.nure.util;

import org.springframework.stereotype.Component;
import ua.nure.model.User;

import java.util.HashMap;
import java.util.Map;

@Component
public class Context {

    private Map<String, Object> context;

    public Context() {
        context = new HashMap<>();
    }

    public void setUser(User user) {
        context.put("user", user);
    }

    public User getUser() {
        return (User) context.get("user");
    }
}
