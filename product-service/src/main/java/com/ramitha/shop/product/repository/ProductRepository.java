package com.ramitha.shop.product.repository;

import com.ramitha.shop.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
