package com.sfl.coolmonkey.emailtemplate.facade.helper.conversion.emailtemplate;

import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateSearchModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateTypeModel;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.component.EmailTemplateTypeTranslationComponent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 29/03/16
 * Time: 14:25
 */
@Component
public class EmailTemplateToEmailTemplateSearchModelConverter extends CustomConverter<EmailTemplate, EmailTemplateSearchModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateToEmailTemplateSearchModelConverter.class);

    //region Dependencies
    //endregion

    //region Constructors
    public EmailTemplateToEmailTemplateSearchModelConverter() {
        LOGGER.debug("Initializing");
    }
    //endregion

    //region Public methods
    @Override
    public EmailTemplateSearchModel convert(final EmailTemplate emailTemplate, final Type<? extends EmailTemplateSearchModel> type) {
        return new EmailTemplateSearchModel(
                emailTemplate.getUuid(),
                emailTemplate.getCompanyUuid(),
                emailTemplate.getName(),
                emailTemplate.getSenderName(),
                emailTemplate.getSenderEmail(),
                emailTemplate.getSubject(),
                mapperFacade.map(emailTemplate.getType(), EmailTemplateTypeModel.class),
                emailTemplate.isActive(),
                emailTemplate.getAttachmentUuids(),
                null
        );
    }
    //endregion
}
