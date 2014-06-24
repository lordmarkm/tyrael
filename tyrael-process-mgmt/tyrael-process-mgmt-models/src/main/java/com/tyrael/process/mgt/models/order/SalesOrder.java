package com.tyrael.process.mgt.models.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.core.style.ToStringCreator;

import com.baldy.commons.models.proper.Person;

/**
 * @author Mark
 */
@Entity(name = "SALES_ORDER")
public class SalesOrder extends ProcessOrder {

    @Column(name = "TRACKING_NO", unique = true, nullable = false)
    private String trackingNo;

    @Column
    private String purchaseOrderNo;

    @ManyToOne(optional = false)
    private Person customer;

    @Column(name = "SHIP_TO")
    private String shipTo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("Tracking no", trackingNo)
            .append("Purchase Order no", purchaseOrderNo)
            .append("Ship to", shipTo)
            .append("Customer", customer)
            .append("Items", items)
            .toString();
    }
    
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

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }

}
