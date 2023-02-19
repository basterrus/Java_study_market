package ru.baster.spring.sample.shop.study.market.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    private String title;
    private List<ProductDto> products;
}
