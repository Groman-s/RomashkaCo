package com.goyanov.romashkaco.model.dto.mappers;

import com.goyanov.romashkaco.model.ProductSale;
import com.goyanov.romashkaco.model.dto.ProductSaleDTO;
import com.goyanov.romashkaco.services.ProductsSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductSaleMapper implements ModelMapper<ProductSale, ProductSaleDTO>
{
    private final ProductsSalesService productsSalesService;

    @Autowired
    public ProductSaleMapper(ProductsSalesService productsSalesService)
    {
        this.productsSalesService = productsSalesService;
    }

    @Override
    public ProductSale toEntity(ProductSaleDTO dto)
    {
        ProductSale productSale = new ProductSale();
        copyProperties(dto, productSale);
        return null;
    }

    @Override
    public void copyProperties(ProductSaleDTO from, ProductSale to)
    {
        if (from.getAmount() != null) to.setAmount(from.getAmount());
        if (from.getSalePrice() != null) to.setSalePrice(from.getSalePrice());
        if (from.getDocumentName() != null) to.setDocumentName(from.getDocumentName());
//        if (from.getProductId() != null) to.setProduct(productsSalesService.findById(from.getProductId()).orElse(null)); TODO реализовать
    }

    @Override
    public ProductSaleDTO toDTO(ProductSale entity)
    {
        return new ProductSaleDTO(entity);
    }
}
