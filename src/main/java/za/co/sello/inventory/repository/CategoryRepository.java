package za.co.sello.inventory.repository;


import za.co.sello.inventory.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {

    //adding categories
    void add(Category category);

    //Reading data
    List<Category> getAll();
    Optional<Category> findById(UUID id);
    Optional<Category> findByName(String name);

    // Update
    void update(Category category);

    // Delete
    void deleteById(UUID id);
}
