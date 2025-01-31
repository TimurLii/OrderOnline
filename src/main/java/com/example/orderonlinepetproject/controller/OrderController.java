package com.example.orderonlinepetproject.controller;

import com.example.orderonlinepetproject.aspect.MyLogFromMethod;
import com.example.orderonlinepetproject.dto.OrderDto;
import com.example.orderonlinepetproject.entity.Order;
import com.example.orderonlinepetproject.mapper.OrderMapper;
import com.example.orderonlinepetproject.mapper.ProductMapper;
import com.example.orderonlinepetproject.service.OrderService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@MyLogFromMethod
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {

      Order order = OrderMapper.convertOrderDtoToOrder(orderDto);// преобразуем dto в сущность

      Order createOrder = orderService.createOrder(order);// сохранение order

      OrderDto createOrderDto = OrderMapper.convertOrderToOrderDto(createOrder); // преобразуем обратно в dto

      return ResponseEntity.status(HttpStatus.OK).body(createOrderDto);

    }
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List <Order> orders = orderService.getAllOrders();
        List<OrderDto> orderDtos = orders.stream()
                .map(OrderMapper :: convertOrderToOrderDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(orderDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {

        Order order = orderService.getOrderById(id);

        if(order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        OrderDto orderDto = OrderMapper.convertOrderToOrderDto(order);

        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        Order existingOrder = orderService.getOrderById(id);
        if (existingOrder == null) {
            return ResponseEntity.notFound().build();
        }

        existingOrder.setCustomerId(orderDto.getCustomerId());
        existingOrder.setProduct(ProductMapper.productDtoToProduct(orderDto.getProduct()));
        existingOrder.setStatus(orderDto.getStatus());

        Order updatedOrder = orderService.updateOrder(existingOrder);
        OrderDto updatedOrderDto = OrderMapper.convertOrderToOrderDto(updatedOrder);
        return ResponseEntity.ok(updatedOrderDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
