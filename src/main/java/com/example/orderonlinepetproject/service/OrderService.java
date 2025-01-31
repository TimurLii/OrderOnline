package com.example.orderonlinepetproject.service;

import com.example.orderonlinepetproject.aspect.LogHibernateOperation;
import com.example.orderonlinepetproject.dto.OrderDto;
import com.example.orderonlinepetproject.entity.Order;
import com.example.orderonlinepetproject.entity.Product;
import com.example.orderonlinepetproject.exeption.InsufficienciesException;
import com.example.orderonlinepetproject.mapper.OrderMapper;
import com.example.orderonlinepetproject.repository.OrderRepository;
import com.example.orderonlinepetproject.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Order createOrder(Order order, Long transferQuantity) {
        Product productInDb = productRepository.findById(order.getProduct().getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        if(!checkQuantity(order, transferQuantity)){
            throw new InsufficienciesException("Excessive amount");
        }

        productRepository.decreaseQuantityByProductId(productInDb.getProductId(), transferQuantity);
        order.setProduct(productInDb);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }
    //TODO Сделать 400 ошибку за место 500

    public Order updateOrder(Order existingOrder) {
        return orderRepository.save(existingOrder);
    }


    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    private boolean checkQuantity(Order order, Long transferQuantity) {
        Long quantityByProductId = productRepository.findQuantityByProductId(order.getProduct().getProductId());
        return quantityByProductId >= transferQuantity;
    }
}
