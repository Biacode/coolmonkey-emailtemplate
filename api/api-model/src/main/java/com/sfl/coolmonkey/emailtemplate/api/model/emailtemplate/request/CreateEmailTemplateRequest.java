package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.request.AbstractCompanyUuidAwareRequestModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateCreateUpdateModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/27/15
 * Time: 10:35 AM
 */
public class CreateEmailTemplateRequest extends AbstractCompanyUuidAwareRequestModel {
    private static final long serialVersionUID = -5905143374999967579L;

    //region Properties
    @JsonProperty("emailTemplate")
    private EmailTemplateCreateUpdateModel emailTemplateModel;
    //endregion

    //region Constructors
    public CreateEmailTemplateRequest() {
    }

    public CreateEmailTemplateRequest(final String companyUuid, final EmailTemplateCreateUpdateModel emailTemplateModel) {
        super(companyUuid);
        this.emailTemplateModel = emailTemplateModel;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateEmailTemplateRequest)) {
            return false;
        }
        final CreateEmailTemplateRequest that = (CreateEmailTemplateRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(emailTemplateModel, that.emailTemplateModel)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(emailTemplateModel)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("emailTemplateModel", emailTemplateModel)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public EmailTemplateCreateUpdateModel getEmailTemplateModel() {
        return emailTemplateModel;
    }

    public void setEmailTemplateModel(final EmailTemplateCreateUpdateModel emailTemplateModel) {
        this.emailTemplateModel = emailTemplateModel;
    }
    //endregion
}
