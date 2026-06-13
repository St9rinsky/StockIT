package za.co.sello.inventory.model;

import java.util.UUID;

public class Product {
    private final UUID id;
    private String SKU;
    private String name;
    private Category category;
    private boolean available;
    private int reorderLevel;

    //existing product
    public Product(UUID id,
                   String SKU,
                   String name,
                   Category category,
                   boolean available,
                   int reorderLevel) {
        this.id = id;
        this.SKU = SKU;
        this.name = name;
        this.category = category;
        this.available = available;
        this.reorderLevel = reorderLevel;
    }

    //new product
    public Product(String sku,
                   String name,
                   Category category) {
        this(UUID.randomUUID(), sku, name, category, false, 0);
    }

    //getters
    public UUID getId() {return id;}
    public String getSKU() {return SKU;}
    public String getName() {return name;}
    public Category getCategory() {return category;}
    public boolean isAvailable() {return available;}
    public int getReorderLevel() {return reorderLevel;}

    //setters
    public void rename(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be blank");
        this.name = name;
    }

    public void changeSKU(String sku){
        if (sku == null || sku.isBlank()) throw new IllegalArgumentException("SKU cannot be blank");
        this.SKU = sku;
    }

    public void changeCategory(Category category){
        if(category == null) throw new IllegalArgumentException("not a valid category");
        this.category = category;
    }

    public void makeAvailable(){this.available = true;}
    public void makeUnAvailable(){this.available = false;}

    public void setReorderLevel(int level){
        if(level < 0) throw new IllegalArgumentException("not valid level");
        this.reorderLevel = level;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", SKU='" + SKU + '\'' +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", available=" + available +
                ", reorderLevel=" + reorderLevel +
                '}';
    }
}

