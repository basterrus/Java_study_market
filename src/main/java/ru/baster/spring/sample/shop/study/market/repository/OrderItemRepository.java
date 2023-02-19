package ru.baster.spring.sample.shop.study.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.baster.spring.sample.shop.study.market.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
