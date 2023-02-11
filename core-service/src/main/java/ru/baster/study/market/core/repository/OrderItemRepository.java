package ru.baster.study.market.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.baster.study.market.core.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
