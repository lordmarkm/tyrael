package com.tyrael.erp.ics.models;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.baldy.commons.models.BaseEntity;
import com.tyrael.commons.models.Product;

/**
 * @author mbmartinez
 * @param <P>
 */
@MappedSuperclass
public abstract class Stock<P extends Product> extends BaseEntity {

    public abstract P getProduct();

    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
