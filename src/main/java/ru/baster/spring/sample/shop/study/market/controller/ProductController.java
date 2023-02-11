package ru.baster.spring.sample.shop.study.market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.baster.spring.sample.shop.study.market.dto.ProductDto;
import ru.baster.spring.sample.shop.study.market.exception.ResourceNotFoundException;
import ru.baster.spring.sample.shop.study.market.model.Product;
import ru.baster.spring.sample.shop.study.market.service.ProductService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream().map(p -> new ProductDto(p.getId(), p.getName(), p.getPrice(), p.getQuantity())).collect(Collectors.toList());
    }

   /* @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            ResponseEntity<AppError> err = new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Продукт не найден, id:" + id), HttpStatus.NOT_FOUND);
            return err;
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }*/

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id:" + id));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
