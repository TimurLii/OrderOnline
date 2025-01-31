package com.example.orderonlinepetproject.service;

import com.example.orderonlinepetproject.aspect.LogHibernateOperation;
import com.example.orderonlinepetproject.dto.OrderDto;
import com.example.orderonlinepetproject.entity.Order;
import com.example.orderonlinepetproject.entity.Product;
import com.example.orderonlinepetproject.repository.OrderRepository;
import com.example.orderonlinepetproject.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@LogHibernateOperation
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        Product product = productRepository.findById(order.getProduct().getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        order.setProduct(product); // Привязываем управляемый объект
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public Order updateOrder(Order existingOrder) {
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
