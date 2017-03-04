package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.response.AbstractResponseModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/27/15
 * Time: 10:44 AM
 */
public class CopyEmailTemplatesResponse extends AbstractResponseModel {
    private static final long serialVersionUID = 2415246339882428161L;

    //region Properties
    @JsonProperty("originalToNewUuidsMap")
    private Map<String, String> originalToNewUuidsMap;
    //endregion

    //region Constructors
    public CopyEmailTemplatesResponse() {
    }

    public CopyEmailTemplatesResponse(final Map<String, String> originalToNewUuidsMap) {
        this.originalToNewUuidsMap = originalToNewUuidsMap;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CopyEmailTemplatesResponse)) {
            return false;
        }
        final CopyEmailTemplatesResponse that = (CopyEmailTemplatesResponse) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(originalToNewUuidsMap, that.originalToNewUuidsMap)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(originalToNewUuidsMap)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("originalToNewUuidsMap", originalToNewUuidsMap)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public Map<String, String> getOriginalToNewUuidsMap() {
        return originalToNewUuidsMap;
    }

    public void setOriginalToNewUuidsMap(final Map<String, String> originalToNewUuidsMap) {
        this.originalToNewUuidsMap = originalToNewUuidsMap;
    }
    //endregion
}
