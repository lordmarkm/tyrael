package com.tyrael.commons.dto;

import org.springframework.core.style.ToStringCreator;

/**
 * @author mbmartinez
 */
public class BaseTyraelNamedDto extends BaseTyraelDto {

    protected String name;
    protected String description;

    protected ToStringCreator toStringCreator() {
        return super.toStringCreator()
            .append("name", name)
            .append("description", description);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
