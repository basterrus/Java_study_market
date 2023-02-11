package ru.baster.spring.sample.shop.study.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.baster.spring.sample.shop.study.market.model.Order;
import ru.baster.spring.sample.shop.study.market.model.User;


import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
