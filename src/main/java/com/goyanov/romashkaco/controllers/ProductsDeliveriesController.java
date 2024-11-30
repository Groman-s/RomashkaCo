package com.goyanov.romashkaco.controllers;

import com.goyanov.romashkaco.model.ProductDelivery;
import com.goyanov.romashkaco.model.dto.ProductDeliveryDTO;
import com.goyanov.romashkaco.services.BaseCrudService;
import com.goyanov.romashkaco.services.ProductsDeliveriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class ProductsDeliveriesController extends BaseCrudController<ProductDelivery, Long, ProductDeliveryDTO>
{
    @GetMapping
    public List<ProductDeliveryDTO> getAllDeliveries
    (
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    )
    {
        return service.findAll(page, size);
    }

    @Autowired
    public ProductsDeliveriesController(BaseCrudService<ProductDelivery, Long, ProductDeliveryDTO> service)
    {
        super(service);
    }

    @Override
    public String getAddedMessage()
    {
        return "Документ поставки успешно оформлен!";
    }

    @Override
    public String getUpdatedMessage()
    {
        return "Документ поставки успешно обновлён!";
    }

    @Override
    public String getDeletedMessage()
    {
        return "Документ поставки успешно удалён!";
    }
}
