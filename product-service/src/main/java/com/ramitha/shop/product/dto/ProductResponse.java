package com.ramitha.shop.product.dto;

public record ProductResponse (
        String id,
        String name,
        String skuCode,
        String description,
        double price
) {
}