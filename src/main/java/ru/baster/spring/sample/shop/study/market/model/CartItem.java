package ru.baster.spring.sample.shop.study.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String name;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public void changeQuantity(int delta) {
        quantity += delta;
        price = pricePerProduct.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);
    }

}