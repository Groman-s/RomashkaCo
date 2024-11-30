package com.goyanov.romashkaco.model.dto.mappers;

import com.goyanov.romashkaco.model.ProductSale;
import com.goyanov.romashkaco.model.dto.ProductSaleDTO;
import com.goyanov.romashkaco.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductSaleMapper implements ModelMapper<ProductSale, ProductSaleDTO>
{
    private final ProductsService productsService;

    @Autowired
    public ProductSaleMapper(ProductsService productsService)
    {
        this.productsService = productsService;
    }

    @Override
    public ProductSale toEntity(ProductSaleDTO dto)
    {
        ProductSale productSale = new ProductSale();
        copyProperties(dto, productSale);
        return productSale;
    }

    @Override
    public void copyProperties(ProductSaleDTO from, ProductSale to)
    {
        if (from.getAmount() != null) to.setAmount(from.getAmount());
        if (from.getSalePrice() != null) to.setSalePrice(from.getSalePrice());
        if (from.getDocumentName() != null) to.setDocumentName(from.getDocumentName());
        if (from.getProductArticle() != null) to.setProduct(productsService.findByArticle(from.getProductArticle()));
    }

    @Override
    public ProductSaleDTO toDTO(ProductSale entity)
    {
        return new ProductSaleDTO(entity);
    }
}
