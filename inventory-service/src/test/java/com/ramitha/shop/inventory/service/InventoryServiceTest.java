package com.ramitha.shop.inventory.service;

import com.ramitha.shop.inventory.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    public InventoryServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isInStock_shouldReturnTrueWhenRepositoryReturnsTrue() {
        when(inventoryRepository.existsBySkuCodeAndQuantityGreaterThanEqual("ABC123", 5))
                .thenReturn(true);

        boolean result = inventoryService.isInStock("ABC123", 5);

        assertThat(result).isTrue();
    }

    @Test
    void isInStock_shouldReturnFalseWhenRepositoryReturnsFalse() {
        when(inventoryRepository.existsBySkuCodeAndQuantityGreaterThanEqual("XYZ789", 10))
                .thenReturn(false);

        boolean result = inventoryService.isInStock("XYZ789", 10);

        assertThat(result).isFalse();
    }
}