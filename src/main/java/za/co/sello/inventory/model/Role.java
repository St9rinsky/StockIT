package za.co.sello.inventory.model;

public enum Role {

    ADMIN, //can change roles
    STOCK_MANAGER, //manages stock, adds and updates stuff
    POS //point of sale can only remove products
}
