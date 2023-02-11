package ru.baster.study.market.core.converters;

import lombok.RequiredArgsConstructor;
import ru.baster.study.market.core.model.Order;
import org.springframework.stereotype.Component;
import ru.baster.study.market.core.dto.OrderDto;
import ru.baster.study.market.core.dto.OrderItemDto;

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
