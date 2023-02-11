package ru.baster.spring.sample.shop.study.market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.baster.spring.sample.shop.study.market.model.Category;
import ru.baster.spring.sample.shop.study.market.repository.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

}
