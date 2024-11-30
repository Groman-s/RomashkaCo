package com.goyanov.romashkaco.model.dto.mappers;

public interface ModelMapper<E, D>
{
    E toEntity(D dto);

    D toDto(E entity);

    void copyProperties(D from, E to);
}
