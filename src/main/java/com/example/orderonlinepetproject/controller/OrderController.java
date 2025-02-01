package com.example.orderonlinepetproject.controller;

import com.example.orderonlinepetproject.aspect.MyLogFromMethod;
import com.example.orderonlinepetproject.dto.OrderDto;
import com.example.orderonlinepetproject.entity.Order;
import com.example.orderonlinepetproject.exeption.OrderNotFoundException;
import com.example.orderonlinepetproject.mapper.OrderMapper;
import com.example.orderonlinepetproject.mapper.ProductMapper;
import com.example.orderonlinepetproject.service.OrderService;
import com.example.orderonlinepetproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@MyLogFromMethod
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {

      Order order = OrderMapper.convertOrderDtoToOrder(orderDto);// преобразуем dto в сущность

      Order createOrder = orderService.createOrder(order , orderDto.getProduct().getQuantity());// сохранение order

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
            throw new OrderNotFoundException("Order with ID " + id + " not found.");
        }

        OrderDto orderDto = OrderMapper.convertOrderToOrderDto(order);

        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }



    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrderById(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        Order existingOrder = orderService.getOrderById(id);
        if (existingOrder == null) {
            return ResponseEntity.notFound().build();
        }

        existingOrder.setCustomerId(orderDto.getCustomerId());
        existingOrder.setProduct(ProductMapper.convertProductDtoToProduct(orderDto.getProduct()));
        existingOrder.setStatus(orderDto.getStatus());

        Order updatedOrder = orderService.updateOrder(existingOrder);
        OrderDto updatedOrderDto = OrderMapper.convertOrderToOrderDto(updatedOrder);
        return ResponseEntity.ok(updatedOrderDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if(order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            orderService.deleteOrderById(id);
            return ResponseEntity.status(HttpStatus.OK).body(OrderMapper.convertOrderToOrderDto(order));
        }
    }

}
