package com.sfl.coolmonkey.emailtemplate.service.common.dto;

import com.sfl.coolmonkey.emailtemplate.service.common.model.AbstractDomainEntityModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.MutableDateTime;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/14/15
 * Time: 2:16 PM
 */
public abstract class AbstractDomainEntityModelDto<T extends AbstractDomainEntityModel> implements Serializable {
    private static final long serialVersionUID = 994996008376762751L;

    //region Constructors
    public AbstractDomainEntityModelDto() {
    }
    //endregion

    //region Utility methods
    public static Date cloneDateIfNotNull(final Date date) {
        if (date == null) {
            return null;
        }
        final MutableDateTime mutableDateTime = new MutableDateTime(date);
        return mutableDateTime.toDate();
    }
    //endregion

    public static Double getDoubleValueOrNull(final BigDecimal value) {
        return value != null ? value.doubleValue() : null;
    }

    //region Abstract methods
    public abstract void updateDomainEntityProperties(final T domainEntity);
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractDomainEntityModelDto)) {
            return false;
        }
        final EqualsBuilder builder = new EqualsBuilder();
        return builder.build();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        return builder.build();
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);
        return builder.build();
    }
    //endregion
}
