package com.goyanov.romashkaco.services;

import com.goyanov.romashkaco.exceptions.not.found.EntityNotFoundException;
import com.goyanov.romashkaco.exceptions.not.found.ProductSaleNotFoundException;
import com.goyanov.romashkaco.model.ProductSale;
import com.goyanov.romashkaco.model.dto.ProductSaleDTO;
import com.goyanov.romashkaco.model.dto.mappers.ModelMapper;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductsSalesService extends BaseCrudService<ProductSale, Long, ProductSaleDTO>
{
    @Autowired
    public ProductsSalesService(JpaRepository<ProductSale, Long> repository, ModelMapper<ProductSale,
        ProductSaleDTO> modelMapper, Validator validator)
    {
        super(repository, modelMapper, validator);
    }

    @Override
    public EntityNotFoundException getThrowableEntityNotFoundException()
    {
        return new ProductSaleNotFoundException();
    }
}
