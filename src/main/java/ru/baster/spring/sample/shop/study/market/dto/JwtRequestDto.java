package ru.baster.spring.sample.shop.study.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequestDto {
    private String username;
    private String password;
}
