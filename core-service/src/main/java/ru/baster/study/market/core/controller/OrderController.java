package ru.baster.study.market.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import ru.baster.study.market.auth.exceptions.ResourceNotFoundException;
import ru.baster.study.market.auth.models.User;
import ru.baster.study.market.auth.services.UserService;
import ru.baster.study.market.core.converters.OrderConverter;
import ru.baster.study.market.core.model.OrderData;
import ru.baster.study.market.core.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<SpringDataJaxb.OrderDto> getOrders(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException(String.format("User with username: %s not found", principal.getName())));
        return orderService.getAllOrdersByUser(user).stream().map(orderConverter::entityToDto).toList();
    }

}