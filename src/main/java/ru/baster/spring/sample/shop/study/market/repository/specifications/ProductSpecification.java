package ru.baster.spring.sample.shop.study.market.repository.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.baster.spring.sample.shop.study.market.model.Product;

import java.math.BigDecimal;


public class ProductSpecification {
    public static Specification<Product> priceGranderOrEqualsThen(BigDecimal price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> priceLessOrEqualsThen(BigDecimal price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> titleLike(String titlePart) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }
}
