package com.tyrael.process.mgt.services.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyrael.process.mgt.models.order.WorkOrder;

/**
 * 
 * @author Mark
 *
 */
public interface WorkOrderService extends JpaRepository<WorkOrder, Long> {

}
