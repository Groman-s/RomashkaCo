package com.goyanov.romashkaco.model.dto.mappers;

import com.goyanov.romashkaco.model.ProductSale;
import com.goyanov.romashkaco.model.dto.ProductSaleDTO;
import com.goyanov.romashkaco.repositories.ProductsRepository;
import com.goyanov.romashkaco.repositories.ProductsSalesRepository;
import com.goyanov.romashkaco.services.ProductsSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductSaleMapper implements ModelMapper<ProductSale, ProductSaleDTO>
{
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductSaleMapper(ProductsRepository productsRepository)
    {
        this.productsRepository = productsRepository;
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
        if (from.getProductId() != null) to.setProduct(productsRepository.findById(from.getProductId()).orElse(null));
    }

    @Override
    public ProductSaleDTO toDTO(ProductSale entity)
    {
        return new ProductSaleDTO(entity);
    }
}
