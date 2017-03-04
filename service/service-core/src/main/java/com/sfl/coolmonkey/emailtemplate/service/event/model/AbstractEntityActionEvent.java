package com.sfl.coolmonkey.emailtemplate.service.event.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.context.ApplicationEvent;

/**
 * User: Valentina Sargsyan
 * Company: SFL LLC
 * Date: 17-3-2016
 * Time: 11:36
 */
public abstract class AbstractEntityActionEvent extends ApplicationEvent {
    private static final long serialVersionUID = -1908419338848663234L;

    //region Properties
    private final String uuid;
    //endregion

    //region Constructors
    public AbstractEntityActionEvent(final Object source, final String uuid) {
        super(source);
        this.uuid = uuid;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractEntityActionEvent)) {
            return false;
        }
        final AbstractEntityActionEvent that = (AbstractEntityActionEvent) o;
        return new EqualsBuilder()
                .append(uuid, that.uuid)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(uuid)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getUuid() {
        return uuid;
    }
    //endregion
}
