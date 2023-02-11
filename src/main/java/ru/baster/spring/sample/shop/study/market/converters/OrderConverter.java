package ru.baster.spring.sample.shop.study.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.baster.spring.sample.shop.study.market.dto.OrderDto;
import ru.baster.spring.sample.shop.study.market.dto.OrderItemDto;
import ru.baster.spring.sample.shop.study.market.model.Order;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order) {
        List<OrderItemDto> items = order.getItems().stream().map(orderItemConverter::entityToDto).toList();
        return new OrderDto(order.getId(), order.getTotalPrice(), items);
    }
}
