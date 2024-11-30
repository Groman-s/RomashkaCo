package com.goyanov.romashkaco.services;

import com.goyanov.romashkaco.exceptions.not.found.ProductByArticleNotFoundException;
import com.goyanov.romashkaco.exceptions.not.found.ProductByIdNotFoundException;
import com.goyanov.romashkaco.exceptions.not.found.EntityNotFoundException;
import com.goyanov.romashkaco.model.Product;
import com.goyanov.romashkaco.model.dto.ProductDTO;
import com.goyanov.romashkaco.model.dto.mappers.ModelMapper;
import com.goyanov.romashkaco.repositories.ProductsRepository;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductsService extends BaseCrudService<Product, Long, ProductDTO>
{
    @Autowired
    public ProductsService
        (JpaRepository<Product, Long> repository, ModelMapper<Product, ProductDTO> modelMapper, Validator validator)
    {
        super(repository, modelMapper, validator);
    }

    @Override
    public EntityNotFoundException getThrowableEntityNotFoundException()
    {
        return new ProductByIdNotFoundException();
    }

    public List<ProductDTO> findAllWithFilters
    (
        int page, int size, String name, Boolean inStock,
        BigDecimal minPrice, BigDecimal maxPrice, String orderBy, String direction
    )
    {
        Specification<Product> specification = Specification.where(null);

        if (!orderBy.equals("name") && !orderBy.equals("price"))
        {
            orderBy = "name";
        }

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

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), orderBy));
        List<Product> products = ((JpaSpecificationExecutor<Product>)repository).findAll(specification, pageable).getContent();
        return products.stream().map(modelMapper::toDTO).toList();
    }

    public Product findByArticle(Long article)
    {
        return ((ProductsRepository)repository).findByArticle(article).orElseThrow(ProductByArticleNotFoundException::new);
    }
}
