package ru.baster.spring.sample.shop.study.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.baster.spring.sample.shop.study.market.dto.CartDto;
import ru.baster.spring.sample.shop.study.market.util.Cart;


@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setItems(cart.getItems().stream().map(cartItemConverter::entityToDto).toList());
        return cartDto;
    }

}
