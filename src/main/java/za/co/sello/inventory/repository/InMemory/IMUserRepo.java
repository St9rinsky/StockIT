package za.co.sello.inventory.repository.InMemory;

import za.co.sello.inventory.model.Role;
import za.co.sello.inventory.model.User;
import za.co.sello.inventory.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class IMUserRepo implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {users.add(user);}

    @Override
    public List<User> getAll() {return users;}

    @Override
    public Optional<User> getByID(UUID id) {
        for (User user : users){
            if(user.getId().equals(id)) return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByCompanyId(String id) {
        for (User user : users){
            if(user.getCompanyId().equals(id)) return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getByRole(Role role) {
        List<User> selected = new ArrayList<>();
        for (User user : users){
            if(user.getRole().equals(role)) selected.add(user);
        }
        return selected;
    }

    @Override
    public void update(User updatedUser) {
        for(int i = 0; i < users.size(); i++) {
            User current = users.get(i);

            if (current.getId().equals(updatedUser.getId())) {
                users.set(i, updatedUser);
                return;
            }
        }
    }

    @Override
    public void removeById(UUID id) {
        User user = this.getByID(id).orElseThrow(() -> new RuntimeException("User not found"));
        users.remove(user);


    }
}
