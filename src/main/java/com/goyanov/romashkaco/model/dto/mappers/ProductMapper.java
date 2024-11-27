package com.goyanov.romashkaco.model.dto.mappers;

import com.goyanov.romashkaco.model.Product;
import com.goyanov.romashkaco.model.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper
{
    public Product toEntity(ProductDTO productDTO)
    {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setInStock(productDTO.getInStock());
        return product;
    }

    public ProductDTO toDTO(Product product)
    {
        return new ProductDTO(product);
    }
}
