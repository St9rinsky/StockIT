package za.co.sello.inventory.repository.InMemory;

import za.co.sello.inventory.model.StockMovement;
import za.co.sello.inventory.model.StockMovementType;
import za.co.sello.inventory.model.User;
import za.co.sello.inventory.repository.StockMovementRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class IMStockMovementRepo implements StockMovementRepository {
    private final List<StockMovement> stockMovementList = new ArrayList<>();

    @Override
    public void record(StockMovement movement) { stockMovementList.add(movement);}

    @Override
    public List<StockMovement> getAll() {return stockMovementList;}

    @Override
    public List<StockMovement> findByProductId(UUID productId) {
        List<StockMovement> movements = new ArrayList<>();
        for(StockMovement movement: stockMovementList){
            if (movement.getProduct().getId().equals(productId)) movements.add(movement);
        }
        return movements;
    }

    @Override
    public List<StockMovement> findByType(StockMovementType type) {
        List<StockMovement> movements = new ArrayList<>();
        for(StockMovement movement: stockMovementList){
            if (movement.getMovementType().equals(type)) movements.add(movement);
        }
        return movements;
    }

    @Override
    public List<StockMovement> findByUser(User user) {
        List<StockMovement> movements = new ArrayList<>();
        for(StockMovement movement: stockMovementList){
            if (movement.getCreatedBy().equals(user)) movements.add(movement);
        }
        return movements;
    }

    @Override
    public List<StockMovement> findByDateRange(LocalDateTime from, LocalDateTime to) {
        List<StockMovement> movements = new ArrayList<>();
        for(StockMovement movement: stockMovementList){
            if(movement.getCreatedAt().isAfter(from) && movement.getCreatedAt().isBefore(to)){
                movements.add(movement);
            }
        }
        return movements;
    }

    @Override
    public Optional<StockMovement> findById(UUID id) {
        for(StockMovement movement: stockMovementList){
            if (movement.getId().equals(id)) return Optional.of(movement);
        }
        return Optional.empty();
    }
}
