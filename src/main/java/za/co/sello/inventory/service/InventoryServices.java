package za.co.sello.inventory.service;

import za.co.sello.inventory.exception.*;
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
        validateProduct(product);
        if (productRepository.existsBySku(product.getSku())) {
            throw new DuplicateProductException(
                    "Product with SKU already exists: " + product.getSku()
            );
        }
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
        validateQuantity(quantity);
        validateCreator(createdBy);
        validateProduct(product);
        validateLocation(location);
        validateReason(reason);
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
        validateQuantity(quantity);
        validateCreator(createdBy);
        validateProduct(product);
        validateLocation(location);
        validateReason(reason);
        int currentStock = getCurrentStock(product.getId(), location.getId());

        if (quantity > currentStock) {
            throw new InsufficientStockException("Cannot remove more stock than available.");
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


    //Validations
    private void validateQuantity(int quantity) {
        if (quantity <= 0) {throw new InvalidStockMovementException("Quantity must be greater than zero.");}
    }

    private void validateCreator(String name) {
        if (name == null || name.isBlank()) {throw new InvalidStockMovementException("Creator name cannot be null.");}
    }

    private void validateProduct(Product product) {
        if (product == null) {throw new InvalidStockMovementException("Product cannot be null.");}

        if (product.getName() == null || product.getName().isBlank()) {
            throw new InvalidStockMovementException("Product name cannot be blank.");
        }

        if (product.getSku() == null || product.getSku().isBlank()) {
            throw new InvalidStockMovementException("Product SKU cannot be blank.");
        }
    }

    private void validateLocation(Location location) {
        if (location == null) {throw new InvalidStockMovementException("Location cannot be null.");}
    }

    private void validateReason(StockMovementReason reason ) {
        if (reason == null) {throw new InvalidStockMovementException("Stock movement reason cannot be null.");}
    }
}