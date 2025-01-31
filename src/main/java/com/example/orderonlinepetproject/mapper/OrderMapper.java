package com.example.orderonlinepetproject.mapper;

import com.example.orderonlinepetproject.dto.OrderDto;
import com.example.orderonlinepetproject.entity.Order;
import com.example.orderonlinepetproject.entity.Product;

public class OrderMapper {
    public static Order convertOrderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setCustomerId(orderDto.getCustomerId());

        if (orderDto.getProduct() != null && orderDto.getProduct().getProductId() != null) {
            Product product = new Product();
            product.setProductId(orderDto.getProduct().getProductId());
            order.setProduct(product);
        } else {
            throw new IllegalArgumentException("Product or Product ID must not be null");
        }

        order.setStatus(orderDto.getStatus());
        return order;
    }

    public static OrderDto convertOrderToOrderDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getCustomerId(),
                ProductMapper.getProductDto(order),
                order.getStatus());
    }

}
