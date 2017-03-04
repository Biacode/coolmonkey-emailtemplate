package com.sfl.coolmonkey.emailtemplate.service.helper.impl;

import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.emailtemplate.externalclients.translations.communicator.TranslationsServiceCommunicator;
import com.sfl.coolmonkey.translations.api.model.translation.TranslationModel;
import com.sfl.coolmonkey.translations.api.model.translation.request.GetAllTranslationsByUiLocationRequest;
import com.sfl.coolmonkey.translations.api.model.translation.response.GetAllTranslationsByUiLocationResponse;
import org.apache.commons.lang3.RandomStringUtils;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 28/03/16
 * Time: 17:55
 */
public class ExternalServiceCommunicatorDummyImpl implements TranslationsServiceCommunicator {

    //region Constants
    private static final int TEN = 10;

    private static final String EMAIL_SEND_TO = "EmailSendTo";
    //endregion

    //region Dependencies
    //endregion

    //region Constructors
    public ExternalServiceCommunicatorDummyImpl() {
    }
    //endregion

    //region Public methods

    //region Translations
    @Nonnull
    @Override
    public ResultResponseModel<GetAllTranslationsByUiLocationResponse> getAllTranslationsByUiLocation(@Nonnull final GetAllTranslationsByUiLocationRequest request) {
        return new ResultResponseModel<>(new GetAllTranslationsByUiLocationResponse(buildEnumTranslations()));
    }
    //endregion

    //endregion

    //region Utility methods
    private List<TranslationModel> buildEnumTranslations() {
        return Arrays.asList(
                new TranslationModel(UUID.randomUUID().toString(), EMAIL_SEND_TO, "InfoToAddress", "en-us", RandomStringUtils.random(TEN)),
                new TranslationModel(UUID.randomUUID().toString(), EMAIL_SEND_TO, "CustomerEmployee", "en-us", RandomStringUtils.random(TEN)),
                new TranslationModel(UUID.randomUUID().toString(), EMAIL_SEND_TO, "AppointmentToAddress", "en-us", RandomStringUtils.random(TEN))
        );
    }
    //endregion
}
