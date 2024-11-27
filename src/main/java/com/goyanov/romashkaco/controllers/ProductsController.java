package com.goyanov.romashkaco.controllers;

import com.goyanov.romashkaco.model.dto.ProductDTO;
import com.goyanov.romashkaco.model.dto.mappers.ProductMapper;
import com.goyanov.romashkaco.services.ProductsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductsController
{
    private final ProductsService productsService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductsController(ProductsService productsService, ProductMapper productMapper)
    {
        this.productsService = productsService;
        this.productMapper = productMapper;
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts()
    {
        return ResponseEntity.ok(productsService.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id)
    {
        return ResponseEntity.ok(productsService.findById(id));
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody @Valid ProductDTO productDTO)
    {
        productsService.save(productMapper.toEntity(productDTO));
        return ResponseEntity.ok("Товар успешно добавлен!");
    }

    @PostMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody @Valid ProductDTO productDTO)
    {
        productsService.update(id, productMapper.toEntity(productDTO));
        return ResponseEntity.ok("Товар успешно обновлён!");
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id)
    {
        productsService.deleteById(id);
        return ResponseEntity.ok().body("Продукт успешно удалён!");
    }
}
