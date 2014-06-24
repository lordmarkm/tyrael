package com.tyrael.process.mgt.models.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.core.style.ToStringCreator;

import com.baldy.commons.models.BaseBaldyEntity;
import com.tyrael.process.mgt.models.product.Product;

@Entity(name = "ORDER_ITEM")
public class OrderItem extends BaseBaldyEntity {

    @ManyToOne(optional = false)
    private SalesOrder salesOrder;

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

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
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
