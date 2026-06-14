package za.co.sello.inventory.repository;

import za.co.sello.inventory.model.Role;
import za.co.sello.inventory.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    // adding users
    void add(User user);

    // Reading data
    List<User> getAll();
    Optional<User> getByID(UUID id);
    Optional<User> getByCompanyId(String id);
    List<User> getByRole(Role role);

    //update
    void update(User user);

    //removing
    void removeById(UUID id);
}
