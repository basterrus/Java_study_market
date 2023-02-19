package ru.baster.study.market.core.repository;

import ru.baster.study.market.core.model.Order;
import ru.baster.study.market.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
