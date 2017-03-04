package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request;

import com.sfl.coolmonkey.commons.api.model.request.AbstractRequestModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.SystemEmailTemplateTypeModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.ws.rs.QueryParam;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 11/25/15
 * Time: 11:21 AM
 */
public class GetEmailTemplateBySystemTypeRequest extends AbstractRequestModel {
    private static final long serialVersionUID = -8546405006709818239L;

    //region Properties
    @QueryParam("systemType")
    private SystemEmailTemplateTypeModel systemType;
    //endregion

    //region Constructors
    public GetEmailTemplateBySystemTypeRequest() {
    }

    public GetEmailTemplateBySystemTypeRequest(final SystemEmailTemplateTypeModel systemType) {
        this.systemType = systemType;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetEmailTemplateBySystemTypeRequest)) {
            return false;
        }
        final GetEmailTemplateBySystemTypeRequest that = (GetEmailTemplateBySystemTypeRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(systemType, that.systemType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(systemType)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("systemType", systemType)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public SystemEmailTemplateTypeModel getSystemType() {
        return systemType;
    }

    public void setSystemType(final SystemEmailTemplateTypeModel systemType) {
        this.systemType = systemType;
    }
    //endregion
}
