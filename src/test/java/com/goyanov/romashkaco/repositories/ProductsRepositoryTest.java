package com.goyanov.romashkaco.repositories;

import com.goyanov.romashkaco.model.Product;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductsRepositoryTest
{
    @Autowired
    private ProductsRepository productsRepository;

    @Test
    @Disabled
    public void testFilters()
    {
        List<Product> products = productsRepository.filterByKeyword("яблоко");
        System.out.println("SUCCESS");
    }
}