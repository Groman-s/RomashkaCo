package com.goyanov.romashkaco.services;

import com.goyanov.romashkaco.exceptions.not.found.EntityNotFoundException;
import com.goyanov.romashkaco.model.Product;
import com.goyanov.romashkaco.model.dto.mappers.ModelMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public abstract class BaseCrudService<ENT, ID, DTO>
{
    protected final JpaRepository<ENT, ID> repository;
    protected final ModelMapper<ENT, DTO> modelMapper;
    protected final Validator validator;

    public BaseCrudService(JpaRepository<ENT, ID> repository, ModelMapper<ENT, DTO> modelMapper, Validator validator)
    {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    public abstract EntityNotFoundException getThrowableEntityNotFoundException();

    public List<DTO> findAll(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).stream().map(modelMapper::toDTO).toList();
    }

    public DTO findById(ID id)
    {
        return modelMapper.toDTO(repository.findById(id).orElseThrow(this::getThrowableEntityNotFoundException));
    }

    public void save(DTO dto)
    {
        ENT entity = modelMapper.toEntity(dto);
        Set<ConstraintViolation<ENT>> validate = validator.validate(entity);
        if (!validate.isEmpty())
        {
            throw new ConstraintViolationException(validate);
        }
        repository.save(entity);
    }

    public void update(ID id, DTO productDTO)
    {
        ENT existing = repository.findById(id).orElseThrow(this::getThrowableEntityNotFoundException);
        modelMapper.copyProperties(productDTO, existing);
        Set<ConstraintViolation<ENT>> validate = validator.validate(existing);
        if (!validate.isEmpty())
        {
            throw new ConstraintViolationException(validate);
        }
        repository.save(existing);
    }

    public void deleteById(ID id)
    {
        repository.findById(id).orElseThrow(this::getThrowableEntityNotFoundException);
        repository.deleteById(id);
    }
}
