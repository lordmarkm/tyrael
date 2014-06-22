package com.tyrael.process.mgt.services.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyrael.process.mgt.models.order.SalesOrder;

/**
 * 
 * @author Mark
 *
 */
public interface SalesOrderService extends JpaRepository<SalesOrder, Long> {

}
