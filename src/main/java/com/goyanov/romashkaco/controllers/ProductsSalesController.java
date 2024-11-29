package com.goyanov.romashkaco.controllers;

import com.goyanov.romashkaco.services.ProductsSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sales")
public class ProductsSalesController
{
    private final ProductsSalesService productsSalesService;

    @Autowired
    public ProductsSalesController(ProductsSalesService productsSalesService)
    {
        this.productsSalesService = productsSalesService;
    }
}
