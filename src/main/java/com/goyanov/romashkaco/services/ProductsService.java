package com.goyanov.romashkaco.services;

import com.goyanov.romashkaco.exceptions.ProductNotFoundException;
import com.goyanov.romashkaco.model.Product;
import com.goyanov.romashkaco.model.dto.ProductDTO;
import com.goyanov.romashkaco.model.dto.mappers.ProductMapper;
import com.goyanov.romashkaco.repositories.ProductsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductsService
{
    private final ProductsRepository productsRepository;
    private final ProductMapper productMapper;
    private final Validator validator;

    @Autowired
    public ProductsService(ProductsRepository productsRepository, ProductMapper productMapper, Validator validator)
    {
        this.productsRepository = productsRepository;
        this.productMapper = productMapper;
        this.validator = validator;
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
        Product product = productMapper.toEntity(productDTO);
        Set<ConstraintViolation<Product>> validate = validator.validate(product);
        if (!validate.isEmpty())
        {
            throw new ConstraintViolationException(validate);
        }
        productsRepository.save(product);
    }

    public void update(long id, ProductDTO productDTO)
    {
        Product existing = productsRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productMapper.copyProperties(productDTO, existing);
        Set<ConstraintViolation<Product>> validate = validator.validate(existing);
        if (!validate.isEmpty())
        {
            throw new ConstraintViolationException(validate);
        }
        productsRepository.save(existing);
    }

    public void deleteById(long id)
    {
        productsRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productsRepository.deleteById(id);
    }
}
