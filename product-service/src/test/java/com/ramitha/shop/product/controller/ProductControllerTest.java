package com.ramitha.shop.product.controller;

import com.ramitha.shop.product.repository.ProductRepository;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.utility.TestcontainersConfiguration;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    private ProductRepository productRepository;

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldCreateProduct() {
        String requestBody = """
            {
                "name": "Test Product",
                "description": "This is a test product",
                "price": 99.99
            }
            """;

        String productId = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/products")
                .then()
                .statusCode(201)
                .body("name", Matchers.equalTo("Test Product"))
                .body("description", Matchers.equalTo("This is a test product"))
                .body("price", Matchers.equalTo(99.99f))
                .extract()
                .path("id");

        // Clean up directly via repository
        productRepository.deleteById(productId);
    }

    @Test
    void shouldGetAllProducts() {
        String requestBody1 = """
            {
                "name": "Test Product 1",
                "description": "This is test product 1",
                "price": 49.99
            }
            """;

        String requestBody2 = """
            {
                "name": "Test Product 2",
                "description": "This is test product 2",
                "price": 79.99
            }
            """;

        String productId1 = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody1)
                .when()
                .post("/api/products")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        String productId2 = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody2)
                .when()
                .post("/api/products")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        RestAssured.given()
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("size()", Matchers.greaterThanOrEqualTo(2));

        // Clean up directly via repository
        productRepository.deleteById(productId1);
        productRepository.deleteById(productId2);
    }

    @Test
    void shouldGetProductById() {
        String requestBody = """
            {
                "name": "Test Product",
                "description": "This is a test product",
                "price": 99.99
            }
            """;

        String productId = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/products")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        RestAssured.given()
                .when()
                .get("/api/products/{id}", productId)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("Test Product"))
                .body("description", Matchers.equalTo("This is a test product"))
                .body("price", Matchers.equalTo(99.99f));

        // Clean up directly via repository
        productRepository.deleteById(productId);
    }

    @Test
    void shouldUpdateProductById() {
        String requestBody = """
            {
                "name": "Test Product",
                "description": "This is a test product",
                "price": 99.99
            }
            """;

        String productId = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/products")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        String updateRequestBody = """
            {
                "name": "Updated Product",
                "description": "This is an updated test product",
                "price": 149.99
            }
            """;

        RestAssured.given()
                .header("Content-Type", "application/json")
                .body(updateRequestBody)
                .when()
                .put("/api/products/{id}", productId)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("Updated Product"))
                .body("description", Matchers.equalTo("This is an updated test product"))
                .body("price", Matchers.equalTo(149.99f));

        // Clean up directly via repository
        productRepository.deleteById(productId);
      }

    @Test
    void shouldDeleteProductById() {
        String requestBody = """
            {
                "name": "Test Product",
                "description": "This is a test product",
                "price": 99.99
            }
            """;

        String productId = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/products")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        RestAssured.given()
                .when()
                .delete("/api/products/{id}", productId)
                .then()
                .statusCode(204);
    }
}