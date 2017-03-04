package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.ApiModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 10/27/15
 * Time: 5:39 PM
 */
public class EmailGridModel implements ApiModel {
    private static final long serialVersionUID = -4178294043882809422L;

    //region Properties
    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private EmailTemplateTypeModel type;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("senderName")
    private String senderName;

    @JsonProperty("senderEmail")
    private String senderEmail;

    @JsonProperty("usedOnDispositionCodes")
    private int usedOnDispositionCodes;

    @JsonProperty("numberOfAttachments")
    private int numberOfAttachments;

    @JsonProperty("active")
    private boolean active;
    //endregion

    //region Constructors
    public EmailGridModel() {
    }

    public EmailGridModel(final String uuid, final String name, final EmailTemplateTypeModel type,
                          final String subject, final String senderName, final String senderEmail,
                          final int usedOnDispositionCodes, final int numberOfAttachments, final boolean active) {
        this.uuid = uuid;
        this.name = name;
        this.type = type;
        this.subject = subject;
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.usedOnDispositionCodes = usedOnDispositionCodes;
        this.numberOfAttachments = numberOfAttachments;
        this.active = active;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailGridModel)) {
            return false;
        }
        final EmailGridModel that = (EmailGridModel) o;
        return new EqualsBuilder()
                .append(usedOnDispositionCodes, that.usedOnDispositionCodes)
                .append(numberOfAttachments, that.numberOfAttachments)
                .append(active, that.active)
                .append(uuid, that.uuid)
                .append(name, that.name)
                .append(type, that.type)
                .append(subject, that.subject)
                .append(senderName, that.senderName)
                .append(senderEmail, that.senderEmail)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(uuid)
                .append(name)
                .append(type)
                .append(subject)
                .append(senderName)
                .append(senderEmail)
                .append(usedOnDispositionCodes)
                .append(numberOfAttachments)
                .append(active)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("name", name)
                .append("type", type)
                .append("subject", subject)
                .append("senderName", senderName)
                .append("senderEmail", senderEmail)
                .append("usedOnDispositionCodes", usedOnDispositionCodes)
                .append("numberOfAttachments", numberOfAttachments)
                .append("active", active)
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

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public EmailTemplateTypeModel getType() {
        return type;
    }

    public void setType(final EmailTemplateTypeModel type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(final String senderName) {
        this.senderName = senderName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(final String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public int getUsedOnDispositionCodes() {
        return usedOnDispositionCodes;
    }

    public void setUsedOnDispositionCodes(final int usedOnDispositionCodes) {
        this.usedOnDispositionCodes = usedOnDispositionCodes;
    }

    public int getNumberOfAttachments() {
        return numberOfAttachments;
    }

    public void setNumberOfAttachments(final int numberOfAttachments) {
        this.numberOfAttachments = numberOfAttachments;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }
    //endregion
}
