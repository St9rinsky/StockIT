package za.co.sello.inventory.model;

public enum Role {

    //might need to change in the future so that pos rights can be adjusted

    ADMIN, //can change roles
    STOCK_MANAGER, //manages stock, adds and updates stuff
    POS //point of sale can only remove products
}