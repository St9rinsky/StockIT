package za.co.sello.inventory.model;

import java.util.Objects;
import java.util.UUID;

public class Location {
    private final UUID id;
    private String name;

    //existing Location
    public Location(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    //new location
    public Location(String name){this(UUID.randomUUID(), name);}

    //getters
    public UUID getId() {return id;}
    public String getName() {return name;}

    //setters
    public void rename(String name) {
        if(name == null || name.isBlank()) throw new IllegalArgumentException("Location name cannot be blank");
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Location location)) return false;
        return Objects.equals(id, location.id) && Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
