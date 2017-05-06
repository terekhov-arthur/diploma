package ua.nure.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.model.User;
import ua.nure.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class Context {

    private Map<String, Object> context;
    private final UserRepository userRepository;

    @Autowired
    public Context(UserRepository userRepository) {
        context = new HashMap<>();

        //todo: remove default user
        this.userRepository = userRepository;
        User user = userRepository.findOne(1L);
        setUser(user);
    }

    public void setUser(User user) {
        context.put("user", user);
    }

    public User getUser() {
        return (User) context.get("user");
    }
}
