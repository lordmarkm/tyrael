package com.tyrael.process.mgt.models.order;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.process.mgt.models.product.Product;

/**
 * 
 * @author Mark
 *
 */
@MappedSuperclass
public abstract class WorkOrder<E extends SalesOrder, O extends OrderItem> extends ProcessOrder {

    @Column(name = "TRACKING_NO", unique = true, nullable = false)
    private String trackingNo;
    
    @ManyToOne
    private E salesOrder;

    @OneToOne
    private O orderItem;

    @Column
    private Integer quantity;

    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("Tracking no.", trackingNo)
            .append("Sales order", salesOrder)
            .append("Product", orderItem)
            .append("Quantity", quantity)
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public O getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(O orderItem) {
        this.orderItem = orderItem;
    }
}
