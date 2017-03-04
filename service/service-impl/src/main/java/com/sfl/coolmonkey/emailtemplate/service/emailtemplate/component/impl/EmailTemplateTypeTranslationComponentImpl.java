package com.sfl.coolmonkey.emailtemplate.service.emailtemplate.component.impl;

import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.emailtemplate.externalclients.translations.communicator.TranslationsServiceCommunicator;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.component.EmailTemplateTypeTranslationComponent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplateType;
import com.sfl.coolmonkey.translations.api.model.translation.TranslationModel;
import com.sfl.coolmonkey.translations.api.model.translation.request.GetAllTranslationsByUiLocationRequest;
import com.sfl.coolmonkey.translations.api.model.translation.response.GetAllTranslationsByUiLocationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * User: Ruben Vardanyan
 * Company: SFL LLC
 * Date: 4/13/16
 * Time: 12:23 PM
 */
@Component
public class EmailTemplateTypeTranslationComponentImpl implements EmailTemplateTypeTranslationComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateTypeTranslationComponentImpl.class);

    //region Static Properties
    private static final Map<EmailTemplateType, String> KEY_MAP;
    private static final String UI_LOCATION = "EmailSendTo";
    //endregion

    //region Dependencies
    @Autowired
    private TranslationsServiceCommunicator translationsServiceCommunicator;
    //endregion

    //region Static
    static {
        KEY_MAP = new EnumMap<>(EmailTemplateType.class);
        KEY_MAP.put(EmailTemplateType.INFO_TO_ADDRESS, "InfoToAddress");
        KEY_MAP.put(EmailTemplateType.NOTIFICATION_TO_SALES_USER, "CustomerEmployee");
        KEY_MAP.put(EmailTemplateType.APPOINTMENT_EMAIL_TO_ADDRESS, "AppointmentToAddress");
    }
    //endregion

    //region Constructor
    public EmailTemplateTypeTranslationComponentImpl() {
        LOGGER.debug("Initializing email template type translation component");
    }
    //endregion

    @Nonnull
    @Override
    public List<String> getTranslationsForEmailType(@Nonnull final EmailTemplateType fieldType) {
        final GetAllTranslationsByUiLocationRequest translationRequest = new GetAllTranslationsByUiLocationRequest();
        translationRequest.setUiLocation(UI_LOCATION);
        final ResultResponseModel<GetAllTranslationsByUiLocationResponse> result = translationsServiceCommunicator.getAllTranslationsByUiLocation(translationRequest);
        return result.getResponse().getGrid().stream()
                .filter(translationModel -> translationModel.getKey().equals(KEY_MAP.get(fieldType)))
                .map(TranslationModel::getMessage)
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    public Map<EmailTemplateType, List<String>> getTranslationsForAllFieldTypes() {
        final GetAllTranslationsByUiLocationRequest translationRequest = new GetAllTranslationsByUiLocationRequest();
        translationRequest.setUiLocation(UI_LOCATION);
        final ResultResponseModel<GetAllTranslationsByUiLocationResponse> result = translationsServiceCommunicator.getAllTranslationsByUiLocation(translationRequest);
        Map<EmailTemplateType, List<String>> translations = new EnumMap<>(EmailTemplateType.class);
        KEY_MAP.forEach((fieldType, s) -> translations.put(fieldType,
                result.getResponse().getGrid().stream().filter(t -> t.getKey().equals(s)).map(TranslationModel::getMessage).collect(Collectors.toList())));
        return translations;
    }
}
