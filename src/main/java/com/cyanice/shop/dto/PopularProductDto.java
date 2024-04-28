package com.cyanice.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PopularProductDto {
    private long id;
    private String name;
    private String description;
    private Double price;
    private long saleCount;
}
