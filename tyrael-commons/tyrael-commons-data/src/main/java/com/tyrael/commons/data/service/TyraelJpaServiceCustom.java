package com.tyrael.commons.data.service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.baldy.commons.models.BaseEntity;
import com.tyrael.commons.dto.PageInfo;

/**
 * @author mbmartinez
 * @param <E> Entity
 * @param <D> DTO
 */
@Transactional
public interface TyraelJpaServiceCustom<E extends BaseEntity, D> {

    D findOneInfo(Long id);
    D saveInfo(D dto);
    D softDelete(Long id);

    PageInfo<D> pageInfo(Pageable page);

}
