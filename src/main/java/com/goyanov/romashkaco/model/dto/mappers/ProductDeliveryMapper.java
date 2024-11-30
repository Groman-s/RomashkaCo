package com.goyanov.romashkaco.model.dto.mappers;

import com.goyanov.romashkaco.model.ProductDelivery;
import com.goyanov.romashkaco.model.dto.ProductDeliveryDTO;
import com.goyanov.romashkaco.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDeliveryMapper implements ModelMapper<ProductDelivery, ProductDeliveryDTO>
{
    private final ProductsService productsService;

    @Autowired
    public ProductDeliveryMapper(ProductsService productsService)
    {
        this.productsService = productsService;
    }

    @Override
    public ProductDelivery toEntity(ProductDeliveryDTO dto)
    {
        ProductDelivery productDelivery = new ProductDelivery();
        copyProperties(dto, productDelivery);
        return productDelivery;
    }

    @Override
    public void copyProperties(ProductDeliveryDTO from, ProductDelivery to)
    {
        if (from.getAmount() != null) to.setAmount(from.getAmount());
        if (from.getDocumentName() != null) to.setDocumentName(from.getDocumentName());
        if (from.getProductArticle() != null) to.setProduct(productsService.findByArticle(from.getProductArticle()));
    }

    @Override
    public ProductDeliveryDTO toDTO(ProductDelivery entity)
    {
        return new ProductDeliveryDTO(entity);
    }
}
