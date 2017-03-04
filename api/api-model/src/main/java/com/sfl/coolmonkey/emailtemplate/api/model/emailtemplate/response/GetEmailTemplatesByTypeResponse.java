package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.response.AbstractResponseModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/29/15
 * Time: 6:41 PM
 */
public class GetEmailTemplatesByTypeResponse extends AbstractResponseModel {
    private static final long serialVersionUID = -218561644324890116L;

    //region Properties
    @JsonProperty("emailTemplates")
    private List<EmailTemplateModel> models;
    //endregion

    //region Constructors
    public GetEmailTemplatesByTypeResponse() {
    }

    public GetEmailTemplatesByTypeResponse(final List<EmailTemplateModel> models) {
        this.models = models;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetEmailTemplatesByTypeResponse)) {
            return false;
        }
        final GetEmailTemplatesByTypeResponse that = (GetEmailTemplatesByTypeResponse) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(models, that.models)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(models)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("models", models)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public List<EmailTemplateModel> getModels() {
        return models;
    }

    public void setModels(final List<EmailTemplateModel> models) {
        this.models = models;
    }
    //endregion
}