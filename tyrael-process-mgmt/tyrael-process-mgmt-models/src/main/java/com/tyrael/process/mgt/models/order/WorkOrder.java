package com.tyrael.process.mgt.models.order;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.core.style.ToStringCreator;

/**
 * 
 * @author Mark
 *
 */
@MappedSuperclass
public abstract class WorkOrder<E extends SalesOrder> extends ProcessOrder {

    @Column(name = "TRACKING_NO", unique = true, nullable = false)
    private String trackingNo;
    
    @ManyToOne
    private E salesOrder;

    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("Tracking no.", trackingNo)
            .append("Sales order", salesOrder)
            .toString();
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public E getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(E salesOrder) {
        this.salesOrder = salesOrder;
    }
}
