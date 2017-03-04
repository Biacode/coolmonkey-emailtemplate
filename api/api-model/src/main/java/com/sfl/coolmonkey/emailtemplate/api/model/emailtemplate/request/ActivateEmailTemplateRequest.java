package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.request.AbstractCompanyUuidAwareRequestModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 10/8/15
 * Time: 7:52 PM
 */
public class ActivateEmailTemplateRequest extends AbstractCompanyUuidAwareRequestModel {
    private static final long serialVersionUID = -7836752532411477584L;

    //region Properties
    @JsonProperty("id")
    private String uuid;
    //endregion

    //region Constructors
    public ActivateEmailTemplateRequest() {
    }

    public ActivateEmailTemplateRequest(final String companyUuid, final String uuid) {
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
        if (!(o instanceof ActivateEmailTemplateRequest)) {
            return false;
        }
        final ActivateEmailTemplateRequest that = (ActivateEmailTemplateRequest) o;
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

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    //endregion
}
