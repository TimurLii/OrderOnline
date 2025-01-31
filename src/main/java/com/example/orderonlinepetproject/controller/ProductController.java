package com.example.orderonlinepetproject.controller;

import com.example.orderonlinepetproject.aspect.MyLogFromMethod;
import com.example.orderonlinepetproject.dto.ProductDto;
import com.example.orderonlinepetproject.entity.Product;
import com.example.orderonlinepetproject.mapper.ProductMapper;
import com.example.orderonlinepetproject.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/product")
@MyLogFromMethod
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {

        Product product = ProductMapper.convertProductDtoToProduct(productDto);

        Product createProduct = productService.createProduct(product);

        ProductDto createProductDto = ProductMapper.convertProductToProductDto(createProduct);

        return ResponseEntity.status(HttpStatus.OK).body(createProductDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {

        List<Product> productList = productService.getAllProducts();

        List<ProductDto> productDtoList = productList.stream()
                .map(ProductMapper::convertProductToProductDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(productDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {

        Product product = productService.findById(id);

        if(product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ProductDto productDto = ProductMapper.convertProductToProductDto(product);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto>
    updateProductById(@PathVariable Long id,@RequestBody ProductDto productDto)
    {
        Product existingProduct = productService.findById(id);
        if(existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setQuantity(productDto.getQuantity());

        Product updateProduct = productService.updateProduct(existingProduct);
        ProductDto updateProductDto = ProductMapper.convertProductToProductDto(updateProduct);

        return ResponseEntity.status(HttpStatus.OK).body(updateProductDto);
    }

}
