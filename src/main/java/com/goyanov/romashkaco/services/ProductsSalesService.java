package com.goyanov.romashkaco.services;

import com.goyanov.romashkaco.repositories.ProductsSalesRepository;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsSalesService
{
    private final ProductsSalesRepository productsSalesRepository;
    private final Validator validator;

    @Autowired
    public ProductsSalesService(ProductsSalesRepository productsSalesRepository, Validator validator)
    {
        this.productsSalesRepository = productsSalesRepository;
        this.validator = validator;
    }
}
