package ru.baster.study.market.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.baster.study.market.core.dto.ProductDto;
import ru.baster.study.market.core.exception.ResourceNotFoundException;
import ru.baster.study.market.core.model.Category;
import ru.baster.study.market.core.model.Product;
import ru.baster.study.market.core.service.CategoryService;
import ru.baster.study.market.core.soap.product.ProductSoapDto;


@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;
    public ProductDto entityToDto(Product product) {
        ProductDto p = new ProductDto();
        p.setId(product.getId());
        p.setTitle(product.getTitle());
        p.setPrice(product.getPrice());
        p.setCategoryTitle(product.getCategory().getTitle());
        p.setHeight(product.getHeight());
        p.setWeight(product.getWeight());
        p.setDescription(product.getDescription());
        p.setImage(product.getImage());
        return p;
    }
    public ProductSoapDto entityToSoapDto(Product product) {
        ProductSoapDto soapDto = new ProductSoapDto();
        soapDto.setId(product.getId());
        soapDto.setCategoryTitle(product.getTitle());
        soapDto.setPrice(product.getPrice());
        soapDto.setTitle(product.getTitle());
        soapDto.setHeight(product.getHeight());
        soapDto.setWeight(product.getWeight());
        soapDto.setDescription(product.getDescription());
        soapDto.setImage(product.getImage());
        return soapDto;
    }

    public Product dtoToEntity(ProductDto product) {
        Product p = new Product();
        p.setId(product.getId());
        p.setTitle(product.getTitle());
        p.setPrice(product.getPrice());
        Category category = categoryService.findByTitle(product.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        p.setCategory(category);
        return p;
    }
}
