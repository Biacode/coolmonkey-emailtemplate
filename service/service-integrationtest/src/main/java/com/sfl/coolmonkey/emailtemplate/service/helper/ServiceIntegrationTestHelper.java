package com.sfl.coolmonkey.emailtemplate.service.helper;

import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.EmailTemplateService;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.dto.EmailTemplateDto;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/3/15
 * Time: 4:07 PM
 */
@Component
public class ServiceIntegrationTestHelper extends CommonTestHelper {

    //region Dependencies
    @Autowired
    private EmailTemplateService emailTemplateService;
    //endregion

    //region Constructors
    public ServiceIntegrationTestHelper() {
    }
    //endregion

    //region Public methods

    //region Email template
    public EmailTemplate createAndPersistEmailTemplate() {
        return createAndPersistEmailTemplate(UUID.randomUUID().toString());
    }

    public EmailTemplate createAndPersistEmailTemplate(final String companyUuid) {
        final EmailTemplateType type = EmailTemplateType.APPOINTMENT_EMAIL_TO_ADDRESS;
        final EmailTemplateDto dto = createEmailTemplateDto();
        return emailTemplateService.create(companyUuid, type, dto);
    }
    //endregion

    //endregion
}
