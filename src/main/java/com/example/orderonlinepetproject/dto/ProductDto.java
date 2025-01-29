package com.example.orderonlinepetproject.dto;

import com.example.orderonlinepetproject.entity.Product;
import lombok.Data;
import lombok.Value;

/**
 * DTO for {@link Product}
 */
@Value
@Data
public class ProductDto {
    Long productId;
    Long quantity;
    Long price;
}