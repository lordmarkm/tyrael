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
public abstract class OrderItem<S extends SalesOrder, P extends Product, W extends WorkOrder> extends BaseBaldyEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "SALES_ORDER_ID")
    private S salesOrder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    private P product;

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

    public S getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(S salesOrder) {
        this.salesOrder = salesOrder;
    }

    public P getProduct() {
        return product;
    }

    public void setProduct(P product) {
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
