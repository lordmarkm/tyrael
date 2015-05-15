package com.tyrael.commons.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.baldy.commons.models.BaseEntity;
import com.mysema.query.types.Predicate;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.commons.mapper.service.MappingService;

/**
 * @author mbmartinez
 * @param <E> Entity
 * @param <D> DTO
 */
public abstract class TyraelJpaServiceCustomImpl<E extends BaseEntity, D, R extends TyraelJpaService<E>>
    extends MappingService<E, D> {

    @Autowired
    protected R repo;

    public D findOneInfo(Long id) {
        return toDto(repo.findOne(id));
    }

    public D saveInfo(D dto) {
        return toDto(repo.save(toEntity(dto)));
    }

    public E saveInfoAndGetEntity(D dto) {
        return repo.save(toEntity(dto));
    }

    public List<D> saveInfo(Iterable<D> dtos) {
        return toDto(repo.save(toEntity(dtos)));
    }

    public List<E> saveInfoAndGetEntity(Iterable<D> dtos) {
        return repo.save(toEntity(dtos));
    }

    public D softDelete(Long id) {
        E entity = repo.findOne(id);
        if (null == entity) {
            return null;
        }
        entity.setDeleted(true);
        return toDto(repo.save(entity));
    }

    public PageInfo<D> pageInfo(Pageable page) {
        Page<E> results = repo.findAll(page);
        return toPageInfo(results);
    }

    public PageInfo<D> pageInfo(Predicate predicate, Pageable page) {
        Page<E> results = repo.findAll(predicate, page);
        return toPageInfo(results);
    }

    public PageInfo<D> toPageInfo(Page<E> page) {
        List<D> infos = toDto(page);

        PageInfo<D> pageResponse = new PageInfo<>();
        pageResponse.setData(infos);
        pageResponse.setTotal(page.getTotalElements());
        return pageResponse;
    }
}
