package za.co.sello.inventory.repository.InMemory;

import za.co.sello.inventory.model.Category;
import za.co.sello.inventory.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class IMCategoryRepo implements CategoryRepository {
    private final List<Category> categories = new ArrayList<>();

    @Override
    public void add(Category category) {categories.add(category);}

    @Override
    public List<Category> getAll() {return categories;}

    @Override
    public Optional<Category> findById(UUID id) {
        for (Category category : categories){
            if(category.getId().equals(id)) return Optional.of(category);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Category> findByName(String name) {
        for (Category category : categories){
            if(category.getName().equals(name)) return Optional.of(category);
        }
        return Optional.empty();
    }

    @Override
    public void update(Category category) {
        for(int i = 0; i < categories.size(); i++) {
            Category current = categories.get(i);

            if (current.getId().equals(category.getId())) {
                categories.set(i, category);
                return;
            }
        }
    }

    @Override
    public void deleteById(UUID id) {
        Category category = this.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        categories.remove(category);

    }
}
