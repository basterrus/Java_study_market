package ru.baster.spring.sample.shop.study.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.baster.spring.sample.shop.study.market.dto.OrderItemDto;
import ru.baster.spring.sample.shop.study.market.exception.ResourceNotFoundException;
import ru.baster.spring.sample.shop.study.market.model.CartItem;
import ru.baster.spring.sample.shop.study.market.model.Order;
import ru.baster.spring.sample.shop.study.market.model.OrderItem;
import ru.baster.spring.sample.shop.study.market.service.ProductService;


import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderItemConverter {
    private final ProductService productService;

    public OrderItem cartItemToOrderItem(CartItem cartItem, Order order) {
        var product = productService.findById(cartItem.getProductId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Product id: %s not found", cartItem.getProductId())));
        return new OrderItem(null,
                product,
                order,
                cartItem.getQuantity(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice(),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    public OrderItemDto entityToDto(OrderItem o) {
        return new OrderItemDto(o.getId(), o.getProduct().getName(), o.getProduct().getDescription(), o.getProduct().getQuantity(), o.getProduct().getPrice(), o.getPricePerProduct());
    }
}
