package com.goyanov.romashkaco.services;

import com.goyanov.romashkaco.exceptions.ProductNotFoundException;
import com.goyanov.romashkaco.model.Product;
import com.goyanov.romashkaco.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductsService
{
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository)
    {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll()
    {
        return productsRepository.findAll();
    }

    public Product findById(long id)
    {
        return productsRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public void save(Product product)
    {
        productsRepository.save(product);
    }

    public void update(long id, Product product)
    {
        productsRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setId(id);
        productsRepository.save(product);
    }

    public void deleteById(long id)
    {
        if (!productsRepository.deleteById(id)) throw new ProductNotFoundException();
    }
}
