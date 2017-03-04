package com.sfl.coolmonkey.emailtemplate.service.emailtemplate.component;

import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplateType;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

/**
 * User: Ruben Vardanyan
 * Company: SFL LLC
 * Date: 4/13/16
 * Time: 12:22 PM
 */
public interface EmailTemplateTypeTranslationComponent {

    @Nonnull
    List<String> getTranslationsForEmailType(@Nonnull final EmailTemplateType fieldType);

    @Nonnull
    Map<EmailTemplateType, List<String>> getTranslationsForAllFieldTypes();

}
