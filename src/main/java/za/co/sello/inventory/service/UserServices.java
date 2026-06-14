package za.co.sello.inventory.service;

import za.co.sello.inventory.model.Role;
import za.co.sello.inventory.model.User;
import za.co.sello.inventory.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //creating a new user
    public void createUser(User currentUser, User newUser) {
        requireAdmin(currentUser);
        if(userRepository.getByCompanyId(newUser.getCompanyId()).isPresent()){
            throw new IllegalArgumentException("User already exist");
        }
        userRepository.add(newUser);
    }

    //change user role
    public void changeUserRole(User currentUser, String companyId, Role newRole){
        requireAdmin(currentUser); // validator
        Optional<User> optionalUser = userRepository.getByCompanyId(companyId);
        User user = optionalUser.orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.updateRole(newRole);
        userRepository.update(user);
    }

    //delete users
    public void removeUser(User currentUser, String companyId) {
        requireAdmin(currentUser);
        Optional<User> optionalUser = userRepository.getByCompanyId(companyId);
        User user = optionalUser.orElseThrow(() ->
                new RuntimeException("User not found"));

        userRepository.removeById(user.getId());
    }

    public List<User> getAllUsers() {return userRepository.getAll();}

    public User getUser(String companyId) {
        return userRepository.getByCompanyId(companyId)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    private void requireAdmin(User user) {
        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Admin access required.");
        }
    }

}
