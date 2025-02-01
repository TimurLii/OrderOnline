package com.example.orderonlinepetproject.entity;

import com.example.orderonlinepetproject.dto.ProductDto;
import com.example.orderonlinepetproject.enums.Status;
import com.example.orderonlinepetproject.exeption.InvalidStatusForOrder;
import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "customer_id")
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public void updateStatus(Status newStatus) {
        if (this.status.canTransitionTo(newStatus)) {
            this.status = newStatus;
        } else {
            throw new InvalidStatusForOrder("Cannot transition from " + this.status + " to " + newStatus);
        }
    }
}
