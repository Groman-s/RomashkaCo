package com.goyanov.romashkaco.services;

import com.goyanov.romashkaco.model.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductsServiceTest
{
    @Autowired
    private ProductsService productsService;

    @Test
    public void testInStockFilter1()
    {
        List<ProductDTO> products = productsService.findAllWithFilters(
                null, true, null, null, PageRequest.of(0, 100));

        assertTrue(products.stream().allMatch(ProductDTO::getInStock));
    }

    @Test
    public void testInStockFilter2()
    {
        List<ProductDTO> products = productsService.findAllWithFilters(
                null, false, null, null, PageRequest.of(0, 100));

        assertTrue(products.stream().noneMatch(ProductDTO::getInStock));
    }

    @Test
    public void testMultipleFilters()
    {
        List<ProductDTO> products = productsService.findAllWithFilters(
                "яблоко", false, BigDecimal.valueOf(100), BigDecimal.valueOf(900),
                PageRequest.of(0, 100));

        assertTrue(products.stream().allMatch(dto -> dto.getName().toLowerCase().contains("яблоко")));
        assertTrue(products.stream().noneMatch(ProductDTO::getInStock));
        assertTrue(products.stream().allMatch(dto -> dto.getPrice().compareTo(BigDecimal.valueOf(100)) >= 0));
        assertTrue(products.stream().allMatch(dto -> dto.getPrice().compareTo(BigDecimal.valueOf(900)) <= 0));
    }
}