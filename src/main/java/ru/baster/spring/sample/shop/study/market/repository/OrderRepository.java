package ru.baster.spring.sample.shop.study.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.baster.spring.sample.shop.study.market.model.Order;
import ru.baster.spring.sample.shop.study.market.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Page<Order> findAllByUser(User user, Pageable pageable);
}