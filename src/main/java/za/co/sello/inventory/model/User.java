package za.co.sello.inventory.model;

import java.util.UUID;

public class User {
    private final UUID id;
    private String name;
    private final String companyId;
    private Role role;

    //current user
    public User(UUID id, String name,String companyId, Role role) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
        this.role = role;
    }

    //new users
    public User(String name,String companyId, Role role){this(UUID.randomUUID(), name,companyId,role);}

    //getters
    public UUID getId() {return id;}
    public String getName() {return name;}
    public String getCompanyId() {return companyId;}
    public Role getRole() {return role;}

    //setters
    public void updateRole(Role role){
        this.role = role;
    }
    public void updateName(String name){this.name = name; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", companyId='" + companyId + '\'' +
                ", role=" + role +
                '}';
    }
}
