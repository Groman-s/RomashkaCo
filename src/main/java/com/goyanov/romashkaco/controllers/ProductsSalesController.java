package com.goyanov.romashkaco.controllers;

import com.goyanov.romashkaco.model.ProductSale;
import com.goyanov.romashkaco.model.dto.ProductSaleDTO;
import com.goyanov.romashkaco.services.BaseCrudService;
import com.goyanov.romashkaco.services.ProductsSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sales")
public class ProductsSalesController extends BaseCrudController<ProductSale, Long, ProductSaleDTO>
{
    @Autowired
    public ProductsSalesController(BaseCrudService<ProductSale, Long, ProductSaleDTO> service)
    {
        super(service);
    }

    @Override
    public String getAddedMessage()
    {
        return "Документ продажи успешно оформлен!";
    }

    @Override
    public String getUpdatedMessage()
    {
        return "Документ продажи успешно обновлён!";
    }

    @Override
    public String getDeletedMessage()
    {
        return "Документ продажи успешно удалён!";
    }
}
