package za.co.sello.inventory.repository.InMemory;

import za.co.sello.inventory.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private final List<User> users = new ArrayList<>();

    public void add(User user) {
        users.add(user);
    }

    //getters
    public List<User> getAll() {return users;}

    public User getByID(){

    }
}
