package za.co.sello.inventory.model;

import java.util.UUID;

public class Product {
    private final UUID id;
    private String name;
    private String sku;
    private Category category;
    private int lowStockThreshold;
    private boolean active;

    public Product(UUID id,String name, String sku, Category category,Boolean active,int lowStockThreshold) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.category = category;
        this.lowStockThreshold = lowStockThreshold;
        this.active = active;
    }
    public Product(String name, String sku, Category category) {
        this(UUID.randomUUID(), name, sku, category,true,10);
    }

    // getters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public int getLowStockThreshold(){
        return lowStockThreshold;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isActive() {
        return active;
    }

    //state edits
    public void rename(String name) {
        this.name = name;
    }

    public void changeSku(String sku) {
        this.sku = sku;
    }

    public void changeCategory(Category category) {
        this.category = category;
    }

    public void setLowStockThreshold(int lowStockThreshold){
        this.lowStockThreshold = lowStockThreshold;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", category=" + category +
                "threshold=" + lowStockThreshold +
                ", active=" + active +
                '}';
    }
}