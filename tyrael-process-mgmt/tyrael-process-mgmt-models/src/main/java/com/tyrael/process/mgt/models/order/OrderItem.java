package com.tyrael.process.mgt.models.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.core.style.ToStringCreator;

import com.baldy.commons.models.BaseBaldyEntity;
import com.tyrael.process.mgt.models.product.Product;

@MappedSuperclass
public abstract class OrderItem<E extends SalesOrder, F extends Product, W extends WorkOrder> extends BaseBaldyEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "SALES_ORDER_ID")
    private E salesOrder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    private F product;

    @ManyToOne
    @JoinColumn(name = "WORK_ORDER")
    private W workOrder;

    @Column
    private BigDecimal quantity = BigDecimal.ZERO;

    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("Sales order", salesOrder.getTrackingNo())
            .append("Product", product)
            .append("Quantity", quantity)
            .append("Work order", workOrder)
            .toString();
    }

    public E getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(E salesOrder) {
        this.salesOrder = salesOrder;
    }

    public F getProduct() {
        return product;
    }

    public void setProduct(F product) {
        this.product = product;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public W getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(W workOrder) {
        this.workOrder = workOrder;
    }

}
