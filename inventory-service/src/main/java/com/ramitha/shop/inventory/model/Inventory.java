package com.ramitha.shop.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "t_inventory")
@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    private Long id;

    private String skuCode;

    private Integer quantity;

}
