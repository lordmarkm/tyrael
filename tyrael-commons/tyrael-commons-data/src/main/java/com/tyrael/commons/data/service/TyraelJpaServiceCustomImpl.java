package com.tyrael.commons.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.baldy.commons.models.BaseEntity;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.commons.mapper.service.MappingService;

/**
 * @author mbmartinez
 * @param <E> Entity
 * @param <D> DTO
 */
public abstract class TyraelJpaServiceCustomImpl<E extends BaseEntity, D, R extends JpaRepository<E, Long>>
    extends MappingService<E, D> {

    @Autowired
    protected R repo;

    public D findOneInfo(Long id) {
        return toDto(repo.findOne(id));
    }

    public D saveInfo(D dto) {
        return toDto(repo.save(toEntity(dto)));
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
        List<D> infos = toDto(results);

        PageInfo<D> pageResponse = new PageInfo<>();
        pageResponse.setData(infos);
        pageResponse.setTotal(results.getTotalElements());
        return pageResponse;
    }

}
