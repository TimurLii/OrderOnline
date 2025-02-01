package com.example.orderonlinepetproject.repository;

import com.example.orderonlinepetproject.dto.OrderDto;
import com.example.orderonlinepetproject.entity.Order;
import com.example.orderonlinepetproject.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    ResponseEntity<List<OrderDto>> findAllByStatus(Status status);
}
