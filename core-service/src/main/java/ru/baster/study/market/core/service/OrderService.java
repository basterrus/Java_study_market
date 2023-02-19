package ru.baster.study.market.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.baster.study.market.core.model.Order;
import ru.baster.study.market.core.model.User;
import ru.baster.study.market.core.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.baster.study.market.core.converters.OrderItemConverter;
import ru.baster.study.market.core.model.OrderItem;
import ru.baster.study.market.core.util.Cart;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final CartService cartService;
    private final OrderItemConverter orderItemConverter;
    private final OrderRepository orderRepository;

    @Transactional
    public void creatOrder(User user) {
        Cart cart = cartService.getCurrentCart(user.getUsername());
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());

        List<OrderItem> items = cart.getItems().stream().map(oi -> orderItemConverter.cartItemToOrderItem(oi, order)).toList();
        order.setItems(items);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        cartService.clear(user.getUsername());

    }

    public List<Order> getAllOrdersByUser(User user) {
        return orderRepository.findAllByUser(user).stream().toList();
    }
}
