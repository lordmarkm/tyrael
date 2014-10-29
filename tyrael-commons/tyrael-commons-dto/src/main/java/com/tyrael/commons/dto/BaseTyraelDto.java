package com.tyrael.commons.dto;

import org.springframework.core.style.ToStringCreator;

/**
 * @author mbmartinez
 */
public abstract class BaseTyraelDto {

    protected Long id;
    protected boolean deleted;

    @Override
    public String toString() {
        return this.toStringCreator().toString();
    }
    protected ToStringCreator toStringCreator() {
        return new ToStringCreator(this)
            .append("id", id)
            .append("deleted", deleted);
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
