package com.goyanov.romashkaco.repositories;

import com.goyanov.romashkaco.model.Product;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
class ProductsRepositoryTest
{
    @Autowired
    private ProductsRepository productsRepository;

    @Test
    public void testFilters()
    {
        Pageable pageable = PageRequest.of(0, 10);
        List<Product> products = productsRepository.findByKeyWord("яблоко", pageable).getContent();
        System.out.println("SUCCESS");
    }
}