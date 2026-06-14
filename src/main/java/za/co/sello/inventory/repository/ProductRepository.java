package za.co.sello.inventory.repository;

import za.co.sello.inventory.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    // adding products
    void add(Product product);

    // Read
    List<Product> getAll();
    Optional<Product> findById(UUID id);
    Optional<Product> findBySku(String sku);
    List<Product> findByCategoryId(UUID categoryId);

    // Update
    void update(Product product);

    // Delete
    void deleteById(UUID id);
}
