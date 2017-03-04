package com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.component.impl;

import com.sfl.coolmonkey.commons.api.model.CommonErrorType;
import com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.component.EmailTemplateFacadeValidationComponent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.EmailTemplateService;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Ruben Vardanyan
 * Company: SFL LLC
 * Date: 1/8/2016
 * Time: 2:30 PM
 */
@Component
public class EmailTemplateFacadeValidationComponentImpl implements EmailTemplateFacadeValidationComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateFacadeValidationComponentImpl.class);

    //region Dependencies
    @Autowired
    private EmailTemplateService emailTemplateService;
    //endregion

    //region Constructor
    public EmailTemplateFacadeValidationComponentImpl() {
        LOGGER.debug("Initializing email template validation component");
    }
    //endregion

    //region Public methods
    @Override
    public Map<CommonErrorType, Object> validateEmailTemplateNameIsUniqueForCreate(@Nonnull final String name, @Nonnull final String companyUuid) {
        Assert.notNull(name);
        Assert.notNull(companyUuid);
        final Map<CommonErrorType, Object> errors = new HashMap<>();
        if (emailTemplateService.checkIfEmailTemplateExistForNameAndCompany(name, companyUuid)) {
            errors.put(CommonErrorType.EMAIL_TEMPLATE_EXISTING_NAME, null);
        }
        return errors;
    }

    @Override
    public Map<CommonErrorType, Object> validateEmailTemplateNameIsUniqueForUpdate(@Nonnull final String name, @Nonnull final String emailTemplateUuid, @Nonnull final String companyUuid) {
        Assert.notNull(name);
        Assert.notNull(emailTemplateUuid);
        Assert.notNull(companyUuid);
        final EmailTemplate emailTemplate = emailTemplateService.getByUuid(emailTemplateUuid);
        final Map<CommonErrorType, Object> errors = new HashMap<>();
        if (emailTemplate.getCompanyUuid().equals(companyUuid) && (!emailTemplate.getName().equals(name) &&
                emailTemplateService.checkIfEmailTemplateExistForNameAndCompany(name, companyUuid))) {
            errors.put(CommonErrorType.EMAIL_TEMPLATE_EXISTING_NAME, null);
        }
        return errors;
    }
    //endregion
}
