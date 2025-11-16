package com.ramitha.shop.product.dto;

public record ProductRequest (
        String id,
        String name,
        String description,
        double price
) {
}