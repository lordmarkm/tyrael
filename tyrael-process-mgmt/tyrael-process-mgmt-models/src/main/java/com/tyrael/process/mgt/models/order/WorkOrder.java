package com.tyrael.process.mgt.models.order;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author Mark
 *
 */
@MappedSuperclass
public abstract class WorkOrder extends ProcessOrder {

    @Column(name = "TRACKING_NO", unique = true, nullable = false)
    private String trackingNo;

    @Column
    private Integer quantity;

    @Column
    private String status;

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
