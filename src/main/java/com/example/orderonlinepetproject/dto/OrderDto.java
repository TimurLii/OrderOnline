package com.example.orderonlinepetproject.dto;

import com.example.orderonlinepetproject.entity.Order;
import com.example.orderonlinepetproject.entity.Product;
import com.example.orderonlinepetproject.enums.Status;
import lombok.Data;
import lombok.Value;

/**
 * DTO for {@link Order}
 */
@Value
@Data
public class OrderDto {
    Long orderId;
    Long customerId;
    ProductDto product;
    Status status;


}