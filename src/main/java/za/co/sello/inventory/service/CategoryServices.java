package za.co.sello.inventory.service;

import za.co.sello.inventory.model.Category;
import za.co.sello.inventory.model.Role;
import za.co.sello.inventory.model.User;
import za.co.sello.inventory.repository.CategoryRepository;
import za.co.sello.inventory.repository.ProductRepository;

import java.util.List;

public class CategoryServices {
    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;

    public CategoryServices(CategoryRepository categoryRepo,
                            ProductRepository productRepo){
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    //add new
    public void addCategory(User currentUser, Category category){
        requiredAccess(currentUser);
        if (categoryRepo.findByName(category.getName()).isPresent()) {
            throw new IllegalArgumentException("Category already exists");
        }
        categoryRepo.add(category);

    }

    //change name
    public void renameCategory(User currentUser, String oldName, String newName) {
        requiredAccess(currentUser);
        Category category = categoryRepo.findByName(oldName)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        if (categoryRepo.findByName(newName).isPresent()) {
            throw new IllegalArgumentException("Category name already exists");
        }

        category.rename(newName);
        categoryRepo.update(category);
    }

    //delete
    public void deleteCategory(User currentUser, String categoryName) {
        requiredAccess(currentUser);
        Category category = categoryRepo.findByName(categoryName)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        if (!productRepo.findByCategoryId(category.getId()).isEmpty()) {
            throw new IllegalArgumentException("Category contains products");
        }
        categoryRepo.deleteById(category.getId());
    }

    //get all
    public List<Category> getAllCategories() {
        return categoryRepo.getAll();
    }

    //validators
    private void requiredAccess(User currentUser){
        if (currentUser.getRole() != Role.ADMIN || currentUser.getRole() != Role.STOCK_MANAGER){
            throw new IllegalArgumentException("Unauthorised access");
        }
    }
}
