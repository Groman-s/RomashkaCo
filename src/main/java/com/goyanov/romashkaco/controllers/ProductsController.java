package com.goyanov.romashkaco.controllers;

import com.goyanov.romashkaco.model.dto.ProductDTO;
import com.goyanov.romashkaco.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/products")
public class ProductsController
{
    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService)
    {
        this.productsService = productsService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts
    (
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,

        @RequestParam(required = false) Boolean inStock,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) BigDecimal maxPrice,
        @RequestParam(required = false) BigDecimal minPrice,

        @RequestParam(defaultValue = "name") String orderBy,
        @RequestParam(defaultValue = "asc") String direction
    )
    {
        return ResponseEntity.ok(productsService.findAllWithFilters
            (page, size, name, inStock, minPrice, maxPrice, orderBy, direction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id)
    {
        return ResponseEntity.ok(productsService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO)
    {
        productsService.save(productDTO);
        return ResponseEntity.ok("Товар успешно добавлен!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO)
    {
        productsService.update(id, productDTO);
        return ResponseEntity.ok("Товар успешно обновлён!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id)
    {
        productsService.deleteById(id);
        return ResponseEntity.ok().body("Продукт успешно удалён!");
    }
}
