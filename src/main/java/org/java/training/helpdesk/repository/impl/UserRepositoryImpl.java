package org.java.training.helpdesk.repository.impl;

import org.java.training.helpdesk.entity.User;
import org.java.training.helpdesk.entity.enums.Role;
import org.java.training.helpdesk.entity.enums.State;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserRepositoryImpl{
    private static final Map<String, User> database = new HashMap<>();
    private static final User admin = new User("Alina", "Shostak",
            Role.ADMIN, "AlinaShostak@.com", "Godel", State.UNBLOCKED);

    public UserRepositoryImpl() {
        database.put(admin.getEmail(), admin);
    }

    public Optional<User> findUserByEmail(String email) {
        System.out.println(email);
        return Optional.ofNullable(database.get(email));
    }

    public Collection<User> findAll() {
        return database.values();
    }

    public User save(User user) {
        database.put(user.getEmail(), user);
        return database.get(user.getEmail());
    }
}
