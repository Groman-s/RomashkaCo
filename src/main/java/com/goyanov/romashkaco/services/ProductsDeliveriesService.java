package com.goyanov.romashkaco.services;

import com.goyanov.romashkaco.repositories.ProductsDeliveriesRepository;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsDeliveriesService
{
    private final ProductsDeliveriesRepository productsDeliveriesRepository;
    private final Validator validator;

    @Autowired
    public ProductsDeliveriesService(ProductsDeliveriesRepository productsDeliveriesRepository, Validator validator)
    {
        this.productsDeliveriesRepository = productsDeliveriesRepository;
        this.validator = validator;
    }
}
