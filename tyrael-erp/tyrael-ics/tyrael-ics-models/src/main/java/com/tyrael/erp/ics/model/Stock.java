package com.tyrael.erp.ics.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author mbmartinez
 * @param <P>
 */
public abstract class Stock<P extends Product> {

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
