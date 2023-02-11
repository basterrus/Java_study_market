package ru.baster.study.market.cart.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.baster.study.market.cart.integrations.ProductServiceIntegration;
import ru.baster.study.market.cart.models.Cart;
import ru.baster.study.market.cart.models.CartItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.function.Consumer;


@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;
    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    public void add(String uuid, Long id) {
        ProductDto product = productServiceIntegration.getProductById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Can't add product to the cart, product id:%s not found", id)));
        log.info("add product method {}", product.getTitle());
        execute(uuid, cart -> cart.add(ProductDto));
    }

    public void deleteProductFromCart(String uuid, Long id) {
        execute(uuid, cart -> cart.remove(id));
    }
    public void deleteAllQuantity(String uuid, Long id) {
        execute(uuid, cart -> cart.removeAllQuantity(id));
    }
    private String getTargetUuid(String uuid) {
        return cartPrefix + uuid;
    }
    public Cart getCurrentCart(String uuid) {
        String targetUuid = getTargetUuid(uuid);
         if (!redisTemplate.hasKey(targetUuid)) {
            redisTemplate.opsForValue().set(targetUuid, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }
    public void clear(String uuid) {
        execute(uuid, Cart::clear);
    }

    private void execute(String uuid, Consumer<Cart> operation) {
        Cart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(getTargetUuid(uuid), cart);
    }

    public void mergeCart(String uuid, String targetUuid) {
        if (redisTemplate.hasKey(getTargetUuid(uuid))) {
            Cart guestCart = getCurrentCart(uuid);
            for (CartItem item : guestCart.getItems()) {
                add(targetUuid, item.getProductId());
            }
        }
    }
}
