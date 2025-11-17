package com.ramitha.shop.product.service;

import com.ramitha.shop.product.dto.ProductRequest;
import com.ramitha.shop.product.model.Product;
import com.ramitha.shop.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest {

    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private final ProductService productService = new ProductService(productRepository);

    @Test
    void shouldCreateProductAndSaveToRepository() {
        ProductRequest request = new ProductRequest(null, "Test Product", "This is a test product", 499.99);

        Product product = productService.createProduct(request);

        assertThat(product.getName()).isEqualTo("Test Product");
        assertThat(product.getDescription()).isEqualTo("This is a test product");
        assertThat(product.getPrice()).isEqualTo(499.99);

        Mockito.verify(productRepository).save(product);
    }

    @Test
    void shouldGetAllProducts() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of(
                new Product("1", "Product 1", "Description 1", 100.0),
                new Product("2", "Product 2", "Description 2", 200.0)
        ));

        var products = productService.getAllProducts();

        assertThat(products).hasSize(2);
        assertThat(products.get(0).name()).isEqualTo("Product 1");
        assertThat(products.get(1).name()).isEqualTo("Product 2");
    }

    @Test
    void shouldGetProductById() {
        String productId = "1";
        Mockito.when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(
                new Product("1", "Product 1", "Description 1", 100.0)
        ));
        var product = productService.getProductById(productId);
        assertThat(product.name()).isEqualTo("Product 1");
    }

    @Test
    void shouldUpdateProductById() {
        String productId = "1";
        ProductRequest updateRequest = new ProductRequest(null, "Updated Product", "Updated Description", 150.0);
        Mockito.when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(
                new Product("1", "Product 1", "Description 1", 100.0)
        ));

        var updatedProduct = productService.updateProductById(productId, updateRequest);

        assertThat(updatedProduct.name()).isEqualTo("Updated Product");
        assertThat(updatedProduct.description()).isEqualTo("Updated Description");
        assertThat(updatedProduct.price()).isEqualTo(150.0);
    }

    @Test
    void shouldDeleteProductById() {
        String productId = "1";
        productService.deleteProductById(productId);
        Mockito.verify(productRepository).deleteById(productId);
    }

}