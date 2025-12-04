package com.ramitha.shop.order.service;

import com.ramitha.shop.order.client.InventoryClient;
import com.ramitha.shop.order.dto.OrderRequest;
import com.ramitha.shop.order.event.OrderPlacedEvent;
import com.ramitha.shop.order.exception.OutOfStockException;
import com.ramitha.shop.order.model.Order;
import com.ramitha.shop.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final InventoryClient inventoryClient;

    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public Order placeOrder(OrderRequest orderRequest) {

        if (inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity())) {
            Order order = Order.builder()
                    .orderNumber(UUID.randomUUID().toString())
                    .skuCode(orderRequest.skuCode())
                    .price(orderRequest.price())
                    .quantity(orderRequest.quantity())
                    .build();

            orderRepository.save(order);

            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();

            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail(orderRequest.userDetails().email());
            orderPlacedEvent.setFirstName(orderRequest.userDetails().firstName());
            orderPlacedEvent.setLastName(orderRequest.userDetails().lastName());

            log.info("Sending OrderPlacedEvent to Kafka: {}", orderPlacedEvent);

            kafkaTemplate.send("order-placed", orderPlacedEvent);

            log.info("Order placed successfully");

            return order;
        } else {
            log.error("Product is not in stock, cannot place order");
            throw new OutOfStockException("Product is not in stock, cannot place order");
        }
    }
}
