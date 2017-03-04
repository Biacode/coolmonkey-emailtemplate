package com.sfl.coolmonkey.emailtemplate.facade.helper;

import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.*;
import com.sfl.coolmonkey.emailtemplate.service.helper.CommonTestHelper;

import java.util.Collections;
import java.util.UUID;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/20/15
 * Time: 2:51 PM
 */
@SuppressWarnings({
        "MagicNumber",
        "DuplicateStringLiteralInspection",
        "checkstyle:com.puppycrawl.tools.checkstyle.checks.coding.MagicNumberCheck",
        "pmd:AvoidDuplicateLiterals",
        "squid:S1192"
})
public class FacadeImplTestHelper extends CommonTestHelper {

    //region Constants
    //endregion

    //region Constructors
    public FacadeImplTestHelper() {
    }
    //endregion

    //region Public methods

    //region Email template
    public EmailTemplateModel createEmailTemplateModel() {
        final EmailTemplateModel model = new EmailTemplateModel();
        model.setUuid(UUID.randomUUID().toString());
        model.setType(EmailTemplateTypeModel.APPOINTMENT_EMAIL_TO_ADDRESS);
        model.setName("Test name");
        model.setSenderName("Test sender name");
        model.setSenderEmail("sender@example.com");
        model.setReplyEmail("reply@example.com");
        model.setCcEmails(Collections.singletonList("ccEmail@example.com"));
        model.setBccEmails(Collections.singletonList("bccEmail@example.com"));
        model.setSubject("Definitely Not Spam");
        model.setHtmlContent("Buy our stuff!!!!!!");
        model.setAttachments(Collections.singletonList(createAttachmentModel()));
        model.setActive(true);
        return model;
    }

    public EmailTemplateCreateUpdateModel createEmailTemplateCreateUpdateModel() {
        final EmailTemplateCreateUpdateModel model = new EmailTemplateCreateUpdateModel();
        model.setUuid(UUID.randomUUID().toString());
        model.setType(EmailTemplateTypeModel.APPOINTMENT_EMAIL_TO_ADDRESS);
        model.setName("Test name");
        model.setSenderName("Test sender name");
        model.setSenderEmail("sender@example.com");
        model.setReplyEmail("reply@example.com");
        model.setCcEmails(Collections.singletonList("ccEmail@example.com"));
        model.setBccEmails(Collections.singletonList("bccEmail@example.com"));
        model.setSubject("Definitely Not Spam");
        model.setHtmlContent("Buy our stuff!!!!!!");
        model.setAttachments(Collections.singletonList(UUID.randomUUID().toString()));
        model.setActive(true);
        return model;
    }

    public EmailTemplateSearchModel createEmailTemplateSearchModel() {
        return new EmailTemplateSearchModel(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                "Test name" + UUID.randomUUID().toString(),
                "Test sender name" + UUID.randomUUID().toString(),
                "Test sender email" + UUID.randomUUID().toString(),
                "Test subject" + UUID.randomUUID().toString(),
                EmailTemplateTypeModel.APPOINTMENT_EMAIL_TO_ADDRESS,
                true,
                Collections.singletonList(UUID.randomUUID().toString()),
                Collections.singletonList(UUID.randomUUID().toString())
        );
    }
    //endregion

    //region Attachment
    public AttachmentModel createAttachmentModel() {
        final AttachmentModel model = new AttachmentModel();
        model.setUuid(UUID.randomUUID().toString());
        model.setFileName("Test origin name.jpg");
        return model;
    }
    //endregion

    //endregion
}
