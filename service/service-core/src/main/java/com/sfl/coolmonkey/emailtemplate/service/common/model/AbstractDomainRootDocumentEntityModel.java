package com.sfl.coolmonkey.emailtemplate.service.common.model;

import com.sfl.coolmonkey.emailtemplate.service.common.util.DateUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/14/15
 * Time: 1:53 PM
 */
public abstract class AbstractDomainRootDocumentEntityModel extends AbstractDomainEntityModel {

    private static final long serialVersionUID = -1152667704652945755L;

    //region Properties
    @Id
    private ObjectId id;

    @Field("created")
    @Indexed
    private Date created;

    @Field("updated")
    @Indexed
    private Date updated;

    @Field("removed")
    @Indexed
    private Date removed;
    //endregion

    //region Constructors
    public AbstractDomainRootDocumentEntityModel() {
        created = new Date();
        updated = DateUtils.cloneDateIfNotNull(created);
    }
    //endregion

    //region Utility methods
    public static ObjectId getIdOrNull(final AbstractDomainRootDocumentEntityModel entity) {
        return entity != null ? entity.getId() : null;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractDomainRootDocumentEntityModel)) {
            return false;
        }
        final AbstractDomainRootDocumentEntityModel that = (AbstractDomainRootDocumentEntityModel) o;
        final EqualsBuilder builder = new EqualsBuilder();
        builder.appendSuper(super.equals(that));
        builder.append(getId(), that.getId());
        builder.append(this.getCreated(), that.getCreated());
        builder.append(this.getUpdated(), that.getUpdated());
        builder.append(this.getRemoved(), that.getRemoved());
        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        builder.appendSuper(super.hashCode());
        builder.append(getId());
        builder.append(getCreated());
        builder.append(getUpdated());
        builder.append(getRemoved());
        return builder.build();
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);
        builder.appendSuper(super.toString());
        builder.append("id", getId());
        builder.append("created", getCreated());
        builder.append("updated", getUpdated());
        builder.append("removed", getRemoved());
        return builder.build();
    }
    //endregion

    //region Properties getters and setters
    public ObjectId getId() {
        return id;
    }

    public void setId(final ObjectId id) {
        this.id = id;
    }

    public Date getCreated() {
        return DateUtils.cloneDateIfNotNull(created);
    }

    public void setCreated(final Date created) {
        this.created = DateUtils.cloneDateIfNotNull(created);
    }

    public Date getUpdated() {
        return DateUtils.cloneDateIfNotNull(updated);
    }

    public void setUpdated(final Date updated) {
        this.updated = DateUtils.cloneDateIfNotNull(updated);
    }

    public Date getRemoved() {
        return DateUtils.cloneDateIfNotNull(removed);
    }

    public void setRemoved(final Date removed) {
        this.removed = DateUtils.cloneDateIfNotNull(removed);
    }
    //endregion
}
