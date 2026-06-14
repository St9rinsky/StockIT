package za.co.sello.inventory.repository.InMemory;

import za.co.sello.inventory.model.Location;
import za.co.sello.inventory.model.Product;
import za.co.sello.inventory.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class IMProductRepo implements ProductRepository {
    private final List<Product> products = new ArrayList<>();

    @Override
    public void add(Product product) {products.add(product);}

    @Override
    public List<Product> getAll() {return products;}

    @Override
    public Optional<Product> findById(UUID id) {
        for(Product product: products){
            if(product.getId().equals(id)) return Optional.of(product);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        for(Product product: products){
            if(product.getSKU().equals(sku)) return Optional.of(product);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findByCategoryId(UUID categoryId) {
        List<Product> selected = new ArrayList<>();
        for(Product product: products){
            if(product.getCategory().getId().equals(categoryId)) selected.add(product);
        }
        return selected;
    }

    @Override
    public List<Product> findByLocationId(UUID locationId) {
        List<Product> selected = new ArrayList<>();
        for(Product product: products){
            if(product.getLocation().getId().equals(locationId)) selected.add(product);
        }
        return selected;
    }

    @Override
    public void update(Product product) {
        for(int i = 0; i < products.size(); i++) {
            Product current = products.get(i);

            if (current.getId().equals(product.getId())) {
                products.set(i, product);
                return;
            }
        }
    }

    @Override
    public void deleteById(UUID id) {
    Product product = this.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    products.remove(product);
    }
}
