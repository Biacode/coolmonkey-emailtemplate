package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request;

import com.sfl.coolmonkey.commons.api.model.request.AbstractCompanyUuidAwareRequestModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateTypeModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.ws.rs.QueryParam;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/29/15
 * Time: 6:38 PM
 */
public class GetEmailTemplatesByTypeRequest extends AbstractCompanyUuidAwareRequestModel {
    private static final long serialVersionUID = 834306822957950544L;

    //region Properties
    @QueryParam("type")
    private EmailTemplateTypeModel type;
    //endregion

    //region Constructors
    public GetEmailTemplatesByTypeRequest() {
    }

    public GetEmailTemplatesByTypeRequest(final String companyUuid, final EmailTemplateTypeModel type) {
        super(companyUuid);
        this.type = type;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetEmailTemplatesByTypeRequest)) {
            return false;
        }
        final GetEmailTemplatesByTypeRequest that = (GetEmailTemplatesByTypeRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(type, that.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(type)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("type", type)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public EmailTemplateTypeModel getType() {
        return type;
    }

    public void setType(final EmailTemplateTypeModel type) {
        this.type = type;
    }
    //endregion
}
