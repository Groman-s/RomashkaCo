package com.goyanov.romashkaco.services;

import com.goyanov.romashkaco.exceptions.not.found.EntityNotFoundException;
import com.goyanov.romashkaco.exceptions.not.found.ProductDeliveryNotFound;
import com.goyanov.romashkaco.model.ProductDelivery;
import com.goyanov.romashkaco.model.dto.ProductDeliveryDTO;
import com.goyanov.romashkaco.model.dto.mappers.ModelMapper;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductsDeliveriesService extends BaseCrudService<ProductDelivery, Long, ProductDeliveryDTO>
{
    @Autowired
    public ProductsDeliveriesService(JpaRepository<ProductDelivery, Long> repository, ModelMapper<ProductDelivery,
        ProductDeliveryDTO> modelMapper, Validator validator)
    {
        super(repository, modelMapper, validator);
    }

    @Override
    public EntityNotFoundException getThrowableEntityNotFoundException()
    {
        return new ProductDeliveryNotFound();
    }
}
