package com.example.orderonlinepetproject.repository;

import com.example.orderonlinepetproject.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p.quantity FROM Product p WHERE p.productId = :productId")
    Long findQuantityByProductId(@Param("productId") Long productId);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.quantity = p.quantity - :amount WHERE p.productId = :productId AND p.quantity >= :amount")
    void decreaseQuantityByProductId(@Param("productId") Long productId, @Param("amount") Long amount);
}


