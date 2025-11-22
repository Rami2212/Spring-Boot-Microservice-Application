package com.ramitha.shop.product.dto;

public record ProductRequest (
        String id,
        String name,
        String skuCode,
        String description,
        double price
) {
}