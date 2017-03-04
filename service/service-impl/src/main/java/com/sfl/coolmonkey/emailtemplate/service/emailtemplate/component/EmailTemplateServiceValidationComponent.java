package com.sfl.coolmonkey.emailtemplate.service.emailtemplate.component;

import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.dto.EmailTemplateDto;

import javax.annotation.Nonnull;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/28/15
 * Time: 2:21 PM
 */
public interface EmailTemplateServiceValidationComponent {
    void performEmailTemplateDtoValidations(@Nonnull final EmailTemplateDto dto);
}
