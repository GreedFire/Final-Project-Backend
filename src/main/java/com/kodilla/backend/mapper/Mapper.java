package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.Dto;
import com.kodilla.backend.domain.entity.Entity;

import java.util.List;

public interface Mapper <T extends Entity, S extends Dto > {

    T mapToEntity(S dto);

    S mapToDto(T entity);

    List<T> mapToEntityList(List<S> dto);

    List<S> mapToDtoList(List<T> dto);
}
