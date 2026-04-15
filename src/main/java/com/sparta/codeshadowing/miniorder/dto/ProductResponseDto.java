package com.sparta.codeshadowing.miniorder.dto;

import com.sparta.codeshadowing.miniorder.entity.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {

    private final Long id;
    private final String name;
    private final Integer price;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
