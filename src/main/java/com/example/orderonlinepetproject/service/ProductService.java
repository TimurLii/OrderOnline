package com.example.orderonlinepetproject.service;

import com.example.orderonlinepetproject.aspect.LogHibernateOperation;
import com.example.orderonlinepetproject.entity.Product;
import com.example.orderonlinepetproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@LogHibernateOperation
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return  productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public Product updateProduct(Product existingProduct) {
        return productRepository.save(existingProduct);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
