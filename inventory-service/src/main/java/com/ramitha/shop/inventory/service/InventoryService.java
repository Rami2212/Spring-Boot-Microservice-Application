package com.ramitha.shop.inventory.service;

import com.ramitha.shop.inventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryService {
    
    private final InventoryRepository inventoryRepository;
    
    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }
}
