package za.co.sello.inventory.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.sello.inventory.exception.*;
import za.co.sello.inventory.model.*;
import za.co.sello.inventory.repository.ProductRepository;
import za.co.sello.inventory.repository.StockMovementRepository;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private InventoryServices inventoryService;
    private Product product;
    private Location location;

    @BeforeEach
    void setUp() {
        ProductRepository productRepository = new ProductRepository();
        StockMovementRepository stockMovementRepository = new StockMovementRepository();

        inventoryService = new InventoryServices(
                productRepository,
                stockMovementRepository
        );

        Category category = new Category("Fruits");
        product = new Product("Apple", "123456", category);
        location = new Location("Fruits and Veg");

        inventoryService.addProduct(product);
    }

    @Test
    void shouldRejectAddingZeroStock() {
        assertThrows(
                InvalidStockMovementException.class,
                () -> inventoryService.recordStockIn(
                        product,
                        location,
                        StockMovementReason.RESTOCK,
                        0,
                        new User("Sello",Role.ADMIN)
                )
        );
    }

    @Test
    void shouldRejectAddingNegativeStock() {
        assertThrows(
                InvalidStockMovementException.class,
                () -> inventoryService.recordStockIn(
                        product,
                        location,
                        StockMovementReason.RESTOCK,
                        -5,
                        new User("Sello",Role.ADMIN)
                )
        );
    }

    @Test
    void shouldRejectNoCreator() {
        assertThrows(
                InvalidStockMovementException.class,
                () -> inventoryService.recordStockIn(
                        product,
                        location,
                        StockMovementReason.RESTOCK,
                        10,
                        new User(" ",Role.ADMIN)
                )
        );
    }

    @Test
    void shouldRejectNullProduct() {
        assertThrows(
                InvalidStockMovementException.class,
                () -> inventoryService.recordStockIn(
                        null,
                        location,
                        StockMovementReason.RESTOCK,
                        10,
                        new User("Sello",Role.ADMIN)
                )
        );
    }

    @Test
    void shouldRejectNullLocation() {
        assertThrows(
                InvalidStockMovementException.class,
                () -> inventoryService.recordStockIn(
                        product,
                        null,
                        StockMovementReason.RESTOCK,
                        10,
                        new User("Sello",Role.ADMIN)
                )
        );
    }

    @Test
    void shouldRejectNullReason() {
        assertThrows(
                InvalidStockMovementException.class,
                () -> inventoryService.recordStockIn(
                        product,
                        location,
                        null,
                        10,
                        new User("Sello",Role.ADMIN)
                )
        );
    }

    @Test
    void shouldRejectStockOutMoreThanAvailable() {
        inventoryService.recordStockIn(
                product,
                location,
                StockMovementReason.INITIAL_STOCK,
                5,
                new User("Sello",Role.ADMIN)
        );

        assertThrows(
                InsufficientStockException.class,
                () -> inventoryService.recordStockOut(
                        product,
                        location,
                        StockMovementReason.SALE,
                        10,
                        new User("Sello",Role.ADMIN)
                )
        );
    }

    @Test
    void shouldRejectDuplicateSku() {
        Category category = new Category("Fruits");

        Product duplicate = new Product(
                "Banana",
                "123456",
                category
        );

        assertThrows(
                DuplicateProductException.class,
                () -> inventoryService.addProduct(duplicate)
        );
    }

    @Test
    void shouldFindProductBySku() {
        Product found = inventoryService.getProductBySku("123456");

        assertEquals(product.getId(), found.getId());
    }

    @Test
    void shouldThrowExceptionWhenProductSkuDoesNotExist() {
        assertThrows(
                ProductNotFoundException.class,
                () -> inventoryService.getProductBySku("nothing")
        );
    }
}