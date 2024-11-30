package com.goyanov.romashkaco.model.dto.mappers;

import com.goyanov.romashkaco.exceptions.not.found.ProductByArticleNotFoundException;
import com.goyanov.romashkaco.model.ProductDelivery;
import com.goyanov.romashkaco.model.dto.ProductDeliveryDTO;
import com.goyanov.romashkaco.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDeliveryMapper implements ModelMapper<ProductDelivery, ProductDeliveryDTO>
{
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductDeliveryMapper(ProductsRepository productsRepository)
    {
        this.productsRepository = productsRepository;
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
        if (from.getProductArticle() != null)
            to.setProduct(productsRepository.findByArticle(from.getProductArticle()).
                    orElseThrow(ProductByArticleNotFoundException::new));
    }

    @Override
    public ProductDeliveryDTO toDTO(ProductDelivery entity)
    {
        return new ProductDeliveryDTO(entity);
    }
}
