package com.sfl.coolmonkey.emailtemplate.service.common.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/20/15
 * Time: 3:37 PM
 */
public abstract class AbstractDomainUuidAwareEntityModel extends AbstractDomainEntityModel {

    private static final long serialVersionUID = -155162216038286391L;

    //region Properties
    @Field("uuid")
    @Indexed(unique = true)
    private final String uuid;
    //endregion

    //region Constructors
    public AbstractDomainUuidAwareEntityModel() {
        this(UUID.randomUUID().toString());
    }

    public AbstractDomainUuidAwareEntityModel(final String uuid) {
        super();
        Assert.hasText(uuid, "Uuid should not be empty");
        this.uuid = uuid;
    }
    //endregion

    //region Utility methods
    public static String getUuidOrNull(final AbstractDomainUuidAwareEntityModel entity) {
        return entity != null ? entity.getUuid() : null;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractDomainUuidAwareEntityModel)) {
            return false;
        }
        final AbstractDomainUuidAwareEntityModel that = (AbstractDomainUuidAwareEntityModel) o;
        final EqualsBuilder builder = new EqualsBuilder();
        builder.appendSuper(super.equals(that));
        builder.append(this.getUuid(), that.getUuid());
        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        builder.appendSuper(super.hashCode());
        builder.append(this.getUuid());
        return builder.build();
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);
        builder.appendSuper(super.toString());
        builder.append("uuid", this.getUuid());
        return builder.build();
    }
    //endregion

    //region Properties getters and setters
    public String getUuid() {
        return uuid;
    }
    //endregion
}
