package ru.baster.spring.sample.shop.study.market.converters;

import org.springframework.stereotype.Component;
import ru.baster.spring.sample.shop.study.market.dto.CartItemDto;
import ru.baster.spring.sample.shop.study.market.model.CartItem;


@Component
public class CartItemConverter {

    public CartItemDto entityToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setImage(cartItem.getImage());
        return cartItemDto;
    }
}
