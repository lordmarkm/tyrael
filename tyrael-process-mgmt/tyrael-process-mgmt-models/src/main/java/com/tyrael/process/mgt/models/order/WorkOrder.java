package com.tyrael.process.mgt.models.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.core.style.ToStringCreator;

/**
 * 
 * @author Mark
 *
 */
@Entity(name = "WORK_ORDER")
public class WorkOrder extends ProcessOrder {

    @Column(name = "TRACKING_NO", unique = true, nullable = false)
    private String trackingNo;
    
    @ManyToOne
    private SalesOrder salesOrder;

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

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }
}
