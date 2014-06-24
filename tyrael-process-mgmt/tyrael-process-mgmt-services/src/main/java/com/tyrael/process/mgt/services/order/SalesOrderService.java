package com.tyrael.process.mgt.services.order;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyrael.process.mgt.models.order.SalesOrder;

/**
 * @author Mark
 */
public interface SalesOrderService<E extends SalesOrder, I extends Serializable>
    extends JpaRepository<E, I> {

}
