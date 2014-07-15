package com.tyrael.process.mgt.models.material;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.baldy.commons.models.BaseBaldyEntity;


@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MaterialStack<M extends Material> extends BaseBaldyEntity {

    @OneToOne
    @JoinColumn(name = "MATERIAL")
    private M material;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    public M getMaterial() {
        return material;
    }

    public void setMaterial(M material) {
        this.material = material;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
