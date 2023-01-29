package ru.baster.spring.sample.shop.study.market.filters;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.baster.spring.sample.shop.study.market.model.Product;
import ru.baster.spring.sample.shop.study.market.repository.specifications.ProductSpecification;

import java.math.BigDecimal;
import java.util.Map;

//@Getter
//public class ProductFilter {
//    private Specification<Product> spec;
//    private String filterDefinition;
//
//    public ProductFilter(Map<String, String> params) {
//        StringBuilder filterDefinitionBuilder = new StringBuilder();
//        spec = Specification.where(null);
//
//        String filterTitle = params.get("name");
//        if (filterTitle != null && !filterTitle.isBlank()) {
//            spec = spec.and(ProductSpecification.titleLike(filterTitle));
//            filterDefinitionBuilder.append("&name=").append(filterTitle);
//        }
//
//        if (params.containsKey("minPrice") && !params.get("minPrice").isBlank()) {
//            Integer minPrice = Integer.parseInt(params.get("minPrice"));
//            spec = spec.and(ProductSpecification.priceGranderOrEqualsThen(minPrice));
//            filterDefinitionBuilder.append("&minPrice=").append(minPrice);
//        }
//
//        if (params.containsKey("maxPrice") && !params.get("maxPrice").isBlank()) {
//            Integer maxPrice = Integer.parseInt(params.get("maxPrice"));
//            spec = spec.and(ProductSpecification.priceLessOrEqualsThen(maxPrice));
//            filterDefinitionBuilder.append("&maxPrice=").append(maxPrice);
//        }
//
//        filterDefinition = filterDefinitionBuilder.toString();
//    }
//}