package com.tyrael.commons.mapper.service;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import com.google.common.collect.Lists;

/**
 * @author Mark
 *
 * @param <E>
 * @param <D>
 */
public abstract class MappingService<E, D> {

    @Autowired
    private Mapper mapper;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    public MappingService() {
        Class<?>[] genericTypes = GenericTypeResolver.resolveTypeArguments(getClass(), MappingService.class);
        entityClass = (Class<E>) genericTypes[0];
        dtoClass = (Class<D>) genericTypes[1];
    }

    protected D toDto(E entity) {
        return mapper.map(entity, dtoClass);
    }

    protected E toEntity(D dto) {
        return mapper.map(dto, entityClass);
    }

    protected List<D> toDto(Iterable<E> entities) {
        List<D> dtos = Lists.newArrayList();
        for (E entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
}
