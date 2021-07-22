package com.product.productManager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ProductDTO {
    private String productID;
    private String name;
    private String description;
    private Double weight;
    private Double price;
    private String country;
}
