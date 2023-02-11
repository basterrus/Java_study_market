package ru.baster.study.market.core.converters;

import lombok.RequiredArgsConstructor;
import ru.baster.study.market.core.exception.ResourceNotFoundException;
import ru.baster.study.market.core.model.CartItem;
import ru.baster.study.market.core.model.Order;
import ru.baster.study.market.core.service.ProductService;
import org.springframework.stereotype.Component;
import ru.baster.study.market.core.dto.OrderItemDto;
import ru.baster.study.market.core.model.OrderItem;


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
        return new OrderItemDto(o.getId(), o.getProduct().getTitle(), o.getProduct().getImage(), o.getQuantity(), o.getPricePerProduct(), o.getPrice());
    }
}
