package com.tyrael.process.mgt.models.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.springframework.core.style.ToStringCreator;

import com.baldy.commons.models.proper.Person;

/**
 * @author Mark
 */
@MappedSuperclass
public abstract class SalesOrder<E extends OrderItem, P extends Person> extends ProcessOrder {

    @Column(name = "TRACKING_NO", unique = true, nullable = false)
    private String trackingNo;

    @Column(name = "PURCHASE_ORDER_NO")
    private String purchaseOrderNo;

    @Column(name = "SHIP_TO")
    private String shipTo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CUSTOMER_ID")
    private P customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="salesOrder")
    private List<E> items;

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

    public P getCustomer() {
        return customer;
    }

    public void setCustomer(P customer) {
        this.customer = customer;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public List<E> getItems() {
        return items;
    }

    public void setItems(List<E> items) {
        this.items = items;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }

}
