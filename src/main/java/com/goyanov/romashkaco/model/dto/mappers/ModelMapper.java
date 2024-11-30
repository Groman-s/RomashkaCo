package com.goyanov.romashkaco.model.dto.mappers;

public interface ModelMapper<E, D>
{
    E toEntity(D dto);

    void copyProperties(D from, E to);

    D toDTO(E entity);
}
