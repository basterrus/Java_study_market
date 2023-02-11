package ru.baster.spring.sample.shop.study.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {
    private String address;
    private String phone;
}
