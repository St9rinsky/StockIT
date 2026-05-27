package za.co.sello.inventory.model;

import java.util.UUID;

public class Product {
    private final UUID id;
    private String name;
    private String sku;
    private Category category;
    private boolean active;

    public Product(String name, String sku, Category category) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.sku = sku;
        this.category = category;
        this.active = true;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isActive() {
        return active;
    }

    public void rename(String name) {
        this.name = name;
    }

    public void changeSku(String sku) {
        this.sku = sku;
    }

    public void changeCategory(Category category) {
        this.category = category;
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
                ", active=" + active +
                '}';
    }
}