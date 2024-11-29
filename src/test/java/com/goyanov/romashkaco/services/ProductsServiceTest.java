package com.goyanov.romashkaco.services;

import com.goyanov.romashkaco.model.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
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
            0, 100, null, true, null, null, "id", "asc");

        assertTrue(products.stream().allMatch(ProductDTO::getInStock));
    }

    @Test
    public void testInStockFilter2()
    {
        List<ProductDTO> products = productsService.findAllWithFilters(
            0, 100, null, false, null, null, "id", "asc");

        assertTrue(products.stream().noneMatch(ProductDTO::getInStock));
    }

    @Test
    public void testMultipleFilters()
    {
        List<ProductDTO> products = productsService.findAllWithFilters(
            0, 100, "яблоко", false, BigDecimal.valueOf(100), BigDecimal.valueOf(900),
            "id", "asc");

        assertTrue(products.stream().allMatch(dto -> dto.getName().toLowerCase().contains("яблоко")));
        assertTrue(products.stream().noneMatch(ProductDTO::getInStock));
        assertTrue(products.stream().allMatch(dto -> dto.getPrice().compareTo(BigDecimal.valueOf(100)) >= 0));
        assertTrue(products.stream().allMatch(dto -> dto.getPrice().compareTo(BigDecimal.valueOf(900)) <= 0));
    }

    @Test
    public void testSorting()
    {
        List<ProductDTO> products = productsService.findAllWithFilters(
            0, 100, null, null, null, null,
            "name", "asc");

        List<ProductDTO> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparing(ProductDTO::getName));

        for (int i = 0; i < products.size(); i++)
        {
            assertEquals(sortedProducts.get(i).getName(), products.get(i).getName());
        }
    }
}