package com.tyrael.erp.ics.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.baldy.commons.models.BaseNamedEntity;

/**
 * @author mbmartinez
 */
@Entity(name = "LOCATION")
public class Location extends BaseNamedEntity {

    @OneToMany
    @JoinColumn(name = "PARENT_LOCATION_ID")
    private Location parent;

    public Location getParent() {
        return parent;
    }

    public void setParent(Location parent) {
        this.parent = parent;
    }

}
