package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.response.AbstractResponseModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 10/27/15
 * Time: 8:25 PM
 */
public class GetEmailTemplateBySystemTypeResponse extends AbstractResponseModel {
    private static final long serialVersionUID = 8240188092367972236L;

    //region Properties
    @JsonProperty("emailTemplate")
    private EmailTemplateModel emailTemplate;
    //endregion

    //region Constructors
    public GetEmailTemplateBySystemTypeResponse() {
    }

    public GetEmailTemplateBySystemTypeResponse(final EmailTemplateModel emailTemplate) {
        this.emailTemplate = emailTemplate;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetEmailTemplateBySystemTypeResponse)) {
            return false;
        }
        final GetEmailTemplateBySystemTypeResponse that = (GetEmailTemplateBySystemTypeResponse) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(emailTemplate, that.emailTemplate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(emailTemplate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("emailTemplate", emailTemplate)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public EmailTemplateModel getEmailTemplate() {
        return emailTemplate;
    }

    public void setEmailTemplate(final EmailTemplateModel emailTemplate) {
        this.emailTemplate = emailTemplate;
    }
    //endregion
}
