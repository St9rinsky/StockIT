package za.co.sello.inventory.repository;


import za.co.sello.inventory.model.Role;
import za.co.sello.inventory.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public void add(User user) {
        users.add(user);
    }

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(UUID id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public List<User> findByRole(Role role){
        return users.stream()
                .filter(user -> user.getRole().equals(role))
                .toList();
    }
}
