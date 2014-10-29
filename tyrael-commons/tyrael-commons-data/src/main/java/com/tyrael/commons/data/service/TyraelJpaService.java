package com.tyrael.commons.data.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.baldy.commons.models.BaseEntity;
import com.tyrael.commons.dto.PageInfo;

/**
 * @author mbmartinez
 * @param <E> Entity
 * @param <D> DTO
 */
public interface TyraelJpaService<E extends BaseEntity, D> extends JpaRepository<E, Long> {

    D findOneInfo(Long id);
    D saveInfo(D dto);
    D softDelete(Long id);

    PageInfo<D> pageInfo(Pageable page);

}
