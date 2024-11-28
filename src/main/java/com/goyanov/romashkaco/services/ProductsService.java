package com.goyanov.romashkaco.services;

import com.goyanov.romashkaco.exceptions.ProductNotFoundException;
import com.goyanov.romashkaco.model.Product;
import com.goyanov.romashkaco.model.dto.ProductDTO;
import com.goyanov.romashkaco.model.dto.mappers.ProductMapper;
import com.goyanov.romashkaco.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService
{
    private final ProductsRepository productsRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductsService(ProductsRepository productsRepository, ProductMapper productMapper)
    {
        this.productsRepository = productsRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> findAll()
    {
        return productsRepository.findAll().stream().map(productMapper::toDTO).toList();
    }

    public ProductDTO findById(long id)
    {
        return productMapper.toDTO(productsRepository.findById(id).orElseThrow(ProductNotFoundException::new));
    }

    public void save(ProductDTO productDTO)
    {
        productsRepository.save(productMapper.toEntity(productDTO));
    }

    public void update(long id, ProductDTO productDTO)
    {
        productsRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        Product product = productMapper.toEntity(productDTO);
        product.setId(id);
        productsRepository.save(product);
    }

    public void deleteById(long id)
    {
        productsRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productsRepository.deleteById(id);
    }
}
