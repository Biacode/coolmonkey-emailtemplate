package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.response.AbstractResponseModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 11/2/15
 * Time: 3:57 PM
 */
public class GetEmailTemplateResponse extends AbstractResponseModel {
    private static final long serialVersionUID = 2232340780174274775L;

    //region Properties
    @JsonProperty("emailTemplate")
    private EmailTemplateModel model;
    //endregion

    //region Constructors
    public GetEmailTemplateResponse() {
    }

    public GetEmailTemplateResponse(final EmailTemplateModel model) {
        this.model = model;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetEmailTemplateResponse)) {
            return false;
        }
        final GetEmailTemplateResponse that = (GetEmailTemplateResponse) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(model, that.model)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(model)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("model", model)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public EmailTemplateModel getModel() {
        return model;
    }

    public void setModel(final EmailTemplateModel model) {
        this.model = model;
    }
    //endregion
}
