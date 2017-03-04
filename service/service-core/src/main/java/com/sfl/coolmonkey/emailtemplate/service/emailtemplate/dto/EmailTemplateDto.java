package com.sfl.coolmonkey.emailtemplate.service.emailtemplate.dto;

import com.sfl.coolmonkey.emailtemplate.service.common.dto.AbstractDomainEntityModelDto;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/22/15
 * Time: 7:09 PM
 */
public class EmailTemplateDto extends AbstractDomainEntityModelDto<EmailTemplate> {
    private static final long serialVersionUID = 5294007773398735707L;

    //region Properties
    private String name;

    private String senderName;

    private String senderEmail;

    private String replyEmail;

    private List<String> ccEmails;

    private List<String> bccEmails;

    private String subject;

    private String htmlContent;

    private List<String> attachmentUuids;
    //endregion

    //region Constructors
    public EmailTemplateDto() {
    }
    //endregion

    //region Public methods
    @Override
    public void updateDomainEntityProperties(final EmailTemplate domainEntity) {
        domainEntity.setName(getName());
        domainEntity.setSenderName(getSenderName());
        domainEntity.setSenderEmail(getSenderEmail());
        domainEntity.setReplyEmail(getReplyEmail());
        domainEntity.setCcEmails(getCcEmails());
        domainEntity.setBccEmails(getBccEmails());
        domainEntity.setSubject(getSubject());
        domainEntity.setHtmlContent(getHtmlContent());
        domainEntity.setAttachmentUuids(getAttachmentUuids());
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailTemplateDto)) {
            return false;
        }
        final EmailTemplateDto that = (EmailTemplateDto) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
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
                .append(name)
                .append(senderName)
                .append(senderEmail)
                .append(replyEmail)
                .append(ccEmails)
                .append(bccEmails)
                .append(subject)
                .append(htmlContent)
                .append(attachmentUuids)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("senderName", senderName)
                .append("senderEmail", senderEmail)
                .append("replyEmail", replyEmail)
                .append("ccEmails", ccEmails)
                .append("bccEmails", bccEmails)
                .append("subject", subject)
                .append("attachmentUuids", attachmentUuids)
                .toString();
    }
    //endregion

    //region Properties getters and setters
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
    //endregion
}
