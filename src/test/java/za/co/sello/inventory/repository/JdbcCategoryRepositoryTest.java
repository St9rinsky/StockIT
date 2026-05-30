package za.co.sello.inventory.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import za.co.sello.inventory.config.DatabaseConnectionFactory;
import za.co.sello.inventory.model.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JdbcCategoryRepositoryTest {
    private List<UUID> categoryIdsToDelete;
    private JdbcCategoryRepository categoryRepository;

    @BeforeEach
    void setUp() throws SQLException {
        categoryIdsToDelete = new ArrayList<>();
        DatabaseConnectionFactory connectionFactory = new DatabaseConnectionFactory();
        categoryRepository = new JdbcCategoryRepository(connectionFactory);
    }

    @AfterEach
    void tearDown() {
        for (UUID id : categoryIdsToDelete) {
            categoryRepository.removeByID(id);
        }
        categoryIdsToDelete.clear();
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        Category category = new Category("Test_Category", id);

        categoryRepository.save(category);
        categoryIdsToDelete.add(category.getId());

        Optional<Category> foundCategory = categoryRepository.findById(id);
        assertEquals(id, foundCategory.get().getId());
        assertEquals(category, foundCategory.get());
    }

    @Test
    void returnsEmptyWhenCategoryDoesNotExist() {
        Optional<Category> found = categoryRepository.findById(UUID.randomUUID());
        assertTrue(found.isEmpty());
    }

    @Test
    void removesCategoryByID(){
        UUID id = UUID.randomUUID();
        Category category = new Category("Test_Category", id);

        categoryRepository.save(category);
        categoryIdsToDelete.add(category.getId());

        Optional<Category> foundBefore = categoryRepository.findById(id);
        assertTrue(foundBefore.isPresent());

        categoryRepository.removeByID(id);

        Optional<Category> foundAfter = categoryRepository.findById(id);
        assertTrue(foundAfter.isEmpty());
        categoryIdsToDelete.remove(category.getId());
    }

    @Test
    void savesCategory() throws SQLException {
        Category category = new Category("Test_Category");

        categoryRepository.save(category);
        categoryIdsToDelete.add(category.getId());

        Optional<Category> found = categoryRepository.findById(category.getId());

        assertTrue(found.isPresent());
        assertEquals(category.getId(), found.get().getId());
        assertEquals(category.getName(), found.get().getName());
        assertEquals(category, found.get());
    }

}
