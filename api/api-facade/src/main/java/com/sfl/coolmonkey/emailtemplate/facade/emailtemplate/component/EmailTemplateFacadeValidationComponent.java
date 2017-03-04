package com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.component;


import com.sfl.coolmonkey.commons.api.model.CommonErrorType;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * User: Ruben Vardanyan
 * Company: SFL LLC
 * Date: 1/8/2016
 * Time: 2:25 PM
 */
public interface EmailTemplateFacadeValidationComponent {

    Map<CommonErrorType, Object> validateEmailTemplateNameIsUniqueForCreate(@Nonnull final String name, @Nonnull final String companyUuid);

    Map<CommonErrorType, Object> validateEmailTemplateNameIsUniqueForUpdate(@Nonnull final String name, @Nonnull final String emailTemplateUuid, @Nonnull final String companyUuid);
}
