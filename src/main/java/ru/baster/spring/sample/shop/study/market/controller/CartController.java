package ru.baster.spring.sample.shop.study.market.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.baster.spring.sample.shop.study.market.exception.ResourceNotFoundException;
import ru.baster.spring.sample.shop.study.market.model.Product;
import ru.baster.spring.sample.shop.study.market.service.ProductService;
import ru.baster.spring.sample.shop.study.market.util.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartController {

    private ProductService productService;
    private Cart cart;


    @GetMapping("/add/{product_id}")
    public void add(@PathVariable(name = "product_id") Long productId,
                    HttpServletRequest request,
                    HttpServletResponse response) throws IOException {
        Product product = productService.findById(productId).orElseThrow(()->new ResourceNotFoundException("Продукт не найден"));
        cart.addOrIncrement(product);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/inc/{product_id}")
    public String addOrIncrement(@PathVariable(name = "product_id") Long productId)  {
        cart.increment(productId);
        return "redirect:/cart";
    }

    @GetMapping("/dec/{product_id}")
    public String decrementOrRemove(@PathVariable(name = "product_id") Long productId)  {
        cart.decrementOrRemove(productId);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{product_id}")
    public String remove(@PathVariable(name = "product_id") Long productId)  {
        cart.remove(productId);
        return "redirect:/cart";
    }


}