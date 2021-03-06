package com.tyrael.commons.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.baldy.commons.models.BaseNamedEntity;

/**
 * 
 * @author Mark
 *
 */
@MappedSuperclass
public abstract class Product extends BaseNamedEntity {

    @Column(name = "UOM")
    private String unitOfMeasurement;

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

}
