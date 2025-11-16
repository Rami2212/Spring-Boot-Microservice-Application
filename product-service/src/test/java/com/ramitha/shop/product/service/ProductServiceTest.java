package com.ramitha.shop.product.service;

import com.ramitha.shop.product.dto.ProductRequest;
import com.ramitha.shop.product.model.Product;
import com.ramitha.shop.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest {

    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private final ProductService productService = new ProductService(productRepository);

    @Test
    void shouldCreateProductAndSaveToRepository() {
        ProductRequest request = new ProductRequest(null, "Phone", "Smartphone", 499.99);

        Product product = productService.createProduct(request);

        assertThat(product.getName()).isEqualTo("Phone");
        assertThat(product.getDescription()).isEqualTo("Smartphone");
        assertThat(product.getPrice()).isEqualTo(499.99);

        Mockito.verify(productRepository).save(product);
    }
}