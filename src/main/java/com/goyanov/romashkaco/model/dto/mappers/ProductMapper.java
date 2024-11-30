package com.goyanov.romashkaco.model.dto.mappers;

import com.goyanov.romashkaco.model.Product;
import com.goyanov.romashkaco.model.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements ModelMapper<Product, ProductDTO>
{
    public Product toEntity(ProductDTO productDTO)
    {
        Product product = new Product();
        copyProperties(productDTO, product);
        return product;
    }

    public void copyProperties(ProductDTO from, Product to)
    {
        if (from.getName() != null) to.setName(from.getName());
        if (from.getDescription() != null) to.setDescription(from.getDescription());
        if (from.getPrice() != null) to.setPrice(from.getPrice());
        if (from.getInStock() != null) to.setInStock(from.getInStock());
    }

    public ProductDTO toDto(Product product)
    {
        return new ProductDTO(product);
    }
}
