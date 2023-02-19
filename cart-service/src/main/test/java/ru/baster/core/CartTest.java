package java.ru.baster.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.baster.study.market.cart.services.CartService;
import ru.baster.study.market.core.model.Product;
import ru.baster.study.market.core.service.ProductService;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class CartTest {
    @Autowired
    private CartService cartService;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void initCart() {
        cartService.clear("test_cart");
    }

    @Test
    public void addToCartTest() {
        Product product = new Product();
        product.setTitle("test");
        product.setPrice(BigDecimal.valueOf(100));

        Mockito.doReturn(Optional.of(product)).when(productService).findById(5L);
        cartService.add("test_cart", 5L);
        cartService.add("test_cart", 5L);
        cartService.add("test_cart", 5L);

        Mockito.verify(productService, Mockito.times(1)).findById(ArgumentMatchers.eq(5L));
        Assertions.assertEquals(1, cartService.getCurrentCart("test_cart").getItems().size());
    }
}