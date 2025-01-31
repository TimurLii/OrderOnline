package com.example.orderonlinepetproject.mapper;

import com.example.orderonlinepetproject.dto.OrderDto;
import com.example.orderonlinepetproject.dto.ProductDto;
import com.example.orderonlinepetproject.entity.Order;
import com.example.orderonlinepetproject.entity.Product;

public class ProductMapper {
    public static ProductDto productToProductDto(Product product) {
        return new ProductDto(product.getProductId(), product.getQuantity(), product.getPrice());
    }
    public static Product convertProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());
        return product;
    }

    public static ProductDto convertProductToProductDto(Product product) {
        return new ProductDto(
                product.getProductId()
                , product.getQuantity()
                , product.getPrice());
    }

    public static Product getProduct(OrderDto orderDto) {
        Product product = new Product();
        product.setProductId(orderDto.getProduct().getProductId());
        product.setQuantity(orderDto.getProduct().getQuantity());
        product.setPrice(orderDto.getProduct().getPrice());
        return product;
    }

    public static ProductDto getProductDto(Order order) {

        return new ProductDto(
                order.getProduct().getProductId(),
                order.getProduct().getQuantity(),
                order.getProduct().getPrice());
    }

}
