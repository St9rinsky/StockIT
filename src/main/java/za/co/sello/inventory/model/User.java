package za.co.sello.inventory.model;

import java.util.UUID;

public class User {
    private final UUID id;
    private String name;
    private Role role;

    public User(String name, Role role) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.role = role;
    }

    public void updateRole(Role role){
        this.role = role;
    }
    public void updateName(String name){this.name = name; }

    public UUID getId() {return id;}
    public String getName() {return name;}
    public Role getRole() {return role;}
}
