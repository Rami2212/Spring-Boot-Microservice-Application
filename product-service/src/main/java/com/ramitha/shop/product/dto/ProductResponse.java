package com.ramitha.shop.product.dto;

public record ProductResponse (
        String id,
        String name,
        String description,
        double price
) {
}