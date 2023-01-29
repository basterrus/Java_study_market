package ru.baster.spring.sample.shop.study.market.service;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;
import ru.baster.spring.sample.shop.study.market.dto.ProductDto;
import ru.baster.spring.sample.shop.study.market.model.Product;
import ru.baster.spring.sample.shop.study.market.repository.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
