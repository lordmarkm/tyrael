package com.tyrael.process.mgt.models.order;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.baldy.commons.models.BaseBaldyEntity;

/**
 * 
 * @author Mark
 *
 */
@MappedSuperclass
public abstract class ProcessOrder extends BaseBaldyEntity {

    @Column(name = "DATE_CREATED")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateCreated;

    @Column(name = "DATE_COMPLETED")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateCompleted;

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(DateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public DateTime getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(DateTime dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

}
