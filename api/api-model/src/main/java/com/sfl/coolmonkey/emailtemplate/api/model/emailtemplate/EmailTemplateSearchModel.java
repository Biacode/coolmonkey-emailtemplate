package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.coolmonkey.commons.api.model.ApiModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 29/03/16
 * Time: 12:32
 */
public class EmailTemplateSearchModel implements ApiModel {
    private static final long serialVersionUID = 2988477971886877379L;

    //region Properties
    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("companyUuid")
    private String companyUuid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("senderName")
    private String senderName;

    @JsonProperty("senderEmail")
    private String senderEmail;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("type")
    private EmailTemplateTypeModel type;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("attachmentsUuids")
    private List<String> attachmentsUuids;

    @JsonProperty("typeTexts")
    private List<String> typeTexts;
    //endregion

    //region Constructors
    public EmailTemplateSearchModel() {
    }

    public EmailTemplateSearchModel(final String uuid,
                                    final String companyUuid,
                                    final String name,
                                    final String senderName,
                                    final String senderEmail,
                                    final String subject,
                                    final EmailTemplateTypeModel type,
                                    final boolean active,
                                    final List<String> attachmentsUuids,
                                    final List<String> typeTexts) {
        this.uuid = uuid;
        this.companyUuid = companyUuid;
        this.name = name;
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.subject = subject;
        this.type = type;
        this.active = active;
        this.attachmentsUuids = attachmentsUuids;
        this.typeTexts = typeTexts;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailTemplateSearchModel)) {
            return false;
        }
        final EmailTemplateSearchModel that = (EmailTemplateSearchModel) o;
        return new EqualsBuilder()
                .append(active, that.active)
                .append(uuid, that.uuid)
                .append(companyUuid, that.companyUuid)
                .append(name, that.name)
                .append(senderName, that.senderName)
                .append(senderEmail, that.senderEmail)
                .append(subject, that.subject)
                .append(type, that.type)
                .append(attachmentsUuids, that.attachmentsUuids)
                .append(typeTexts, that.typeTexts)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(uuid)
                .append(companyUuid)
                .append(name)
                .append(senderName)
                .append(senderEmail)
                .append(subject)
                .append(type)
                .append(active)
                .append(attachmentsUuids)
                .append(typeTexts)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("companyUuid", companyUuid)
                .append("name", name)
                .append("senderName", senderName)
                .append("senderEmail", senderEmail)
                .append("subject", subject)
                .append("type", type)
                .append("active", active)
                .append("attachmentsUuids", attachmentsUuids)
                .append("typeTexts", typeTexts)
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

    public String getCompanyUuid() {
        return companyUuid;
    }

    public void setCompanyUuid(final String companyUuid) {
        this.companyUuid = companyUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public EmailTemplateTypeModel getType() {
        return type;
    }

    public void setType(final EmailTemplateTypeModel type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public List<String> getAttachmentsUuids() {
        return attachmentsUuids;
    }

    public void setAttachmentsUuids(final List<String> attachmentsUuids) {
        this.attachmentsUuids = attachmentsUuids;
    }

    public List<String> getTypeTexts() {
        return typeTexts;
    }

    public void setTypeTexts(final List<String> typeTexts) {
        this.typeTexts = typeTexts;
    }
    //endregion
}
