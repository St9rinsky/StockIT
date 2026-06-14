package za.co.sello.inventory.repository;

import za.co.sello.inventory.model.StockMovement;
import za.co.sello.inventory.model.StockMovementType;
import za.co.sello.inventory.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StockMovementRepository {

    // Create
    void record(StockMovement movement);

    // Read
    List<StockMovement> getAll();
    List<StockMovement> findByProductId(UUID productId);
    List<StockMovement> findByType(StockMovementType type);
    List<StockMovement> findByUser(User use);
    List<StockMovement> findByDateRange(LocalDateTime from, LocalDateTime to);
    Optional<StockMovement> findById(UUID id);
}
