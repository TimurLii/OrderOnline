package com.example.orderonlinepetproject.mapper;

import com.example.orderonlinepetproject.dto.OrderDto;
import com.example.orderonlinepetproject.dto.ProductDto;
import com.example.orderonlinepetproject.entity.Order;
import com.example.orderonlinepetproject.entity.Product;

public class OrderMapper {
    public static Order convertOrderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setCustomerId(orderDto.getCustomerId());
        order.setProduct(getProduct(orderDto));
        return order;
    }

    public static OrderDto convertOrderToOrderDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getCustomerId(),
                getProductDto(order),
                order.getStatus());
    }

    private static Product getProduct(OrderDto orderDto) {
        Product product = new Product();
        product.setProductId(orderDto.getProduct().getProductId());
        product.setQuantity(orderDto.getProduct().getQuantity());
        product.setPrice(orderDto.getProduct().getPrice());
        return product;
    }

    private static ProductDto getProductDto(Order order) {

        return new ProductDto(
                order.getProduct().getProductId(),
                order.getProduct().getQuantity(),
                order.getProduct().getPrice());
    }
}
