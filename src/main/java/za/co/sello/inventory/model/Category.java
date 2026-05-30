package za.co.sello.inventory.model;

import java.util.Objects;
import java.util.UUID;

public class Category {

    private final UUID id;
    private String name;

    public Category(String name, UUID id) {
        this.id = id;
        this.name = name;
    }
    public Category(String name) {
        this(name, UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void rename(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return Objects.equals(id, category.id)
                && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return name;
    }

}
