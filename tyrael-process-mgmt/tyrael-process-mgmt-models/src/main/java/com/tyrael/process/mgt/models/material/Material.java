package com.tyrael.process.mgt.models.material;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.baldy.commons.models.BaseBaldyEntity;

@Entity(name = "MATERIAL")
public class Material extends BaseBaldyEntity {

    @Column(name = "UOM")
    private String unitOfMeasurement;

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

}
