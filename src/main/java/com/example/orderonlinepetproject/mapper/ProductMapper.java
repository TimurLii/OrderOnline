package com.example.orderonlinepetproject.mapper;

import com.example.orderonlinepetproject.dto.ProductDto;
import com.example.orderonlinepetproject.entity.Product;

public class ProductMapper {
    public static ProductDto productToProductDto(Product product) {
        return new ProductDto(product.getProductId(), product.getQuantity(), product.getPrice());
    }
    public static Product productDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
