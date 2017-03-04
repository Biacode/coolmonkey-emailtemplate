package com.sfl.coolmonkey.emailtemplate.service.emailtemplate.component.impl;

import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.component.EmailTemplateServiceValidationComponent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.dto.EmailTemplateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/28/15
 * Time: 2:22 PM
 */
@Component
public class EmailTemplateServiceValidationComponentImpl implements EmailTemplateServiceValidationComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateServiceValidationComponentImpl.class);

    //region Dependencies
    //endregion

    //region Constructors
    public EmailTemplateServiceValidationComponentImpl() {
        LOGGER.debug("Initializing email template service validation component");
    }
    //endregion

    //region Public methods
    @Override
    public void performEmailTemplateDtoValidations(@Nonnull final EmailTemplateDto dto) {
        Assert.notNull(dto);
        Assert.hasText(dto.getSenderName(), "The sender name should not be blank");
        Assert.hasText(dto.getSenderEmail(), "The sender email should not be blank");
        if (dto.getAttachmentUuids() != null) {
            Assert.noNullElements(dto.getAttachmentUuids().toArray(), "The attachments uuid list elements should not be null");
        }
    }
    //endregion

    //region Utility methods
    //endregion
}
