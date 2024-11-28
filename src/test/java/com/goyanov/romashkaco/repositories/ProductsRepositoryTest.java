package com.goyanov.romashkaco.repositories;

import com.goyanov.romashkaco.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductsRepositoryTest
{
    @Autowired
    private ProductsRepository productsRepository;

    @BeforeEach
    public void addProducts()
    {
        productsRepository.save(new Product(null, "Лопата", "", BigDecimal.valueOf(500), true));
        productsRepository.save(new Product(null, "Дверь", "", BigDecimal.valueOf(100), true));
        productsRepository.save(new Product(null, "Яблоко", "", BigDecimal.valueOf(50), true));
        productsRepository.save(new Product(null, "Шкаф", "", BigDecimal.valueOf(300), true));
        productsRepository.save(new Product(null, "Тарелка", "", BigDecimal.valueOf(100), true));
    }

    @Test
    public void testFindAll()
    {
        assertEquals(5, productsRepository.findAll().size());
    }

    @Test
    public void testGeneratedId()
    {
        assertEquals("Яблоко", productsRepository.findById(3L).get().getName());
    }
}