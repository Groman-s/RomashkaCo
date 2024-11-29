package com.goyanov.romashkaco.services;

import com.goyanov.romashkaco.exceptions.ProductNotFoundException;
import com.goyanov.romashkaco.model.Product;
import com.goyanov.romashkaco.model.dto.ProductDTO;
import com.goyanov.romashkaco.model.dto.mappers.ProductMapper;
import com.goyanov.romashkaco.repositories.ProductsRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public List<ProductDTO> findAll(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return productsRepository.findAll(pageable).stream().map(productMapper::toDTO).toList();
    }

    public List<ProductDTO> findAllWithFilters
                (String name, Boolean inStock, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable)
    {
        Specification<Product> specification = Specification.where(null);

        if (inStock != null)
        {
            specification = specification.and((Specification<Product>) (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("inStock"), inStock)
            );
        }

        if (name != null && !name.isBlank())
        {
            specification = specification.and((Specification<Product>) (root, query, criteriaBuilder)
                -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%")
            );
        }

        if (minPrice != null)
        {
            specification = specification.and((Specification<Product>) (root, query, criteriaBuilder)
                -> criteriaBuilder.greaterThan(root.get("price"), minPrice)
            );
        }

        if (maxPrice != null)
        {
            specification = specification.and((Specification<Product>) (root, query, criteriaBuilder)
                -> criteriaBuilder.lessThan(root.get("price"), maxPrice));
        }

        List<Product> products = productsRepository.findAll(specification, pageable).getContent();
        return products.stream().map(productMapper::toDTO).toList();
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
