package za.co.sello.inventory.model;

public enum StockMovementReason {
    INITIAL_STOCK,
    RESTOCK,

    SALE,
    DAMAGED,
    LOST,
    DONATION,

    CORRECTION,

    TRANSFER_IN,
    TRANSFER_OUT
}
