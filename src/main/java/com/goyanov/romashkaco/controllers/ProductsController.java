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

    @Autowired
    public ProductsController(ProductsService productsService)
    {
        this.productsService = productsService;
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts()
    {
        return ResponseEntity.ok(productsService.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id)
    {
        return ResponseEntity.ok(productsService.findById(id));
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO)
    {
        productsService.save(productDTO);
        return ResponseEntity.ok("Товар успешно добавлен!");
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO)
    {
        productsService.update(id, productDTO);
        return ResponseEntity.ok("Товар успешно обновлён!");
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id)
    {
        productsService.deleteById(id);
        return ResponseEntity.ok().body("Продукт успешно удалён!");
    }
}
