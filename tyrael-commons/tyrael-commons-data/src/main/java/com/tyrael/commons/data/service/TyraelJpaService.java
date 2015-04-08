package com.tyrael.commons.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * @author mbmartinez
 * @param <E> the entity
 */
public interface TyraelJpaService<E> extends JpaRepository<E, Long>, QueryDslPredicateExecutor<E> {

}
