package za.co.sello.inventory.repository;

import za.co.sello.inventory.exception.InvalidStockMovementException;
import za.co.sello.inventory.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public void save(Product product) {
        products.add(product);
    }

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(UUID id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    public boolean existsBySku(String sku) {
        return products.stream()
                .anyMatch(product -> product.getSku().equalsIgnoreCase(sku));
    }

    //validators
}