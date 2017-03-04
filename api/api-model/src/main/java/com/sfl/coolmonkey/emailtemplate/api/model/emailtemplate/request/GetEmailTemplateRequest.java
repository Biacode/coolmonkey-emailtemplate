package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request;

import com.sfl.coolmonkey.commons.api.model.request.AbstractCompanyUuidAwareRequestModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.ws.rs.QueryParam;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 11/2/15
 * Time: 3:55 PM
 */
public class GetEmailTemplateRequest extends AbstractCompanyUuidAwareRequestModel {
    private static final long serialVersionUID = -2155792745581780477L;

    //region Properties
    @QueryParam("uuid")
    private String uuid;
    //endregion

    //region Constructors
    public GetEmailTemplateRequest() {
    }

    public GetEmailTemplateRequest(final String uuid, final String companyUuid) {
        this.uuid = uuid;
        setCompanyUuid(companyUuid);
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetEmailTemplateRequest)) {
            return false;
        }
        final GetEmailTemplateRequest that = (GetEmailTemplateRequest) o;
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
