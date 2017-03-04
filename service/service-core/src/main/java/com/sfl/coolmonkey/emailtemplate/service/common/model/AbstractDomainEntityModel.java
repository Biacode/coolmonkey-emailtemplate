package com.sfl.coolmonkey.emailtemplate.service.common.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/14/15
 * Time: 1:52 PM
 */
public abstract class AbstractDomainEntityModel extends AbstractBaseEntityModel {

    private static final long serialVersionUID = -497149704382224305L;

    //region Constructors
    public AbstractDomainEntityModel() {
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractDomainEntityModel)) {
            return false;
        }
        final EqualsBuilder builder = new EqualsBuilder();
        return builder.isEquals();
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
