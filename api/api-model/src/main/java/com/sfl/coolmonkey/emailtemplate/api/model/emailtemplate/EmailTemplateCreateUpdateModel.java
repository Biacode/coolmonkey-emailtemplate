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
 * Date: 01/04/16
 * Time: 12:24
 */
public class EmailTemplateCreateUpdateModel implements ApiModel {
    private static final long serialVersionUID = 6257706395117394446L;

    //region Properties
    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("type")
    private EmailTemplateTypeModel type;

    @JsonProperty("name")
    private String name;

    @JsonProperty("senderName")
    private String senderName;

    @JsonProperty("senderEmail")
    private String senderEmail;

    @JsonProperty("replyEmail")
    private String replyEmail;

    @JsonProperty("ccEmails")
    private List<String> ccEmails;

    @JsonProperty("bccEmails")
    private List<String> bccEmails;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("htmlContent")
    private String htmlContent;

    @JsonProperty("attachments")
    private List<String> attachments;

    @JsonProperty("active")
    private boolean active;
    //endregion

    //region Constructors
    public EmailTemplateCreateUpdateModel() {
    }

    public EmailTemplateCreateUpdateModel(final String uuid,
                                          final EmailTemplateTypeModel type,
                                          final String name,
                                          final String senderName,
                                          final String senderEmail,
                                          final String replyEmail,
                                          final List<String> ccEmails,
                                          final List<String> bccEmails,
                                          final String subject,
                                          final String htmlContent,
                                          final List<String> attachments,
                                          final boolean active) {
        this.uuid = uuid;
        this.type = type;
        this.name = name;
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.replyEmail = replyEmail;
        this.ccEmails = ccEmails;
        this.bccEmails = bccEmails;
        this.subject = subject;
        this.htmlContent = htmlContent;
        this.attachments = attachments;
        this.active = active;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailTemplateCreateUpdateModel)) {
            return false;
        }
        final EmailTemplateCreateUpdateModel that = (EmailTemplateCreateUpdateModel) o;
        return new EqualsBuilder()
                .append(active, that.active)
                .append(uuid, that.uuid)
                .append(type, that.type)
                .append(name, that.name)
                .append(senderName, that.senderName)
                .append(senderEmail, that.senderEmail)
                .append(replyEmail, that.replyEmail)
                .append(ccEmails, that.ccEmails)
                .append(bccEmails, that.bccEmails)
                .append(subject, that.subject)
                .append(htmlContent, that.htmlContent)
                .append(attachments, that.attachments)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(uuid)
                .append(type)
                .append(name)
                .append(senderName)
                .append(senderEmail)
                .append(replyEmail)
                .append(ccEmails)
                .append(bccEmails)
                .append(subject)
                .append(htmlContent)
                .append(attachments)
                .append(active)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("type", type)
                .append("name", name)
                .append("senderName", senderName)
                .append("senderEmail", senderEmail)
                .append("replyEmail", replyEmail)
                .append("ccEmails", ccEmails)
                .append("bccEmails", bccEmails)
                .append("subject", subject)
                .append("htmlContent", htmlContent)
                .append("attachments", attachments)
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

    public EmailTemplateTypeModel getType() {
        return type;
    }

    public void setType(final EmailTemplateTypeModel type) {
        this.type = type;
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

    public String getReplyEmail() {
        return replyEmail;
    }

    public void setReplyEmail(final String replyEmail) {
        this.replyEmail = replyEmail;
    }

    public List<String> getCcEmails() {
        return ccEmails;
    }

    public void setCcEmails(final List<String> ccEmails) {
        this.ccEmails = ccEmails;
    }

    public List<String> getBccEmails() {
        return bccEmails;
    }

    public void setBccEmails(final List<String> bccEmails) {
        this.bccEmails = bccEmails;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(final String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(final List<String> attachments) {
        this.attachments = attachments;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean isActive) {
        this.active = isActive;
    }
    //endregion
}
