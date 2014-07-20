package com.tyrael.process.mgt.models.process;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.baldy.commons.models.BaseEntity;
import com.baldy.commons.models.proper.Person;
import com.tyrael.process.mgt.models.order.WorkOrder;

/**
 * @author mbmartinez
 */
@MappedSuperclass
public abstract class Process<P extends Person, W extends WorkOrder> extends BaseEntity {

    @Column(name = "STARTED")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateStarted;

    @Column(name = "ENDED")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateCompleted;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ACTOR_ID")
    private P actor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "WORK_ORDER_ID")
    private W workOrder;

    public DateTime getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(DateTime dateStarted) {
        this.dateStarted = dateStarted;
    }

    public DateTime getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(DateTime dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public P getActor() {
        return actor;
    }

    public void setActor(P actor) {
        this.actor = actor;
    }

    public W getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(W workOrder) {
        this.workOrder = workOrder;
    }
}
