package com.ramitha.shop.order.repository;

import com.ramitha.shop.order.client.InventoryClient;
import com.ramitha.shop.order.model.Order;
import com.ramitha.shop.order.stub.InventoryClientStub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.math.BigDecimal;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureWireMock(port = 0)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saveOrder_shouldSaveOrderSuccessfully() {
        Order order = Order.builder()
                .orderNumber("ORD123456")
                .skuCode("SKU123")
                .price(BigDecimal.valueOf(50))
                .quantity(1)
                .build();

        InventoryClientStub.stubInventoryCall("SKU123", 1,   true);

        orderRepository.save(order);

        Order savedOrder = orderRepository.findById(order.getId()).orElse(null);
        assert savedOrder != null;
        assert savedOrder.getOrderNumber().equals("ORD123456");
        assert savedOrder.getSkuCode().equals("SKU123");
        assert savedOrder.getPrice().equals(BigDecimal.valueOf(50));
        assert savedOrder.getQuantity() == 1;

        orderRepository.delete(order);
    }
}
