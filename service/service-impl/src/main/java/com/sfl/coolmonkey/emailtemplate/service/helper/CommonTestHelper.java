package com.sfl.coolmonkey.emailtemplate.service.helper;

import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.dto.EmailTemplateDto;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplateType;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

/**
 * User: Suren Aznauryan
 * Company: SFL LLC
 * Date: 8/21/15
 * Time: 8:33 PM
 */
@SuppressWarnings({
        "MagicNumber",
        "DuplicateStringLiteralInspection",
        "checkstyle:com.puppycrawl.tools.checkstyle.checks.coding.MagicNumberCheck",
        "pmd:AvoidDuplicateLiterals",
        "squid:S1192"
})
public class CommonTestHelper {

    //region Constructors
    public CommonTestHelper() {
    }
    //endregion

    //region Public methods

    //region Email template
    public EmailTemplate createEmailTemplate() {
        final EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setCompanyUuid(UUID.randomUUID().toString());
        emailTemplate.setType(EmailTemplateType.APPOINTMENT_EMAIL_TO_ADDRESS);
        emailTemplate.setName("Test email template name");
        emailTemplate.setSenderName("Test sender name");
        emailTemplate.setSenderEmail("some_dummy_guy@example.com");
        emailTemplate.setReplyEmail("another_dummy_guy@example.com");
        emailTemplate.setCcEmails(Collections.singletonList("now_the_dummy_guy_in_cc_list@example.com"));
        emailTemplate.setBccEmails(Collections.singletonList("he_is_annoying@example.com"));
        emailTemplate.setSubject("Definitely Not Spam");
        emailTemplate.setHtmlContent("Buy our stuff!!!!!!");
        emailTemplate.setAttachmentUuids(Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        return emailTemplate;
    }

    public EmailTemplateDto createEmailTemplateDto() {
        final EmailTemplateDto dto = new EmailTemplateDto();
        dto.setName("Test name");
        dto.setSenderName("Test sender name");
        dto.setSenderEmail("sender_email@example.com");
        dto.setReplyEmail("reply_email@example.com");
        dto.setCcEmails(Collections.singletonList("cc_email1@example.com"));
        dto.setBccEmails(Collections.singletonList("bcc_email1@example.com"));
        dto.setSubject("Definitely Not Spam");
        dto.setHtmlContent("Buy our stuff!!!!!!");
        dto.setAttachmentUuids(Collections.singletonList(UUID.randomUUID().toString()));
        return dto;
    }
    //endregion

    //endregion
}
