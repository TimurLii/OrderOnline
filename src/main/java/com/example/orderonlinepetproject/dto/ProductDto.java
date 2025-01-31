package com.example.orderonlinepetproject.dto;

import com.example.orderonlinepetproject.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * DTO for {@link Product}
 */
@Value
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ProductDto {
    Long productId;
    Long quantity;
    Long price;
}