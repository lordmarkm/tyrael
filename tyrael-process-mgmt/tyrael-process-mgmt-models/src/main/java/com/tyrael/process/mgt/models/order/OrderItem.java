package com.tyrael.process.mgt.models.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.core.style.ToStringCreator;

import com.baldy.commons.models.BaseBaldyEntity;
import com.tyrael.process.mgt.models.product.Product;

@MappedSuperclass
public abstract class OrderItem<E extends SalesOrder> extends BaseBaldyEntity {

    @ManyToOne(optional = false)
    private E salesOrder;

    @ManyToOne(optional = false)
    private Product product;

    @Column
    private BigDecimal quantity = BigDecimal.ZERO;

    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("Sales order", salesOrder.getTrackingNo())
            .append("Product", product)
            .append("Quantity", quantity)
            .toString();
    }

    public E getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(E salesOrder) {
        this.salesOrder = salesOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

}
