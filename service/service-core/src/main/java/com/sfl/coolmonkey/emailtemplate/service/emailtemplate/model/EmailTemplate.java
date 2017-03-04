package com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model;

import com.sfl.coolmonkey.emailtemplate.service.common.model.AbstractDomainRootDocumentEntityModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/22/15
 * Time: 5:52 PM
 */
@Document(collection = "emailTemplates")
public class EmailTemplate extends AbstractDomainRootDocumentEntityModel {
    private static final long serialVersionUID = -2341128276791243782L;

    //region Properties
    @Field("uuid")
    @Indexed(name = "email_template_uuid_idx", direction = IndexDirection.ASCENDING, unique = true)
    private String uuid;

    @Field("companyUuid")
    private String companyUuid;

    @Field("type")
    private EmailTemplateType type;

    @Field("systemEmailTemplateType")
    private SystemEmailTemplateType systemEmailTemplateType;

    @Field("name")
    private String name;

    @Field("senderName")
    private String senderName;

    @Field("senderEmail")
    private String senderEmail;

    @Field("replyEmail")
    private String replyEmail;

    @Field("ccEmails")
    private List<String> ccEmails;

    @Field("bccEmails")
    private List<String> bccEmails;

    @Field("subject")
    private String subject;

    @Field("htmlContent")
    private String htmlContent;

    @Field("attachmentUuids")
    private List<String> attachmentUuids;

    @Field("active")
    private boolean active;
    //endregion

    //region Constructors
    public EmailTemplate() {
        setInstanceDefaultProperties();
    }
    //endregion

    //region Utility methods
    private void setInstanceDefaultProperties() {
        uuid = UUID.randomUUID().toString();
        active = true;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailTemplate)) {
            return false;
        }
        final EmailTemplate that = (EmailTemplate) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(active, that.active)
                .append(uuid, that.uuid)
                .append(companyUuid, that.companyUuid)
                .append(type, that.type)
                .append(systemEmailTemplateType, that.systemEmailTemplateType)
                .append(name, that.name)
                .append(senderName, that.senderName)
                .append(senderEmail, that.senderEmail)
                .append(replyEmail, that.replyEmail)
                .append(ccEmails, that.ccEmails)
                .append(bccEmails, that.bccEmails)
                .append(subject, that.subject)
                .append(htmlContent, that.htmlContent)
                .append(attachmentUuids, that.attachmentUuids)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(uuid)
                .append(companyUuid)
                .append(type)
                .append(systemEmailTemplateType)
                .append(name)
                .append(senderName)
                .append(senderEmail)
                .append(replyEmail)
                .append(ccEmails)
                .append(bccEmails)
                .append(subject)
                .append(htmlContent)
                .append(attachmentUuids)
                .append(active)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("companyUuid", companyUuid)
                .append("type", type)
                .append("systemEmailTemplateType", systemEmailTemplateType)
                .append("name", name)
                .append("senderName", senderName)
                .append("senderEmail", senderEmail)
                .append("replyEmail", replyEmail)
                .append("ccEmails", ccEmails)
                .append("bccEmails", bccEmails)
                .append("subject", subject)
                .append("attachmentUuids", attachmentUuids)
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

    public String getCompanyUuid() {
        return companyUuid;
    }

    public void setCompanyUuid(final String companyUuid) {
        this.companyUuid = companyUuid;
    }

    public EmailTemplateType getType() {
        return type;
    }

    public void setType(final EmailTemplateType type) {
        this.type = type;
    }

    public SystemEmailTemplateType getSystemEmailTemplateType() {
        return systemEmailTemplateType;
    }

    public void setSystemEmailTemplateType(final SystemEmailTemplateType systemEmailTemplateType) {
        this.systemEmailTemplateType = systemEmailTemplateType;
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

    public List<String> getAttachmentUuids() {
        return attachmentUuids;
    }

    public void setAttachmentUuids(final List<String> attachmentUuids) {
        this.attachmentUuids = attachmentUuids;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }
    //endregion
}
