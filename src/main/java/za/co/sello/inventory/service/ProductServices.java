package za.co.sello.inventory.service;

import za.co.sello.inventory.model.Category;
import za.co.sello.inventory.model.Product;
import za.co.sello.inventory.model.Role;
import za.co.sello.inventory.model.User;
import za.co.sello.inventory.repository.CategoryRepository;
import za.co.sello.inventory.repository.LocationRepository;
import za.co.sello.inventory.repository.ProductRepository;

public class ProductServices {
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final LocationRepository locationRepo;

    public ProductServices(
            ProductRepository productRepo,
            CategoryRepository categoryRepo,
            LocationRepository locationRepo) {

        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.locationRepo = locationRepo;
    }

    //create product
    public void createProduct(User currentUser, Product product){
        requiredAccess(currentUser);
        //unique SKU
        if (productRepo.findBySku(product.getSKU()).isPresent()) {
            throw new IllegalArgumentException("SKU already exists");
        }

        // correct category
        categoryRepo.findById(product.getCategory().getId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        // correct location
        locationRepo.findById(product.getLocation().getId())
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        productRepo.add(product);
    }

    //updating a product
    public void updateProduct(User currentUser, Product updatedProduct) {
        requiredAccess(currentUser);
        productRepo.findById(updatedProduct.getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        productRepo.update(updatedProduct);
    }

    // change category
    public void changeCategory(User currentUser, String productSKU, String categoryName) {
        requiredAccess(currentUser);
        Product product = productFound(productSKU);

        Category category = categoryRepo.findByName(categoryName)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        product.changeCategory(category);
        productRepo.update(product);
    }

    // deactivate a product
    public void deactivateProduct(User currentUser, String productSKU){
        requiredAccess(currentUser);
        Product product = productFound(productSKU);
        product.makeUnAvailable();
        productRepo.update(product);
    }

    public void activateProduct(User currentUser, String productSKU){
        requiredAccess(currentUser);
        Product product = productFound(productSKU);
        product.makeAvailable();
        productRepo.update(product);
    }

    //validators
    private void requiredAccess(User currentUser){
        if (currentUser.getRole() != Role.ADMIN || currentUser.getRole() != Role.STOCK_MANAGER){
            throw new IllegalArgumentException("Unauthorised access");
        }
    }

    private Product productFound(String productSKU){
        return productRepo.findBySku(productSKU)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

}
