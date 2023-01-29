package ru.baster.spring.sample.shop.study.market.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.baster.spring.sample.shop.study.market.converters.OrderConverter;
import ru.baster.spring.sample.shop.study.market.dto.OrderDto;
import ru.baster.spring.sample.shop.study.market.exception.ResourceNotFoundException;
import ru.baster.spring.sample.shop.study.market.model.OrderData;
import ru.baster.spring.sample.shop.study.market.model.User;
import ru.baster.spring.sample.shop.study.market.service.OrderService;
import ru.baster.spring.sample.shop.study.market.service.UserService;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void creatOrder(Principal principal, @RequestBody(required = false) OrderData orderData) {
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with username: %s not found", principal.getName())));
        orderService.creatOrder(user);
    }

    @GetMapping
    public List<OrderDto> getOrders(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException(String.format("User with username: %s not found", principal.getName())));
        return orderService.getAllOrdersByUser(user).stream().map(orderConverter::entityToDto).toList();
    }

}