package com.tyrael.process.mgt.models.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.baldy.commons.models.BaseBaldyEntity;
import com.baldy.commons.models.proper.Person;
import com.tyrael.process.mgt.models.product.Product;

/**
 * @author Mark
 */
@Entity(name = "SALES_ORDER")
public class SalesOrder extends BaseBaldyEntity {

    @Column(name = "TRACKING_NO", unique = true, nullable = false)
    private String trackingNo;

    @OneToMany
    private Person customer;

    @OneToMany
    private Product product;

    @OneToMany
    private BigDecimal quantity;

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
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
