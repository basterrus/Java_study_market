package ru.baster.spring.sample.shop.study.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.baster.spring.sample.shop.study.market.dto.CategoryDto;
import ru.baster.spring.sample.shop.study.market.model.Category;


@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final ProductConverter productConverter;

    public CategoryDto entityToDto(Category category) {
        CategoryDto c = new CategoryDto();
        c.setId(category.getId());
        c.setTitle(category.getTitle());
        c.setProducts(category.getProducts().stream().map(productConverter::entityToDto).toList());
        return c;
    }

    public Category dtoToEntity(CategoryDto categoryDto) {
        Category c = new Category();
        c.setId(categoryDto.getId());
        c.setTitle(categoryDto.getTitle());
        c.setProducts(categoryDto.getProducts().stream().map(productConverter::dtoToEntity).toList());
        return c;
    }
}
