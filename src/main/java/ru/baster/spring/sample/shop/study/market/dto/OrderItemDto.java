package ru.baster.spring.sample.shop.study.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;
}
