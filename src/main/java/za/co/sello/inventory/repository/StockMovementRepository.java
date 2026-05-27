package za.co.sello.inventory.repository;

import za.co.sello.inventory.model.StockMovement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StockMovementRepository {
    private final List<StockMovement> movements = new ArrayList<>();

    public void save(StockMovement movement) {
        movements.add(movement);
    }

    public List<StockMovement> findAll() {
        return movements;
    }

    public List<StockMovement> findByProductId(UUID productId) {
        return movements.stream()
                .filter(movement -> movement.getProduct().getId().equals(productId))
                .toList();
    }

    public List<StockMovement> findByProductIdAndLocationId(UUID productId, UUID locationId) {
        return movements.stream()
                .filter(movement -> movement.getProduct().getId().equals(productId))
                .filter(movement -> movement.getLocation().getId().equals(locationId))
                .toList();
    }
}