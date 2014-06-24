package com.tyrael.process.mgt.services.order;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyrael.process.mgt.models.order.WorkOrder;

/**
 * @author Mark
 */
public interface WorkOrderService<E extends WorkOrder, I extends Serializable>
    extends JpaRepository<E, I> {

}
