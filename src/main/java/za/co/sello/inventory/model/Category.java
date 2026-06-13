package za.co.sello.inventory.model;

import java.util.Objects;
import java.util.UUID;

public class Category {
    private final UUID id;
    private String name;

    //existing category
    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    //new category
    public Category(String name){this(UUID.randomUUID(), name);}

    //getters
    public UUID getId() {return id;}
    public String getName() {return name;}

    //setters
    public void rename(String name) {
        if(name == null || name.isBlank()) throw new IllegalArgumentException("category name cannot be blank");
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Category category)) return false;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
