package za.co.sello.inventory.service;

import za.co.sello.inventory.model.*;
import za.co.sello.inventory.repository.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventoryServices {

    private final ProductRepository productRepository;
    private final StockMovementRepository stockMovementRepository;

    public InventoryServices(ProductRepository productRepository,
                            StockMovementRepository stockMovementRepository) {
        this.productRepository = productRepository;
        this.stockMovementRepository = stockMovementRepository;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public StockMovement recordStockIn(Product product,
                                       Location location,
                                       StockMovementReason reason,
                                       int quantity,
                                       String createdBy) {

        StockMovement movement = new StockMovement(
                product,
                location,
                StockMovementType.IN,
                reason,
                quantity,
                createdBy
        );

        stockMovementRepository.save(movement);
        return movement;
    }

    public StockMovement recordStockOut(Product product,
                                        Location location,
                                        StockMovementReason reason,
                                        int quantity,
                                        String createdBy) {

        int currentStock = getCurrentStock(product.getId(), location.getId());

        if (quantity > currentStock) {
            throw new IllegalArgumentException("Cannot remove more stock than available.");
        }

        StockMovement movement = new StockMovement(
                product,
                location,
                StockMovementType.OUT,
                reason,
                quantity,
                createdBy
        );

        stockMovementRepository.save(movement);
        return movement;
    }

    public int getCurrentStock(UUID productId, UUID locationId) {
        int total = 0;

        for (StockMovement movement : stockMovementRepository.findAll()) {
            boolean sameProduct = movement.getProduct().getId().equals(productId);
            boolean sameLocation = movement.getLocation().getId().equals(locationId);

            if (sameProduct && sameLocation) {
                if (movement.getMovementType() == StockMovementType.IN) {
                    total += movement.getQuantity();
                } else if (movement.getMovementType() == StockMovementType.OUT) {
                    total -= movement.getQuantity();
                }
            }
        }

        return total;
    }

    public List<StockMovement> getMovementHistory(Product product) {
        List<StockMovement> history = new ArrayList<>();

        for (StockMovement movement : stockMovementRepository.findAll()) {
            if (movement.getProduct().getId().equals(product.getId())) {
                history.add(movement);
            }
        }

        return history;
    }
}