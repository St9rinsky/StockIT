package za.co.sello.inventory.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class StockMovement {
    private final UUID id;
    private final Product product;
    private final Location location;
    private final StockMovementType movementType;
    private final StockMovementReason movementReason;
    private final int quantity;
    private final User createdBy;
    private final LocalDateTime createdAt;

    public StockMovement(Product product,
                         Location location,
                         StockMovementType movementType,
                         StockMovementReason movementReason,
                         int quantity,
                         User createdBy) {

        this.id = UUID.randomUUID();

        this.product = product;
        this.location = location;

        this.movementType = movementType;
        this.movementReason = movementReason;

        this.quantity = quantity;

        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
    }

    //getters
    public UUID getId() {return id;}
    public Product getProduct() {return product;}
    public Location getLocation() {return location;}
    public StockMovementType getMovementType() {return movementType;}
    public StockMovementReason getMovementReason() {return movementReason;}
    public int getQuantity() {return quantity;}
    public User getCreatedBy() {return createdBy;}
    public LocalDateTime getCreatedAt() {return createdAt;}

    @Override
    public String toString() {
        return "StockMovement{" +
                "id=" + id +
                ", product=" + product +
                ", location=" + location +
                ", movementType=" + movementType +
                ", movementReason=" + movementReason +
                ", quantity=" + quantity +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
