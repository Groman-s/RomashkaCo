package com.goyanov.romashkaco.controllers;

import com.goyanov.romashkaco.services.ProductsDeliveriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deliveries")
public class ProductsDeliveriesController
{
    private final ProductsDeliveriesService productsDeliveriesService;

    @Autowired
    public ProductsDeliveriesController(ProductsDeliveriesService productsDeliveriesService)
    {
        this.productsDeliveriesService = productsDeliveriesService;
    }
}
