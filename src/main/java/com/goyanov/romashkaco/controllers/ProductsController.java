package com.goyanov.romashkaco.controllers;

import com.goyanov.romashkaco.model.Product;
import com.goyanov.romashkaco.model.dto.ProductDTO;
import com.goyanov.romashkaco.services.BaseCrudService;
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
public class ProductsController extends BaseCrudController<Product, Long, ProductDTO>
{
    @Autowired
    public ProductsController(BaseCrudService<Product, Long, ProductDTO> service)
    {
        super(service);
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
        return ResponseEntity.ok(((ProductsService)service).findAllWithFilters
            (page, size, name, inStock, minPrice, maxPrice, orderBy, direction));
    }

    @Override
    public String getAddedMessage()
    {
        return "Товар успешно добавлен!";
    }

    @Override
    public String getUpdatedMessage()
    {
        return "Товар успешно обновлён!";
    }

    @Override
    public String getDeletedMessage()
    {
        return "Продукт успешно удалён!";
    }
}
