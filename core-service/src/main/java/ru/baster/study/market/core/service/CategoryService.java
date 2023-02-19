package ru.baster.study.market.core.service;

import lombok.RequiredArgsConstructor;
import ru.baster.study.market.core.model.Category;
import ru.baster.study.market.core.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

}
