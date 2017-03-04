package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.request.AbstractRequestModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 10/27/15
 * Time: 8:23 PM
 */
public class CopyEmailTemplatesRequest extends AbstractRequestModel {
    private static final long serialVersionUID = -1769225439138538351L;

    //region Properties
    @JsonProperty("fromCompanyUuid")
    private String fromCompanyUuid;

    @JsonProperty("toCompanyUuid")
    private String toCompanyUuid;
    //endregion

    //region Constructors
    public CopyEmailTemplatesRequest() {
    }

    public CopyEmailTemplatesRequest(final String fromCompanyUuid, final String toCompanyUuid) {
        this.fromCompanyUuid = fromCompanyUuid;
        this.toCompanyUuid = toCompanyUuid;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CopyEmailTemplatesRequest)) {
            return false;
        }
        final CopyEmailTemplatesRequest that = (CopyEmailTemplatesRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(fromCompanyUuid, that.fromCompanyUuid)
                .append(toCompanyUuid, that.toCompanyUuid)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(fromCompanyUuid)
                .append(toCompanyUuid)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fromCompanyUuid", fromCompanyUuid)
                .append("toCompanyUuid", toCompanyUuid)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getFromCompanyUuid() {
        return fromCompanyUuid;
    }

    public void setFromCompanyUuid(final String fromCompanyUuid) {
        this.fromCompanyUuid = fromCompanyUuid;
    }

    public String getToCompanyUuid() {
        return toCompanyUuid;
    }

    public void setToCompanyUuid(final String toCompanyUuid) {
        this.toCompanyUuid = toCompanyUuid;
    }
    //endregion
}
