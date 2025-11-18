package com.ramitha.shop.inventory.repository;

import com.ramitha.shop.inventory.model.Inventory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class InventoryRepositoryTest {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    void existsBySkuCodeAndQuantityGreaterThanEqual_shouldReturnTrueWhenEnoughStock() {
        Inventory inventory = Inventory.builder()
                .skuCode("ABC123")
                .quantity(10)
                .build();

        inventoryRepository.save(inventory);

        boolean result = inventoryRepository.existsBySkuCodeAndQuantityGreaterThanEqual("ABC123", 5);

        assertThat(result).isTrue();

        inventoryRepository.delete(inventory);
    }

    @Test
    void existsBySkuCodeAndQuantityGreaterThanEqual_shouldReturnFalseWhenNotEnoughStock() {
        Inventory inventory = Inventory.builder()
                .skuCode("XYZ789")
                .quantity(3)
                .build();

        inventoryRepository.save(inventory);

        boolean result = inventoryRepository.existsBySkuCodeAndQuantityGreaterThanEqual("XYZ789", 5);

        assertThat(result).isFalse();

        inventoryRepository.delete(inventory);
    }
}