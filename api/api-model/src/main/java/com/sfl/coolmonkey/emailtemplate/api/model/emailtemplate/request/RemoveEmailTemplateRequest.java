package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request;

import com.sfl.coolmonkey.commons.api.model.request.AbstractCompanyUuidAwareRequestModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.ws.rs.QueryParam;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 11/25/15
 * Time: 11:29 AM
 */
public class RemoveEmailTemplateRequest extends AbstractCompanyUuidAwareRequestModel {
    private static final long serialVersionUID = 5090127415920690763L;

    //region Properties
    @QueryParam("uuid")
    private String uuid;
    //endregion

    //region Constructors
    public RemoveEmailTemplateRequest() {
    }

    public RemoveEmailTemplateRequest(final String companyUuid, final String uuid) {
        super(companyUuid);
        this.uuid = uuid;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RemoveEmailTemplateRequest)) {
            return false;
        }
        final RemoveEmailTemplateRequest that = (RemoveEmailTemplateRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(uuid, that.uuid)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
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

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }
    //endregion
}
